package com.example.prueba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.prueba.models.RolUsuariosModel;

@Repository
public interface RolUsuariosRepositorio extends CrudRepository<RolUsuariosModel, Integer> {

    @Query(value = "SELECT r.rol_name FROM usuarios u INNER JOIN rol_usuarios ru ON ru.usuarios_id_usuario = u.id_usuario INNER JOIN rol r ON r.id_rol = ru.rol_id_rol WHERE u.id_usuario = :idUsuario", nativeQuery = true)
    String findRolesByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
