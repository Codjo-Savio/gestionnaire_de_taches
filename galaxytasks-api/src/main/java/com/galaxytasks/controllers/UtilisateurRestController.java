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


import com.galaxytasks.dto.UtilisateurDTO;
import com.galaxytasks.dto.UtilisateurCreateDTO;
import com.galaxytasks.services.UtilisateurService;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*") // Pour permettre les requêtes depuis le frontend
public class UtilisateurRestController{

    @Autowired
    private UtilisateurService utilisateurService;

    // POST - Créer un nouvel utilisateur
    // api/user/create
    @PostMapping("/create")
    public ResponseEntity<UtilisateurCreateDTO> create(@RequestBody UtilisateurDTO utilisateur){
        try{
            UtilisateurCreateDTO created = utilisateurService.creerUtilisateur(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }

    //GET - Récupérer tous les utilisateurs
    // api/user/allUsers
    @GetMapping("/allUsers")
    public ResponseEntity<List<UtilisateurCreateDTO>> getAllUser(){
        List<UtilisateurCreateDTO> users = utilisateurService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Test de GET
    @GetMapping("/test")
    public String test() {
        return "ça marche !";
    }

    // DELETE - Supprimer un utilisateur
    // /api/user/delete/{idUtilisateur}
    @DeleteMapping("/delete/{idUtilisateur}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer idUtilisateur){
        if(utilisateurService.deleteUser(idUtilisateur)){
           return ResponseEntity.noContent().build();
        }
        else{
           return ResponseEntity.notFound().build();
        }
    }

    // PUT - Mettre à jour un utilisateur
    // /api/user/update/{idUtilisateur}
    @PutMapping("/update/{idUtilisateur}")
    public ResponseEntity<Optional<UtilisateurCreateDTO>> updateUser(@PathVariable Integer idUtilisateur, @RequestBody UtilisateurDTO utilisateurDTO){
        try{
            Optional<UtilisateurCreateDTO> user = utilisateurService.updateUser(idUtilisateur, utilisateurDTO);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Rechercher un utilisateur par son identifiant
    // /api/user/search/byId?id = ...
    @GetMapping("/search/byId")
    public ResponseEntity<Optional<UtilisateurCreateDTO>> searchById(@RequestParam Integer id){
        try{
            Optional<UtilisateurCreateDTO> user = utilisateurService.searchById(id);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Rechercher un utilisateur par son identifiant
    // /api/user/search/byName?name = ...
    @GetMapping("/search/byName")
    public ResponseEntity<Optional<UtilisateurCreateDTO>> searchByName(@RequestParam String name){
        try{
            Optional<UtilisateurCreateDTO> user = utilisateurService.searchByName(name);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Rechercher un utilisateur par son mail
    // /api/user/search/byEmail?email = ...
    @GetMapping("/search/byEmail")
    public ResponseEntity<Optional<UtilisateurCreateDTO>> searchByEmail(@RequestParam String email){
        try{
            Optional<UtilisateurCreateDTO> user = utilisateurService.searchByEmail(email);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
