package com.example.prueba.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_usuarios")
public class RolUsuariosModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_roles_usuarios;

    private Integer rol_id_rol;
    private Integer usuarios_id_usuario;
    private Boolean deleted;
}
