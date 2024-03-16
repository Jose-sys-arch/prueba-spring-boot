package com.example.prueba.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sessions")
public class SessionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_session;

    private Date fecha_ingreso;
    private Date fechaCierre;
    private Integer idUsuario;
    private Boolean deleted;
}
