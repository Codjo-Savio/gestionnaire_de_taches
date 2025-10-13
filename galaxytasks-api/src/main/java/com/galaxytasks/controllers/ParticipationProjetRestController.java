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

import com.galaxytasks.dto.ParticipationProjetDTO;
import com.galaxytasks.services.ParticipationProjetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/participations")
@CrossOrigin(origins = "*")
public class ParticipationProjetRestController {
    @Autowired
    ParticipationProjetService participationProjetService;

    // POST - Créer une nouvelle participation
    // api/participations
    @PostMapping("")
    public ResponseEntity<ParticipationProjetDTO> create(@Valid @RequestBody ParticipationProjetDTO participationProjetDTO){
        try{
            ParticipationProjetDTO created = participationProjetService.create(participationProjetDTO);
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
    public ResponseEntity<List<ParticipationProjetDTO>> getAll(){
        try{
            List<ParticipationProjetDTO> participations = participationProjetService.getAllParticipations();
            return ResponseEntity.ok(participations);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer une participation par don identifiant
    // api/participations/id?id={...,...}
    @GetMapping("/id")
    public ResponseEntity<Optional<ParticipationProjetDTO>> getById(@Valid @RequestParam Integer project, @Valid @RequestParam Integer user){
        try{
            Optional<ParticipationProjetDTO> participation = participationProjetService.getById(project, user);
            return ResponseEntity.ok(participation);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer les participations par projet
    // api/participations/project?project=...
    @GetMapping("/project")
    public ResponseEntity<List<ParticipationProjetDTO>> getByProject(@Valid @RequestParam Integer project){
        try{
            List<ParticipationProjetDTO> participations = participationProjetService.getByProject(project);
            return ResponseEntity.ok(participations);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer les participations par utilisateur
    // api/participations/user?user=...
    @GetMapping("/user")
    public ResponseEntity<List<ParticipationProjetDTO>> getByUser(@Valid @RequestParam Integer user){
        try{
            List<ParticipationProjetDTO> participations = participationProjetService.getByUser(user);
            return ResponseEntity.ok(participations);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - Mettre à jour une participation
    // api/participations/idProjet/idUtilisateur
    @PutMapping("/{idProjet}/{idUtilisateur}")
    public ResponseEntity<ParticipationProjetDTO> upadateById(@Valid @PathVariable Integer idProjet,
                                                            @Valid @PathVariable Integer idUtilisateur,
                                                            @Valid @RequestBody ParticipationProjetDTO participationProjetDTO){
        try{
            ParticipationProjetDTO participation = participationProjetService.updateById(idProjet, idUtilisateur, participationProjetDTO);
            return ResponseEntity.ok(participation);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Supprimer une participation
    // api/participations/idProjet/idUtlisateur
    @DeleteMapping("/{idProjet}/{idUtilisateur}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer idProjet,
                                        @Valid @PathVariable Integer idUtilisateur){
        if(participationProjetService.deleteById(idProjet, idUtilisateur)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
