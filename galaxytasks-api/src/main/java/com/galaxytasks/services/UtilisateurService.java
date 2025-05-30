/*package com.galaxytasks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galaxytasks.dto.UtilisateurDTO;

import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository UtilisateurRepository;

    // recupérer tous les utilisateurs
    public Utilisateur creerUtilisateur(Utilisateur utilisateur){
        return UtilisateurRepository.save(utilisateur);
    }

    /*public List<Utilisateur> allUsers(){
        return UtilisateurRepository.findAll();
    }*/
//}
