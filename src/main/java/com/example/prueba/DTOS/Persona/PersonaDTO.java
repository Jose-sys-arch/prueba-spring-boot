package com.example.prueba.DTOS.Persona;


import java.sql.Date;

import lombok.Data;

@Data
public class PersonaDTO {
    public String nombres;
    public String apellidos;
    public String identificacion;
    public Date fecha_nacimiento;
    public Boolean status;
}
