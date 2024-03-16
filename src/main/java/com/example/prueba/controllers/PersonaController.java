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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.prueba.DTOS.Persona.PersonaDTO;
import com.example.prueba.DTOS.Persona.UpdatePersonaDTO;
import com.example.prueba.models.PersonaModel;
import com.example.prueba.services.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    
    @Autowired
    PersonaService personaService;

    @GetMapping
    public ArrayList<PersonaModel> obtenerUsuarios(){
        return personaService.obtenerUsuarios();
    }

    @GetMapping(path = "/{id}")
    public Optional<PersonaModel> obtenerUsuarioById(@PathVariable("id") Integer id){
        return personaService.obtenerUsuarioById(id);
    }

    @PostMapping(path = "/new", name = "Registrar Persona")
    public PersonaModel registraPersona(@RequestBody PersonaDTO persona){
        return personaService.registraPersona(persona);
    }

    @PutMapping(path = "/{id}")
    public PersonaModel actualizaPersona(@PathVariable("id") Integer id,@RequestBody UpdatePersonaDTO persona){
        return personaService.actualizaPersona(id, persona);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminaPersona(@PathVariable("id") Integer id){
        String responseService = personaService.eliminaPersona(id);
        return new ResponseEntity<>(responseService.toString(), HttpStatus.OK);
    }
}
