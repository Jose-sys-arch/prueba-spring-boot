package com.example.prueba.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.prueba.DTOS.Usuario.JwtDTO;
import com.example.prueba.DTOS.Usuario.RegistroUsuarioDTO;
import com.example.prueba.DTOS.Usuario.UpdateUserDTO;
import com.example.prueba.Utils.JwtUtil;
import com.example.prueba.interfaces.PersonaUsuario;
import com.example.prueba.models.SessionModel;
import com.example.prueba.models.UsuarioModel;
import com.example.prueba.repositories.RolUsuariosRepositorio;
import com.example.prueba.repositories.SessionRepositorio;
import com.example.prueba.repositories.UsuarioRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    SessionService sessionService;

    @Autowired
    RolUsuariosRepositorio rolUsuariosRepositorio;

    @Autowired
    SessionRepositorio sessionRepositorio;

    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepositorio.findAll();
    }

    public Optional<UsuarioModel> obtenerUsuarioById(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    public ResponseEntity<?> iniciar_sesion(String nombreUsuarioOEmail, String contraseña) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepositorio.findByUser(nombreUsuarioOEmail);

        if (!usuarioOptional.isPresent()) {
            // El usuario no fue encontrado por nombre de usuario; intenta buscar por correo
            usuarioOptional = usuarioRepositorio.findByMail(nombreUsuarioOEmail);
        }

        if (usuarioOptional.isPresent()) {

            if (sessionService.isSessionActive(usuarioOptional.get().getId_usuario())) {
                Map<String, String> map = new HashMap<>();
                map.put("mensaje", "El usuario ya tiene una sesion activa");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
            UsuarioModel usuario = usuarioOptional.get();
            if (!usuario.getStatus().trim().equals("BLOQUEADO")) {
                if (usuario.getPassword().equals(contraseña)) {
                    // Restablecer los intentos fallidos si la contraseña es correcta
                    reestablecerIntentosFallidos(usuarioOptional.get().getId_usuario());
                    // Registra la nueva sesión en la tabla de sesiones
                    SessionModel session = new SessionModel();
                    session.setIdUsuario(usuarioOptional.get().getId_usuario());
                    session.setFecha_ingreso(new Date(System.currentTimeMillis()));
                    session.setFechaCierre(null);
                    session.setDeleted(false);
                    sessionRepositorio.save(session);
                    JwtDTO jwtDTO = new JwtDTO();
                    jwtDTO.setId(usuarioOptional.get().getId_usuario());
                    jwtDTO.setCorreo(usuario.getMail());
                    jwtDTO.setRol(rolUsuariosRepositorio.findRolesByUsuarioId(usuario.getId_usuario()));
                    String token = jwtUtil.generateToken(jwtDTO);
                    Map<String, String> map = new HashMap<>();
                    map.put("token", token);
                    return new ResponseEntity<>(map, HttpStatus.OK);
                } else {
                    // Contraseña incorrecta, aumentar los intentos fallidos
                    usuario.setIntentos_fallidos(usuario.getIntentos_fallidos() + 1);
                    actualizaIntentosFallidos(usuarioOptional.get().getId_usuario(), usuario.getIntentos_fallidos());
                    if (usuario.getIntentos_fallidos() >= 3) {
                        // Bloquear al usuario después de tres intentos fallidos
                        cambiaStatusUsuario(usuarioOptional.get().getId_usuario(), "BLOQUEADO");
                        Map<String, String> map = new HashMap<>();
                        map.put("mensaje", "El usuario ha sido bloqueado");
                        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
                    }
                }
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("mensaje", "El usuario ha sido bloqueado");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("mensaje", "Erro desconocido");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public boolean cerrarSesion(Integer idUsuario) {
        if (!sessionService.isSessionActive(idUsuario)) {
            return false;
        }
        usuarioRepositorio.actualizarEstado(idUsuario, "ACTIVO");
        java.util.Date fechaCierre = new java.util.Date();
        usuarioRepositorio.actualizarFechaCierre(fechaCierre, idUsuario);
        return true;
    }

    public boolean registrarUsuario(RegistroUsuarioDTO dataUser) {
        try {
            String dataUserString = new ObjectMapper().writeValueAsString(dataUser);
            return usuarioRepositorio.registerPersonAndUser(dataUserString) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<UsuarioModel> actualizaIntentosFallidos(Integer id, Integer numIntentosFallidos) {
        String sql = "UPDATE usuarios SET intentos_fallidos = ? WHERE id_usuario = ?";
        jdbcTemplate.update(sql, numIntentosFallidos, id);
        return obtenerUsuarioById(id);
    }

    @Transactional
    public Integer cambiaStatusUsuario(Integer id, String status) {
        return usuarioRepositorio.actualizarEstado(id, status);
    }

    
    public PersonaUsuario getPersonaUsuario(Integer id) {
        return usuarioRepositorio.getDataPersonAndUser(id);
    }

    public void reestablecerIntentosFallidos(Integer id) {
        String sql = "UPDATE usuarios SET intentos_fallidos = 0, session_active = '0', status = 'ACTIVO' WHERE id_usuario = ?";
        jdbcTemplate.update(sql, id);
    }

    public UsuarioModel actualizaUsuario(Integer id, UpdateUserDTO usuario) {
        Optional<UsuarioModel> personaOptional = usuarioRepositorio.findById(id);
        if (personaOptional.isPresent()) {
            UsuarioModel usuarioModel = personaOptional.get();
            usuarioModel.setMail(usuario.getMail());
            usuarioModel.setPassword(usuario.getPassword());
            return usuarioRepositorio.save(usuarioModel);
        }
        return null;
    }

    public String eliminaUsuario(Integer id) {
        Optional<UsuarioModel> usuOptional = usuarioRepositorio.findById(id);
        if (usuOptional.isPresent()) {
            if (usuOptional.get().getDeleted() == false) {
                return "El usuario ya ha sido eliminado";
            }
            UsuarioModel usuarioModel = usuOptional.get();
            usuarioModel.setDeleted(true);
            usuarioRepositorio.save(usuarioModel);
            return "Usuario eliminado correctamente";
        }
        return "Usuario no encontrado";
    }
}
