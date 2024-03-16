package com.example.prueba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.prueba.interfaces.RolOpcionProyeccion;
import com.example.prueba.models.SessionModel;
import java.util.List;



@Repository
public interface SessionRepositorio extends CrudRepository<SessionModel, Integer> {
    List<SessionModel> findByIdUsuario(Integer idUsuario);

    boolean existsByIdUsuarioAndFechaCierreIsNull(Integer idUsuario);

    @Query(value = "SELECT * FROM sessions WHERE fecha_cierre IS NOT NULL AND id_usuario = :idUsuario ORDER BY id_session DESC LIMIT 1", nativeQuery = true)
    Optional<SessionModel> lastSession(@Param("idUsuario") Integer idUsuario);
}
