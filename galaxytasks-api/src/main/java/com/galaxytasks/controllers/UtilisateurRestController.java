package com.galaxytasks.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxytasks.dto.UtilisateurDTO;
import com.galaxytasks.services.UtilisateurService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*") // Pour permettre les requêtes depuis le frontend
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRestController {
    private UtilisateurService utilisateurService;

    @PostMapping("/create")
    public UtilisateurDTO create(@RequestBody UtilisateurDTO utilisateur, @RequestBody String motDePasse){
        return utilisateurService.creerUtilisateur(utilisateur, motDePasse);
    }
}
