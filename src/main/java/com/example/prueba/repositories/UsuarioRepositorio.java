package com.example.prueba.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.prueba.interfaces.PersonaUsuario;
import com.example.prueba.models.UsuarioModel;

@Repository
public interface UsuarioRepositorio extends CrudRepository<UsuarioModel, Integer> {

    Optional<UsuarioModel> findByUser(String user);

    Optional<UsuarioModel> findByMail(String mail);

    @Modifying
    @Query(value = "UPDATE usuarios SET status = :status WHERE id_usuario = :idUsuario", nativeQuery=true)
    Integer actualizarEstado(@Param("idUsuario") Integer idUsuario, @Param("status") String status);

    @Query(value = "SELECT registerpersonanduser(cast(:data as json))", nativeQuery = true)
    Integer registerPersonAndUser(@Param("data") String data);

    @Query(value = "SELECT id_persona as idPersona, nombres as nombres, apellidos as apellidos, identificacion as identificacion, fecha_nacimiento as fechaNacimiento, id_usuario as idUsuario, \"user\" as user, mail as mail FROM persona p INNER JOIN usuarios u ON p.id_persona = u.persona_id WHERE u.id_usuario = :id", nativeQuery = true)
    PersonaUsuario getDataPersonAndUser(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE sessions SET fecha_cierre = :fechaCierre WHERE id_usuario = :idUsuario", nativeQuery = true)
    Integer actualizarFechaCierre(@Param("fechaCierre") Date fechaCierre, @Param("idUsuario") Integer idUsuario);
}