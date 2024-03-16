package com.example.prueba.repositories;

import com.example.prueba.models.RolModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRespositorio extends CrudRepository<RolModel, Integer> {
    
}
