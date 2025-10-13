package com.galaxytasks.controllers;

import java.time.LocalDateTime;
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

import com.galaxytasks.dto.ProjetCreateDTO;
import com.galaxytasks.dto.ProjetDTO;
import com.galaxytasks.services.ProjetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // Pour permettre les requêtes depuis le frontend
public class ProjetRestController {
    @Autowired // instanciation automatique
    private ProjetService projetService;

    // POST - Créer un nouveau projet
    // api/projects
    @PostMapping("")
    public ResponseEntity<ProjetDTO> create(@Valid @RequestBody ProjetCreateDTO projet){
        try{
            ProjetDTO project = projetService.create(projet);
            return ResponseEntity.status(HttpStatus.CREATED).body(project);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Récupérer tous les projets
    // api/projects
    @GetMapping("")
    public ResponseEntity<List<ProjetDTO>> getAllProjects(){
        List<ProjetDTO> projets = projetService.getAllProjects();
        return ResponseEntity.ok(projets);
    }

    // GET - récupérer un projet par son identifiant
    // api/projects/id
    @GetMapping("/id")
    public ResponseEntity<Optional<ProjetDTO>> getById(@Valid @RequestParam Integer id){
        try{
            Optional<ProjetDTO> project = projetService.getById(id);
            return ResponseEntity.ok(project);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer un projet par son identifiant
    // api/projects/titled
    @GetMapping("/title")
    public ResponseEntity<Optional<ProjetDTO>> getByIntitule(@Valid @RequestParam String title){
        try{
            Optional<ProjetDTO> project = projetService.getByIntitule(title);
            return ResponseEntity.ok(project);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer un projet par son identifiant
    // api/projects/titled
    @GetMapping("/user")
    public ResponseEntity<List<ProjetDTO>> getByUser(@Valid @RequestParam Integer user){
        try{
            List<ProjetDTO> project = projetService.getByUserId(user);
            return ResponseEntity.ok(project);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer un projet par son identifiant
    // api/projects/titled
    @GetMapping("/date")
    public ResponseEntity<Optional<ProjetDTO>> getByDate(@Valid @RequestParam LocalDateTime date){
        try{
            Optional<ProjetDTO> project = projetService.getByDate(date);
            return ResponseEntity.ok(project);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - Mise à jour d'un projet
    // api/projects/{idProject}
    @PutMapping("/{idProjet}")
    public ResponseEntity<Optional<ProjetDTO>> updateProject(@Valid @PathVariable Integer idProjet, @Valid @RequestBody ProjetCreateDTO projetCreateDTO){
        try{
            Optional<ProjetDTO> project = projetService.updateProject(idProjet, projetCreateDTO);
            return ResponseEntity.ok(project);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - supprimer un projet
    // api/projects/{idProjets}
    @DeleteMapping("/{idProjet}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer idProjet){
        if(projetService.deleteById(idProjet)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}

