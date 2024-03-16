package com.example.prueba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba.DTOS.RolRolOpciones.CreateRolRolOpcionesDTO;
import com.example.prueba.interfaces.RolOpcionProyeccion;
import com.example.prueba.models.RolRolOpcionesModel;
import com.example.prueba.repositories.RolRolOpcionesRepositorio;

@Service
public class RolRolOpciones {

    @Autowired
    RolRolOpcionesRepositorio rolOpcionesRepositorio;

    public ArrayList<RolRolOpcionesModel> obtenerRoles() {
        return (ArrayList<RolRolOpcionesModel>) rolOpcionesRepositorio.findAll();
    }

    public Optional<RolRolOpcionesModel> getRolOpcionesById(Integer id) {
        return rolOpcionesRepositorio.findById(id);
    }

    public String eliminaRol(Integer id) {
        Optional<RolRolOpcionesModel> rolOptional = rolOpcionesRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            if (rolOptional.get().isDeleted() == false) {
                return "El rol ya ha sido eliminado";
            }
            RolRolOpcionesModel rolModel = rolOptional.get();
            rolModel.setDeleted(false);
            rolOpcionesRepositorio.save(rolModel);
            return "Rol eliminado correctamente";
        }
        return "Rol no encontrado";
    }

    public RolRolOpcionesModel registraRolRolOpciones(CreateRolRolOpcionesDTO rol) {
        RolRolOpcionesModel rol1 = new RolRolOpcionesModel();
        rol1.setRol_id_rol(rol.getRol_id_rol());
        rol1.setRol_opciones_id_opcion(rol.getRol_opciones_id_opcion());
        rol1.setDeleted(false);
        return rolOpcionesRepositorio.save(rol1);
    }

    public RolRolOpcionesModel actualizarRol(Integer id, CreateRolRolOpcionesDTO rol) {
        Optional<RolRolOpcionesModel> rolOptional = rolOpcionesRepositorio.findById(id);
        if (rolOptional.isPresent()) {
            RolRolOpcionesModel rolModel = rolOptional.get();
            rolModel.setRol_id_rol(rol.getRol_id_rol());
            rolModel.setRol_opciones_id_opcion(rol.getRol_opciones_id_opcion());
            return rolOpcionesRepositorio.save(rolModel);
        }
        return null;
    }

    // obtener las opciones de acuerdo a un rol id
    public List<RolOpcionProyeccion> getOpcionesPorRol(String rolName) {
        return rolOpcionesRepositorio.obtenerOpcionesPorRol(rolName);
    }
}
