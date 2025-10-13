package com.galaxytasks.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.dto.ProjetCreateDTO;
import com.galaxytasks.dto.ProjetDTO;
import com.galaxytasks.mappers.ProjetMapper;
import com.galaxytasks.mappers.UtilisateurMapper;
import com.galaxytasks.repository.ProjetRepository;
import com.galaxytasks.repository.UtilisateurRepository;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetMapper mapper;
    @Autowired
    private UtilisateurMapper mapper2;

    // créer un nouveau projet
    public ProjetDTO create(ProjetCreateDTO projetCDto){
        // On récupère l'utilisateur par l'id qui aura été renseigné à la formulation de la requête
        Utilisateur proprietaire = utilisateurRepository.findByidUtilisateur(projetCDto.getIdProprietaire())
                         .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé")); // Si on le trouve pas 
                         // l'utilisateur, une exception est levée
        ProjetDTO projet = mapper.CDTOtoDto(projetCDto); // On mappe vers ProjetDTO
        projet.setProprietaire(mapper2.toCDto(mapper2.toDto(proprietaire))); // On mappe
        // l'utilisateur vers CDTO
        // projet.setIdProjet(null); // On laisse MySql gérer l'id
        // On enregistre le projet et on le mappe ver ProjetDTO
        return mapper.toDto(projetRepository.save(mapper.toEntity(projet))); 
    }

    // récupérer tous les projets
    public List<ProjetDTO> getAllProjects(){
        return projetRepository.findAll() // On récupère tous les projets
            .stream() // On les transforme en flux
            .map(mapper::toDto) // On les mappe sur ProjetDTO
            .collect(Collectors.toList()); // puis on les récupère dans une liste
    }

    // récupérer un projet par son identifiant
    public Optional<ProjetDTO> getById(Integer idProjet){
        return projetRepository.findById(idProjet)
        .map(mapper::toDto);
    }

     // récupérer un projet par son intitule
    public Optional<ProjetDTO> getByIntitule(String intitule){
        return projetRepository.findByIntitule(intitule)
        .map(mapper::toDto);
    }

    // récupérer un projet par son utilisateur
    public List<ProjetDTO> getByUserId(Integer idutilisateur){
        Optional<Utilisateur> user = utilisateurRepository.findByidUtilisateur(idutilisateur);
        return projetRepository.findByProprietaire(user)
        .stream() // On transforme les projets trouvés en flux
        .map(mapper::toDto) // On les mappe vers le DTO
        .collect(Collectors.toList()); // puis on les collecte dans la liste
    }

    // récupérer un projet par sa date de création
    public Optional<ProjetDTO> getByDate(LocalDateTime dateCreation){
        return projetRepository.findByDateCreation(dateCreation)
        .map(mapper::toDto);
    }

    // mise à jour d'un projet
    public Optional<ProjetDTO> updateProject(Integer idProjet, ProjetCreateDTO  project){
        return projetRepository.findByidProjet(idProjet)
        .map(projet ->{
            // mise à jour du projet
            // a partir des informations renseignées par l'utilisateur
            projet.setIntitule(project.getIntitule());
            projet.setDescriptionProjet(project.getDescriptionProjet());
            projet.setDateCreation(project.getDateCreation());
            return mapper.toDto(projetRepository.save(projet));
        });
    }

    // suppression d'un projet
    public boolean deleteById(Integer idProjet){
        // Si le projet existe par son identifiant,
        // On le supprime
        // et on renvoie true
        // sinon false est renvoyé
        if(projetRepository.existsById(idProjet)){
            projetRepository.deleteById(idProjet);
            return true;
        }
        else{
            return false;
        }
    }
}
