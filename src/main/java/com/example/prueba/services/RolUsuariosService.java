package com.example.prueba.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.prueba.DTOS.RolUsuarios.RolUsuariosCreateDTO;
import com.example.prueba.DTOS.RolUsuarios.RolUsuariosUpdateDTO;
import com.example.prueba.models.RolUsuariosModel;
import com.example.prueba.repositories.RolUsuariosRepositorio;

@Service
public class RolUsuariosService {

    @Autowired
    RolUsuariosRepositorio rolUsuariosRepositorio;

    public Optional<RolUsuariosModel> obtenerRolById(Integer id) {
        return rolUsuariosRepositorio.findById(id);
    }

    public ArrayList<RolUsuariosModel> obtenerRolesUsuario() {
        return (ArrayList<RolUsuariosModel>) rolUsuariosRepositorio.findAll();
    }

    public RolUsuariosModel registraRol(RolUsuariosCreateDTO rolUsuario) {
        RolUsuariosModel rolModel = new RolUsuariosModel();
        rolModel.setRol_id_rol(rolUsuario.rol_id_rol);
        rolModel.setUsuarios_id_usuario(rolUsuario.usuarios_id_usuario);
        return rolUsuariosRepositorio.save(rolModel);
    }

    public RolUsuariosModel actualizarRolUsuario(Integer id, RolUsuariosUpdateDTO rolUsuario) {
        Optional<RolUsuariosModel> rolOptional = rolUsuariosRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            RolUsuariosModel rolModel = rolOptional.get();
            rolModel.setRol_id_rol(rolUsuario.rol_id_rol);
            rolModel.setUsuarios_id_usuario(rolUsuario.usuarios_id_usuario);
            rolModel.setDeleted(rolUsuario.deleted);
            return rolUsuariosRepositorio.save(rolModel);
        }
        return null;
    }

    public String eliminaRolUsuario(Integer id) {
        Optional<RolUsuariosModel> rolOptional = rolUsuariosRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            if (rolOptional.get().getDeleted() == false) {
                return "El rol ya ha sido eliminado";
            }
            RolUsuariosModel rolModel = rolOptional.get();
            rolModel.setDeleted(false);
            rolUsuariosRepositorio.save(rolModel);
            return "Rol eliminado correctamente";
        }
        return "Rol no encontrado";
    }
}
