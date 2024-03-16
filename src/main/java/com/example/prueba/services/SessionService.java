package com.example.prueba.services;

import java.util.ArrayList;
import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba.DTOS.Session.CreateSessionDTO;
import com.example.prueba.models.PersonaModel;
import com.example.prueba.models.SessionModel;
import com.example.prueba.repositories.SessionRepositorio;

@Service
public class SessionService {
    
    @Autowired
    SessionRepositorio sessionRepositorio;

    public boolean isSessionActive(Integer idUsuario){
        boolean estado= sessionRepositorio.existsByIdUsuarioAndFechaCierreIsNull(idUsuario);
        return estado;
    }

    public List<SessionModel> obtenerPorUsuario(Integer idUsuario){
        return sessionRepositorio.findByIdUsuario(idUsuario);
    }

    public ArrayList<SessionModel> obtenerTodo(){
        return (ArrayList<SessionModel>) sessionRepositorio.findAll();
    }

    public Optional<SessionModel> obtenerPorId(Integer id){
        return sessionRepositorio.findById(id);
    }

    
    public Optional<SessionModel> lastSession(Integer id){
        return sessionRepositorio.lastSession(id);
    }

    public SessionModel registrar(CreateSessionDTO session){
        SessionModel sessionModel = new SessionModel();
        sessionModel.setFechaCierre(session.fecha_cierre);
        sessionModel.setFecha_ingreso(session.fecha_ingreso);
        sessionModel.setIdUsuario(session.id_usuario);
        sessionModel.setDeleted(false);
        return sessionRepositorio.save(sessionModel);
    }

    public String actualizar(Integer id, CreateSessionDTO session){
        Optional<SessionModel> sessionOptional = sessionRepositorio.findById(id);
        if (sessionOptional.isPresent()) {
            SessionModel sessionModel = sessionOptional.get();
            sessionModel.setFechaCierre(session.fecha_cierre);
            sessionModel.setFecha_ingreso(session.fecha_ingreso);
            sessionModel.setIdUsuario(session.id_usuario);
            sessionRepositorio.save(sessionModel);
            return "Sesión actualizada correctamente";
        }
        return "Sesión no encontrada";
    }

    public String eliminar(Integer id){
        Optional<SessionModel> sessionOptional = sessionRepositorio.findById(id);
        if (sessionOptional.isPresent()) {
            if (sessionOptional.get().getDeleted() == false) {
                return "La sesión ya ha sido eliminada";
            }
            SessionModel session = sessionOptional.get();
            session.setDeleted(true);
            sessionRepositorio.save(session);
            return "Sesión eliminada correctamente";
        }
        return "Sesión no encontrada";
    }


}
