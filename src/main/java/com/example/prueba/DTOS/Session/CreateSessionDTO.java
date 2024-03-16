package com.example.prueba.DTOS.Session;

import java.sql.Date;

import lombok.Data;

@Data
public class CreateSessionDTO {
    public Date fecha_cierre;
    public Date fecha_ingreso;
    public Integer id_usuario;
}
