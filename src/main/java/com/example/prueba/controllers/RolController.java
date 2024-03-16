package com.example.prueba.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.DTOS.Rol.RolCreateDTO;
import com.example.prueba.DTOS.Rol.RolUpdateDTO;
import com.example.prueba.models.RolModel;
import com.example.prueba.services.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
    
    @Autowired
    RolService rolService;

    @GetMapping
    public ArrayList<RolModel> obtenerRoles(){
        return rolService.obtenerRoles();
    }

    @GetMapping(path = "/{id}")
    public Optional<RolModel> obtenerRolById(Integer id){
        return rolService.obtenerRolById(id);
    }

    @PostMapping(path = "/new", name = "Registrar Rol")
    public RolModel registraRol(@RequestBody RolCreateDTO rol){
        return rolService.registraRol(rol);
    }

    @PutMapping(path = "/{id}")
    public RolModel actualizaRol(@PathVariable("id") Integer id,@RequestBody RolUpdateDTO rol){
        return rolService.actualizarRol(id, rol);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminaRol(@PathVariable("id") Integer id){
        String responseService = rolService.eliminaRol(id);
        return new ResponseEntity<>(responseService.toString(), HttpStatus.OK);
    }
}
