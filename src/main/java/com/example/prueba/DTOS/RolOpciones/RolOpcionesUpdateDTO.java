package com.example.prueba.DTOS.RolOpciones;

import lombok.Data;

@Data
public class RolOpcionesUpdateDTO extends RolOpcionesCreateDTO {
    
    public Boolean deleted;
}
