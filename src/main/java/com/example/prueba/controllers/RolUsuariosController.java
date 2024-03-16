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

import com.example.prueba.DTOS.RolUsuarios.RolUsuariosCreateDTO;
import com.example.prueba.DTOS.RolUsuarios.RolUsuariosUpdateDTO;
import com.example.prueba.models.RolUsuariosModel;
import com.example.prueba.services.RolUsuariosService;

@RestController
@RequestMapping("/rol-usuario")
public class RolUsuariosController {
    
    @Autowired
    RolUsuariosService rolUsuariosService;

    @GetMapping
    public ArrayList<RolUsuariosModel> obtenerRolesUsuario() {
        return rolUsuariosService.obtenerRolesUsuario();
    }

    @GetMapping(path = "/{id}")
    public Optional<RolUsuariosModel> obtenerRolById(Integer id) {
        return rolUsuariosService.obtenerRolById(id);
    }

    @PostMapping("/create")
    public RolUsuariosModel registraRol(@RequestBody RolUsuariosCreateDTO rolUsuario) {
        return rolUsuariosService.registraRol(rolUsuario);
    }

    @PutMapping(path = "/{id}")
    public RolUsuariosModel actualizaRol(@PathVariable("id") Integer id,@RequestBody RolUsuariosUpdateDTO rol){
        return rolUsuariosService.actualizarRolUsuario(id, rol);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminaRolUsuario(Integer id) {
        return rolUsuariosService.eliminaRolUsuario(id);
    }
}
