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
    public UtilisateurDTO creerUtilisateur(UtilisateurDTO utilisateurDto){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(utilisateurDto.getNomUtilisateur());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse(null); // à hasher plus tard
        Utilisateur saved = UtilisateurRepository.save(utilisateur);
        return mapToDto(saved);
    }

    public List<Utilisateur> allUsers(){
        return UtilisateurRepository.findAll();
    }
}*/
