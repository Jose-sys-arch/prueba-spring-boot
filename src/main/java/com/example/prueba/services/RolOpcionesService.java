package com.example.prueba.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba.DTOS.RolOpciones.RolOpcionesCreateDTO;
import com.example.prueba.DTOS.RolOpciones.RolOpcionesUpdateDTO;
import com.example.prueba.models.RolOpcionesModel;
import com.example.prueba.repositories.RolOpcionesRepositorio;

@Service
public class RolOpcionesService {

    @Autowired
    RolOpcionesRepositorio rolOpcionesRepositorio;

    public RolOpcionesModel insertaRolOpciones(RolOpcionesCreateDTO rolOpciones) {
        RolOpcionesModel rolModel = new RolOpcionesModel();
        rolModel.setNombre_opcion(rolOpciones.nombre_opcion);
        rolModel.setDeleted(false);
        return rolOpcionesRepositorio.save(rolModel);
    }

    public Optional<RolOpcionesModel> getRolOpcionesById(Integer id) {
        return rolOpcionesRepositorio.findById(id);
    }

    public ArrayList<RolOpcionesModel> obtenerRolesOpciones() {
        return (ArrayList<RolOpcionesModel>) rolOpcionesRepositorio.findAll();
    }

    public RolOpcionesModel actualizarRol(Integer id, RolOpcionesUpdateDTO rol) {
        Optional<RolOpcionesModel> rolOptional = rolOpcionesRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            RolOpcionesModel rolModel = rolOptional.get();
            rolModel.setNombre_opcion(rol.nombre_opcion);
            rolModel.setDeleted(rol.deleted);
            return rolOpcionesRepositorio.save(rolModel);
        }
        return null;
    }

    public String eliminaRol(Integer id) {
        Optional<RolOpcionesModel> rolOptional = rolOpcionesRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            if (rolOptional.get().getDeleted() == false) {
                return "El rol-opcion ya ha sido eliminado";
            }
            RolOpcionesModel rolModel = rolOptional.get();
            rolModel.setDeleted(false);
            rolOpcionesRepositorio.save(rolModel);
            return "Rol opcion eliminado correctamente";
        }
        return "Rol-opcion no encontrado";
    }
}
