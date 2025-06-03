package com.galaxytasks.services;

import java.util.List;
import java.util.Optional;
//import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galaxytasks.dto.UtilisateurDTO;
import com.galaxytasks.dto.UtilisateurCreateDTO;
import com.galaxytasks.exceptions.EmailAlreadyUsedException;
import com.galaxytasks.mappers.UtilisateurMapper;

import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.UtilisateurRepository;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UtilisateurService {
    private UtilisateurRepository UtilisateurRepository;
    private UtilisateurMapper mapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository UtilisateurRepository,
                              UtilisateurMapper mapper,
                              PasswordEncoder passwordEncoder) {
        this.UtilisateurRepository = UtilisateurRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }


    // créer un nouvel utilisateur
    public UtilisateurCreateDTO creerUtilisateur(UtilisateurDTO utilisateurDto){
        // vérifier si le email est unique ou pas
       if(UtilisateurRepository.existsByEmail(utilisateurDto.getEmail())){
            // s'il est déjà utilisé, une exception est levée
            throw new EmailAlreadyUsedException("Email déjà utilisé");
        }
        System.out.println("Email reçu: [" + utilisateurDto.getEmail() + "]");
            System.out.println("Email existe déjà ? " + UtilisateurRepository.existsByEmail(utilisateurDto.getEmail()));
        // Sinon, un utilisateur est créé et est mappé su le DTO
        utilisateurDto.setIdUtilisateur(null); // Laisser MySQL générer l'ID
        Utilisateur utilisateur = mapper.toEntity(utilisateurDto);
        // encoder le mot de passe
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        // Mappage
        UtilisateurDTO user = mapper.toDto(UtilisateurRepository.save(utilisateur));
        return mapper.toCDto(user);
    }

    // récupérer tous les utilisateurs
    // Les retourne sous forme de liste
    public List<UtilisateurCreateDTO> getAllUsers(){
        return UtilisateurRepository.findAll()
        .stream()
        .map(mapper::toDto) // mapper d'entité à DTO
        .map(mapper::toCDto) // mapper de DTO à entité
        .collect(Collectors.toList()); // récupérer dans une liste
    }

    // Supprimer un utilisateur par son identifiant
    // retourne true si la suppression réussie
    // sinon retourne false
    public boolean deleteUser(Integer idUtilisateur){
        if(UtilisateurRepository.existsById(idUtilisateur)){
            UtilisateurRepository.deleteById(idUtilisateur);
            return true;
        }
        else{
            return false;
        }
    }

    // Mise à jour d'un utilisateur à partir de son identifiant
    public Optional<UtilisateurCreateDTO> updateUser(Integer idUtilisateur, UtilisateurDTO utilisateurDTO){
        return UtilisateurRepository.findById(idUtilisateur)
        .map(utilisateur -> {
            utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
            utilisateur.setEmail(utilisateurDTO.getEmail());
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
            return mapper.toDto(UtilisateurRepository.save(utilisateur));
        })
        .map(mapper::toCDto);
    }

    // rechercher un utilisateur par son identifiant
    public Optional<UtilisateurCreateDTO> searchById(Integer idUtilisateur){
        return UtilisateurRepository.findByidUtilisateur(idUtilisateur)
        .map(mapper::toDto)
        .map(mapper::toCDto);
    }

    // rechercher un utilisateur par son nom
    public Optional<UtilisateurCreateDTO> searchByName(String nomUtilisateur){
        return UtilisateurRepository.findBynomUtilisateur(nomUtilisateur)
        .map(mapper::toDto)
        .map(mapper::toCDto);
    }

    // rechercher un utilisateur par son email
    public Optional<UtilisateurCreateDTO> searchByEmail(String email){
        return UtilisateurRepository.findByEmail(email)
        .map(mapper::toDto)
        .map(mapper::toCDto);
    }

}


