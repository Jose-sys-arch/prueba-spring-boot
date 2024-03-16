package com.example.prueba.DTOS.Rol;

import lombok.Data;

@Data
public class RolUpdateDTO extends RolCreateDTO{
    public Boolean deleted;
}
