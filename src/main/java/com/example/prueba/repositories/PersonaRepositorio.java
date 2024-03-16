package com.example.prueba.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.prueba.models.PersonaModel;

@Repository
public interface PersonaRepositorio extends CrudRepository<PersonaModel, Integer> {
    
}
