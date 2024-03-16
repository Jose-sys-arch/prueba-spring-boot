package com.example.prueba.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba.DTOS.Persona.PersonaDTO;
import com.example.prueba.DTOS.Rol.*;
import com.example.prueba.models.PersonaModel;
import com.example.prueba.models.RolModel;
import com.example.prueba.repositories.RolRespositorio;

@Service
public class RolService {

    @Autowired
    RolRespositorio rolRepositorio;

    public ArrayList<RolModel> obtenerRoles() {
        return (ArrayList<RolModel>) rolRepositorio.findAll();
    }

    public Optional<RolModel> obtenerRolById(Integer id) {
        return rolRepositorio.findById(id);
    }

    public RolModel registraRol(RolCreateDTO rol) {
        RolModel rol1 = new RolModel();
        rol1.setRol_name(rol.rol_name);
        rol1.setDeleted(false);
        return rolRepositorio.save(rol1);
    }

    public RolModel actualizarRol(Integer id, RolUpdateDTO rol) {
        Optional<RolModel> rolOptional = rolRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            RolModel rolModel = rolOptional.get();
            rolModel.setRol_name(rol.rol_name);
            rolModel.setDeleted(rol.deleted);
            return rolRepositorio.save(rolModel);
        }
        return null;
    }

    public String eliminaRol(Integer id) {
        Optional<RolModel> rolOptional = rolRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            if (rolOptional.get().getDeleted() == false) {
                return "El rol ya ha sido eliminado";
            }
            RolModel rolModel = rolOptional.get();
            rolModel.setDeleted(false);
            rolRepositorio.save(rolModel);
            return "Rol eliminado correctamente";
        }
        return "Rol no encontrado";
    }
}
