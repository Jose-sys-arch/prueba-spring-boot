package com.example.prueba.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_rol_opciones")
public class RolRolOpcionesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_rol_rol_opciones;

    private Integer rol_id_rol;
    private Integer rol_opciones_id_opcion;
    private boolean deleted;
}
