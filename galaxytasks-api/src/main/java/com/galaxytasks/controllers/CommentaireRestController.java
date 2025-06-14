package com.galaxytasks.controllers;

import java.util.List;

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

import com.galaxytasks.dto.CommentaireDTO;
import com.galaxytasks.services.CommentaireService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentaireRestController {
    @Autowired
    private CommentaireService commentaireService;

    // POST - créer un nouveau commentaire
    // api/comments
    @PostMapping("")
    public ResponseEntity<CommentaireDTO> create(@Valid @RequestBody CommentaireDTO commentaireDTO){
        try{
            CommentaireDTO created = commentaireService.create(commentaireDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - récupérer tous les commentaires
    // api/comments
    @GetMapping("")
    public ResponseEntity<List<CommentaireDTO>> getAllCommentaires(){
        try{
            List<CommentaireDTO> comments = commentaireService.getAllCommentaires();
            return ResponseEntity.ok(comments);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer les commentaires par tâche
    // api/comments/task?task=...
    @GetMapping("/task")
    public ResponseEntity<List<CommentaireDTO>> getByTache(@Valid @RequestParam Integer task){
        try{
            List<CommentaireDTO> comments = commentaireService.getByTache(task);
            return ResponseEntity.ok(comments);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - récupérer les commentaires par utilisateur
    // api/comments/user?user=...
    @GetMapping("/user")
    public ResponseEntity<List<CommentaireDTO>> getByUser(@Valid @RequestParam Integer user){
        try{
            List<CommentaireDTO> comments = commentaireService.getByUser(user);
            return ResponseEntity.ok(comments);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - Mettre à jour un commentaire
    // api/comments/{id}
    @PutMapping("/{idCommentaire}")
    public ResponseEntity<CommentaireDTO> updateById(@Valid @PathVariable Integer idCommentaire, @RequestBody CommentaireDTO commentaireDTO){
        try{
            CommentaireDTO updated = commentaireService.updateById(idCommentaire, commentaireDTO);
            return ResponseEntity.ok(updated);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Supprimer un commentaire par son identifiant
    // api/comments/{id}
    @DeleteMapping("/{idCommentaire}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer idCommentaire){
        if(commentaireService.deleteById(idCommentaire)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
