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
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository UtilisateurRepository;
    private UtilisateurMapper mapper;
    private PasswordEncoder passwordEncoder;

    // créer un nouvel utilisateur
    public UtilisateurDTO creerUtilisateur(UtilisateurDTO utilisateurDto, String motDePasse){
        // vérifier si le email est unique ou pas
        if(UtilisateurRepository.existsByEmail(utilisateurDto.getEmail())){
            // s'il est déjà utilisé, une exception est levée
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        // Sinon, un utilisateur est créé et est mappé su le DTO
        Utilisateur utilisateur = mapper.toEntity(utilisateurDto);
        utilisateur.setMotDePasse(passwordEncoder.encode(motDePasse));
        return mapper.toDto(UtilisateurRepository.save(utilisateur));
    }

    /*public List<Utilisateur> allUsers(){
        return UtilisateurRepository.findAll();
    }*/
}
