package com.galaxytasks.services;

//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galaxytasks.dto.UtilisateurDTO;
import com.galaxytasks.mappers.UtilisateurMapper;

import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository UtilisateurRepository;
    private final UtilisateurMapper mapper;

    // créer un nouvel utilisateur
    public UtilisateurDTO creerUtilisateur(UtilisateurDTO utilisateurDto){
        // vérifier si le email est unique ou pas
        if(UtilisateurRepository.existsByEmail(utilisateurDto.getEmail())){
            // s'il est déjà utilisé, une exception est levée
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        // Sinon, un utilisateur est créé et est mappé su le DTO
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(utilisateurDto.getNomUtilisateur());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse("temp"); // à faire

        return mapper.toDto(UtilisateurRepository.save(utilisateur));
    }

    /*public List<Utilisateur> allUsers(){
        return UtilisateurRepository.findAll();
    }*/
}
