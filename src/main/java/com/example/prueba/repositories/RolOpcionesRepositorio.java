package com.example.prueba.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba.models.RolOpcionesModel;

@Repository
public interface RolOpcionesRepositorio extends CrudRepository<RolOpcionesModel, Integer> {
 
}
