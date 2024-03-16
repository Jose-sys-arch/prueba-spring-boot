package com.example.prueba.DTOS.Persona;

import java.sql.Date;

import lombok.Data;

@Data
public class UpdatePersonaDTO {
    public String nombres;
    public String apellidos;
    public String identificacion;
    }