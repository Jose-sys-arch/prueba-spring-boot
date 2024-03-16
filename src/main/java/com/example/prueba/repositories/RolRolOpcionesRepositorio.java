package com.example.prueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.prueba.interfaces.RolOpcionProyeccion;
import com.example.prueba.models.RolRolOpcionesModel;

public interface RolRolOpcionesRepositorio extends CrudRepository<RolRolOpcionesModel, Integer> {

    // obtener las opciones de acuerdo al rol

    @Query(value = "SELECT ro.nombre_opcion as nombreOpcion, rro.url as url, rro.icon as icon FROM rol_rol_opciones rro INNER JOIN rol_opciones ro ON rro.rol_opciones_id_opcion = ro.id_opcion INNER JOIN rol r ON r.id_rol = rro.rol_id_rol WHERE r.rol_name = :rolName", nativeQuery = true)
    public List<RolOpcionProyeccion> obtenerOpcionesPorRol(@Param("rolName") String rolName);
}

