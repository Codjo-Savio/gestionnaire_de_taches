package com.galaxytasks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxytasks.dto.AssignementDTO;
import com.galaxytasks.services.AssignementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/assignements")
@CrossOrigin(origins = "*")
public class AssignementRestController {
    @Autowired
    private AssignementService assignementService;

    // POST - Créer un nouvel assignement
    // api/assignements
    @PostMapping("")
    public ResponseEntity<AssignementDTO> create(@Valid @RequestBody AssignementDTO assignementDTO){
        try{
            AssignementDTO created = assignementService.create(assignementDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Récupérer toutes les participations
    // api/participations
    @GetMapping("")
    public ResponseEntity<List<AssignementDTO>> getAll(){
        try{
            List<AssignementDTO> assignements = assignementService.getAllAssignement();
            return ResponseEntity.ok(assignements);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer par id
    // api/participations/id?task=...&user=...
    @GetMapping("/id")
    public ResponseEntity<Optional<AssignementDTO>> getById(@Valid @RequestParam Integer task, @RequestParam Integer user){
        try{
            Optional<AssignementDTO> assignements = assignementService.getById(task, user);
            return ResponseEntity.ok(assignements);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer par tâche
    // api/participations/task?task=...
    @GetMapping("/task")
    public ResponseEntity<List<AssignementDTO>> getByTache(@Valid @RequestParam Integer task){
        try{
            List<AssignementDTO> assignements = assignementService.getByTache(task);
            return ResponseEntity.ok(assignements);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer par participant
    // api/participations/user?user=...
    @GetMapping("/user")
    public ResponseEntity<List<AssignementDTO>> getByParticipant(@Valid @RequestParam Integer user){
        try{
            List<AssignementDTO> assignements = assignementService.getByParticipant(user);
            return ResponseEntity.ok(assignements);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - Mise à jour d'un assignement
    // api/participations/{idTache}/{Participant}
    @PutMapping("/{idTache}/{idParticipant}")
    public ResponseEntity<AssignementDTO> updateById(@Valid @PathVariable Integer idTache,
                                                        @Valid @PathVariable Integer idParticipant,
                                                        @Valid @RequestBody AssignementDTO assignementDTO){
        try{
            AssignementDTO assignement = assignementService.updateById(idTache, idParticipant, assignementDTO);
            return ResponseEntity.ok(assignement);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Suppression d'un assignement
    // api/participations/{idTache}/{idParticipant}
    @DeleteMapping("/{idTache}/{idParticipant}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer idTache,
                                                        @Valid @PathVariable Integer idParticipant){
        if(assignementService.deleteById(idTache, idParticipant)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
   
}
