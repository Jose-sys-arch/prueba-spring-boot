package com.example.prueba.controllers;

import java.util.ArrayList;
import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.DTOS.Session.CreateSessionDTO;
import com.example.prueba.models.SessionModel;
import com.example.prueba.services.SessionService;

@CrossOrigin
@RestController
@RequestMapping("/session")
public class SessionController {
    
    @Autowired
    SessionService sessionService;

    @GetMapping
    public ArrayList<SessionModel> getSessions(){
        return sessionService.obtenerTodo();
    }

    @GetMapping("/{id}")
    public Optional<SessionModel> getSessionById(Integer id){
        return sessionService.obtenerPorId(id);
    }

    @GetMapping("/usuario/{id}")
    public List<SessionModel> getSessionByUsuarioId(@PathVariable Integer id){
        return sessionService.obtenerPorUsuario(id);
    }

    
    @GetMapping("/usuario/last-session/{id}")
    public Optional<SessionModel> lastSessionByUserId(@PathVariable Integer id){
        return sessionService.lastSession(id);
    }

    @PostMapping("/new")
    public SessionModel createSession(CreateSessionDTO session){
        return sessionService.registrar(session);
    }

    @PutMapping("/{id}")
    public String updateSession(Integer id, CreateSessionDTO session){
        return sessionService.actualizar(id, session);
    }

    @DeleteMapping("/{id}")
    public String deleteSession(Integer id){
        return sessionService.eliminar(id);
    }

}
