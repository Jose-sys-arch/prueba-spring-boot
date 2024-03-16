package com.example.prueba.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.prueba.DTOS.Persona.PersonaDTO;
import com.example.prueba.DTOS.Persona.UpdatePersonaDTO;
import com.example.prueba.models.PersonaModel;

import com.example.prueba.repositories.PersonaRepositorio;

@Service
public class PersonaService {

    @Autowired
    PersonaRepositorio personaRepositorio;

    public ArrayList<PersonaModel> obtenerUsuarios() {
        return (ArrayList<PersonaModel>) personaRepositorio.findAll();
    }

    public Optional<PersonaModel> obtenerUsuarioById(Integer id) {
        return personaRepositorio.findById(id);
    }

    public PersonaModel registraPersona(PersonaDTO persona) {
        PersonaModel persona1 = new PersonaModel();
        persona1.setNombres(persona.nombres);
        persona1.setApellidos(persona.apellidos);
        persona1.setIdentificacion(persona.identificacion);
        persona1.setFecha_nacimiento(persona.fecha_nacimiento);
        persona1.setStatus(persona.status);
        return personaRepositorio.save(persona1);
    }

    public String eliminaPersona(Integer id) {
        Optional<PersonaModel> personaOptional = personaRepositorio.findById(id);
        if (personaOptional.isPresent()) {
            if (personaOptional.get().getStatus() == false) {
                return "La persona ya ha sido eliminada";
            }
            PersonaModel persona = personaOptional.get();
            persona.setStatus(false);
            personaRepositorio.save(persona);
            return "Persona eliminada correctamente";
        }
        return "Persona no encontrada";
    }

    public PersonaModel actualizaPersona(Integer id, UpdatePersonaDTO persona) {
        Optional<PersonaModel> personaOptional = personaRepositorio.findById(id);
        if (personaOptional.isPresent()) {
            PersonaModel persona1 = personaOptional.get();
            persona1.setNombres(persona.nombres);
            persona1.setApellidos(persona.apellidos);
            persona1.setIdentificacion(persona.identificacion);
            return personaRepositorio.save(persona1);
        }
        return null;
    }
}
