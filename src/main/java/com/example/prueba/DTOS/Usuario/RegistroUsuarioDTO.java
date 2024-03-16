package com.example.prueba.DTOS.Usuario;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistroUsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    @Size(max = 100, message = "Los apellidos no pueden tener más de 100 caracteres")
    private String apellidos;

    @NotBlank(message = "La identificación no puede estar vacía")
    @Pattern(regexp = "^(?!.*?(\\d)\\1{3})\\d{10}$", message = "La identificación debe tener 10 dígitos y no puede tener 4 veces seguidas el mismo número")
    private String identificacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 8, max = 20, message = "El nombre de usuario debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[\\w]{8,20}$", message = "El nombre de usuario debe contener al menos un número, una letra mayúscula y no puede contener signos")
    private String user_name;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 dígitos")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W])[\\S]{8,}$", message = "La contraseña debe contener al menos un número, una letra mayúscula, un signo y no puede contener espacios")
    private String password;

    @NotBlank(message = "El rol no debe estar vacio")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "El rol debe ser 'ADMIN' o 'USER'")
    private String rol;
}
