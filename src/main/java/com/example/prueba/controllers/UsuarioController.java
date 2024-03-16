package com.example.prueba.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.DTOS.Usuario.RegistroUsuarioDTO;
import com.example.prueba.DTOS.Usuario.UpdateUserDTO;
import com.example.prueba.interfaces.PersonaUsuario;
import com.example.prueba.models.UsuarioModel;
import com.example.prueba.services.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    private final JdbcTemplate jdbcTemplate;

    public UsuarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioById(@PathVariable("id") Integer id) {
        System.out.println("Id del usuario buscado es: " + id);
        return usuarioService.obtenerUsuarioById(id);
    }

    @GetMapping
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping(path = "/information/{id}")
    public PersonaUsuario getPersonAndUser(@PathVariable("id") Integer id) {
        return usuarioService.getPersonaUsuario(id);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> insertaUsuario(@Valid @RequestBody RegistroUsuarioDTO usuarioModel) {
        usuarioService.registrarUsuario(usuarioModel);
        return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        return usuarioService.iniciar_sesion(body.get("email"), body.get("password"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam Integer id) {
        if (usuarioService.cerrarSesion(id)) {
            return ResponseEntity.ok("Sesion cerrada");
        } else {
            return ResponseEntity.badRequest().body("El usuario no tiene una sesion activa");
        }
    }

    @PutMapping(path = "/{id}")
    public UsuarioModel actualizaUsuario(@PathVariable("id") Integer id, @RequestBody UpdateUserDTO usuarioModel) {
        return usuarioService.actualizaUsuario(id, usuarioModel);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminaUsuario(@PathVariable("id") Integer id) {
        return usuarioService.eliminaUsuario(id);
    }
}
