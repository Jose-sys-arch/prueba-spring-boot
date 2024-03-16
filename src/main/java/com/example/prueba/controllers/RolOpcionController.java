package com.example.prueba.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.DTOS.RolOpciones.RolOpcionesCreateDTO;
import com.example.prueba.DTOS.RolOpciones.RolOpcionesUpdateDTO;
import com.example.prueba.models.RolOpcionesModel;
import com.example.prueba.services.RolOpcionesService;

@RestController
@RequestMapping("/rol-opciones")
public class RolOpcionController {
    
    @Autowired
    RolOpcionesService rolOpcionesService;

    @GetMapping
    public ArrayList<RolOpcionesModel> obtenerRolesOpciones(){
        return rolOpcionesService.obtenerRolesOpciones();
    }

    @GetMapping(path = "/{id}")
    public Optional<RolOpcionesModel> getRolOpcionesById(Integer id){
        return rolOpcionesService.getRolOpcionesById(id);
    }

    @PostMapping(path = "/create")
    public RolOpcionesModel registraRol(@RequestBody RolOpcionesCreateDTO rol){
        return rolOpcionesService.insertaRolOpciones(rol);
    }

    @PutMapping(path = "/{id}")
    public RolOpcionesModel actualizaRol(@PathVariable("id") Integer id,@RequestBody RolOpcionesUpdateDTO rol){
        return rolOpcionesService.actualizarRol(id, rol);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminaRol(@PathVariable("id") Integer id){
        return rolOpcionesService.eliminaRol(id);
    }
}
