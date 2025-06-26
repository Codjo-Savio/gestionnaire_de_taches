package com.galaxytasks.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galaxytasks.dto.UtilisateurDTO;
import com.galaxytasks.administration.AdminProperties;
import com.galaxytasks.dto.UtilisateurCreateDTO;
import com.galaxytasks.exceptions.EmailAlreadyUsedException;
import com.galaxytasks.mappers.UtilisateurMapper;

import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.model.Utilisateur.Statut;
import com.galaxytasks.repository.UtilisateurRepository;


import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UtilisateurService {
    private UtilisateurRepository UtilisateurRepository;
    private UtilisateurMapper mapper;
    private PasswordEncoder passwordEncoder;
    private AdminProperties adminProperties;

    @Autowired
    public UtilisateurService(UtilisateurRepository UtilisateurRepository,
                              UtilisateurMapper mapper,
                              PasswordEncoder passwordEncoder, AdminProperties adminProperties) {
        this.UtilisateurRepository = UtilisateurRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.adminProperties = adminProperties;
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

    // créer un super utilisateur s'il n'existe pas encore
    public void initSuperUtilisateurSiInexistant() {
        if (!UtilisateurRepository.existsByEmail(adminProperties.getEmail())) {
            Utilisateur admin = new Utilisateur();
            admin.setNomUtilisateur("SuperUser");
            admin.setEmail(adminProperties.getEmail());
            admin.setMotDePasse(passwordEncoder.encode(adminProperties.getPassword()));
            admin.setStatut(Statut.SUPER_UTILISATEUR);
            UtilisateurRepository.save(admin);
        }
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
        if(UtilisateurRepository.existsById(idUtilisateur)){ // On vérifie si l'utilisateur existe
            UtilisateurRepository.deleteById(idUtilisateur); // Si oui, on le supprime
            return true;
        } 
        else{
            return false; // sinon false est retourné
        }
    }

    // Mise à jour d'un utilisateur à partir de son identifiant
    public Optional<UtilisateurCreateDTO> updateUser(Integer idUtilisateur, UtilisateurDTO utilisateurDTO){
        return UtilisateurRepository.findById(idUtilisateur)
        .map(utilisateur -> {
            utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
            utilisateur.setEmail(utilisateurDTO.getEmail());
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse())); // On encode le mot de passe
            return mapper.toDto(UtilisateurRepository.save(utilisateur)); // On actualise l'utilisateur
        })
        .map(mapper::toCDto);
    }

    // Mise à jour d'un utilisateur à partir de son identifiant
    public Optional<UtilisateurCreateDTO> updateUserStatus(Integer idUtilisateur, UtilisateurDTO utilisateurDTO){
        return UtilisateurRepository.findById(idUtilisateur)
        .map(utilisateur -> {
            utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
            utilisateur.setEmail(utilisateurDTO.getEmail());
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse())); // On encode le mot de passe
            utilisateur.setStatut(utilisateurDTO.getStatut());
            return mapper.toDto(UtilisateurRepository.save(utilisateur)); // On actualise l'utilisateur
        })
        .map(mapper::toCDto);
    }

    // rechercher un utilisateur par son identifiant
    public Optional<UtilisateurCreateDTO> searchById(Integer idUtilisateur){
        return UtilisateurRepository.findById(idUtilisateur)
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


