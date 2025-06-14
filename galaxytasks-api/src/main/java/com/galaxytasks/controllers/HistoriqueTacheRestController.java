package com.galaxytasks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxytasks.dto.HistoriqueTacheDTO;
import com.galaxytasks.model.HistoriqueTache.TypeAction;
import com.galaxytasks.services.HistoriqueTacheService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class HistoriqueTacheRestController {
    @Autowired
    HistoriqueTacheService historiqueTacheService;

    // POST - créer un nouvel historique
    // api/history
    @PostMapping("")
    public ResponseEntity<HistoriqueTacheDTO> create(@Valid @RequestBody HistoriqueTacheDTO historiqueTacheDTO){
        try{
            HistoriqueTacheDTO created = historiqueTacheService.create(historiqueTacheDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - récupérer l'historique de toutes les tâches
    // api/history
    @GetMapping("")
    public ResponseEntity<List<HistoriqueTacheDTO>> getAllHistory(){
        try{
            List<HistoriqueTacheDTO> history = historiqueTacheService.getAllHistoryNotArchived();
            return ResponseEntity.ok(history);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer l'historique à partir d'une tâche
    // api/history/task?task=...
    @GetMapping("/task")
    public ResponseEntity<List<HistoriqueTacheDTO>> getByTache(@Valid @RequestParam Integer task){
        try{
            List<HistoriqueTacheDTO> history = historiqueTacheService.getByTacheNotArchived(task);
            return ResponseEntity.ok(history);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer l'historique à partir d'un participant
    // api/history/user?user=...
    @GetMapping("/user")
    public ResponseEntity<List<HistoriqueTacheDTO>> getByParticipant(@Valid @RequestParam Integer user){
        try{
            List<HistoriqueTacheDTO> history = historiqueTacheService.getByParticipantNotArchived(user);
            return ResponseEntity.ok(history);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer l'historique à partir de l'action effectuée
    // api/history/user?user=...
    @GetMapping("/action")
    public ResponseEntity<List<HistoriqueTacheDTO>> getByAction(@Valid @RequestParam TypeAction action){
        try{
            List<HistoriqueTacheDTO> history = historiqueTacheService.getByActionNotArchived(action);
            return ResponseEntity.ok(history);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    // PUT - archiver un historique
    // api/history/archived/{id}
    @PutMapping("archived/{id}")
    public ResponseEntity<HistoriqueTacheDTO> archiverById(@Valid @PathVariable Integer id){
        try{
            HistoriqueTacheDTO archived = historiqueTacheService.archiverById(id);
            return ResponseEntity.ok(archived);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - restaurer un historique
    // api/history/archived/{id}
    @PutMapping("restored/{id}")
    public ResponseEntity<HistoriqueTacheDTO> restaurerById(@Valid @PathVariable Integer id){
        try{
            HistoriqueTacheDTO archived = historiqueTacheService.restaurerById(id);
            return ResponseEntity.ok(archived);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer l'historique archivé
    // api/history/archived
    @GetMapping("archived")
    public ResponseEntity<List<HistoriqueTacheDTO>> getAllArchived(){
        try{
            List<HistoriqueTacheDTO> archived = historiqueTacheService.getAllArchived();
            return ResponseEntity.ok(archived);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}