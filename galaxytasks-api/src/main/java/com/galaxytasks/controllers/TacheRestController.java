package com.galaxytasks.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.galaxytasks.dto.TacheDTO;
import com.galaxytasks.services.TacheService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TacheRestController {
    @Autowired
    TacheService tacheService;

    // POST - créer une nouvelle tache
    // api/tasks
    @PostMapping("")
    public ResponseEntity<TacheDTO> create(@Valid @RequestBody TacheDTO tacheDTO){
        try{
            TacheDTO tache = tacheService.create(tacheDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(tache);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Récupérer tous les projets
    // api/tasks
    @GetMapping("")
    public ResponseEntity<List<TacheDTO>> getAllTasks(){
        List<TacheDTO> tasks = tacheService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // GET - Récupérer une tâche par son identifiant
    //api/tasks/id?id=...
    @GetMapping("/id")
    public ResponseEntity<Optional<TacheDTO>> getById(@Valid @RequestParam Integer id){
        try{
            Optional<TacheDTO> task = tacheService.getById(id);
            return ResponseEntity.ok(task);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer une tâche par son titre
    //api/tasks/title?title=...
    @GetMapping("/title")
    public ResponseEntity<Optional<TacheDTO>> getByTitle(@Valid @RequestParam String title){
        try{
            Optional<TacheDTO> task = tacheService.getByTitle(title);
            return ResponseEntity.ok(task);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer une liste de tâches en fonction du projet
    //api/tasks/project?project=...
    @GetMapping("/project")
    public ResponseEntity<List<TacheDTO>> getByProject(@Valid @RequestParam Integer project){
        try{
            List<TacheDTO> tasks = tacheService.getByProject(project);
            return ResponseEntity.ok(tasks);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer une liste de tâches en fonction de l'utilisateur
    //api/tasks/user?user=...
    @GetMapping("/user")
    public ResponseEntity<List<TacheDTO>> getByUser(@Valid @RequestParam Integer user){
        try{
            List<TacheDTO> tasks = tacheService.getByUser(user);
            return ResponseEntity.ok(tasks);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer une tâche par sa date de création
    //api/tasks/creation?creation=...
    @GetMapping("/creation")
    public ResponseEntity<List<TacheDTO>> getByDateCreation(@Valid @RequestParam LocalDateTime creation){
        try{
            List<TacheDTO> task = tacheService.getByDateCreation(creation);
            return ResponseEntity.ok(task);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer une tâche par sa date d'échéance
    //api/tasks/term?term=...
    @GetMapping("/term")
    public ResponseEntity<List<TacheDTO>> getByDateEcheance(@Valid @RequestParam LocalDateTime term){
        try{
            List<TacheDTO> task = tacheService.getByDateEcheance(term);
            return ResponseEntity.ok(task);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


     // PUT - Mise à  jour d'une tâche
    //api/tasks/{idTache}...
    @PutMapping("/{idTache}")
    public ResponseEntity<TacheDTO> upDateById(@Valid @PathVariable Integer idTache, @Valid @RequestBody TacheDTO tache){
        try{
            TacheDTO task = tacheService.updateById(idTache, tache);
            return ResponseEntity.ok(task);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE - Suppression d'une tâche
    // api/tasks/{idTache}
    @DeleteMapping("/{idTache}")
    public ResponseEntity<TacheDTO> deleteById(@Valid @PathVariable Integer idTache){
        if(tacheService.deleteTaskById(idTache)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }      
}
