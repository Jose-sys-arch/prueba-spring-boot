package com.example.prueba.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_usuario;

    private String user;
    
    private String password;
    private String mail;
    private String session_active;
    private Integer persona_id;
    private String status;
    private Boolean deleted;
    private Integer intentos_fallidos;

    // Getters y setters para todas las propiedades
}
