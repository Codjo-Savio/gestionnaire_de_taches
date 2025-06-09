package com.galaxytasks.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxytasks.dto.ParticipationProjetDTO;
import com.galaxytasks.mappers.ParticipationProjetMapper;
import com.galaxytasks.model.ParticipationProjet;
import com.galaxytasks.model.ParticipationProjetId;
import com.galaxytasks.model.Projet;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.ParticipationProjetRepository;
import com.galaxytasks.repository.ProjetRepository;
import com.galaxytasks.repository.UtilisateurRepository;

@Service
public class ParticipationProjetService {
    private ParticipationProjetRepository participationProjetRepository;
    private UtilisateurRepository utilisateurRepository;
    private ProjetRepository projetRepository;
    private ParticipationProjetMapper mapper;

    @Autowired
    public ParticipationProjetService(ParticipationProjetRepository participationProjetRepository,
                                    UtilisateurRepository utilisateurRepository,
                                    ProjetRepository projetRepository,
                                    ParticipationProjetMapper mapper){
        this.participationProjetRepository = participationProjetRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.projetRepository = projetRepository;
        this.mapper = mapper;
    }
    // créer une nouvelle participation
    public ParticipationProjetDTO create(ParticipationProjetDTO participationProjetDTO){
        // On cherche le projet
        Projet project = projetRepository.findById(participationProjetDTO.getIdProjet())
        .orElseThrow(()-> new RuntimeException("Projet non trouvé") );

        // Puis l'utilisateur
        Utilisateur user = utilisateurRepository.findById(participationProjetDTO.getIdUtilisateur())
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        // On les met à jour
        ParticipationProjet created = mapper.toEntity(participationProjetDTO);

        // Créer la clé composite
        ParticipationProjetId id = new ParticipationProjetId(); 
        id.setIdProjet(participationProjetDTO.getIdProjet());
        id.setIdUtilisateur(participationProjetDTO.getIdUtilisateur());
        
        created.setId(id);
        created.setProjet(project);
        created.setUtilisateur(user);
        return mapper.toDto(participationProjetRepository.save(created));
    }


    // récupérer toutes les participations
    public List<ParticipationProjetDTO> getAllParticipations(){
        return participationProjetRepository.findAll()
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer les Participations par leur Id
    public Optional<ParticipationProjetDTO> getById(Integer idProjet, Integer idUtilisateur){
        return participationProjetRepository.findByProjetIdProjetAndUtilisateurIdUtilisateur(idProjet, idUtilisateur)
        .map(mapper::toDto);
    }

    // récupérer les participations par projet
    public List<ParticipationProjetDTO> getByProject(Integer idProjet){
        return participationProjetRepository.findByProjetIdProjet(idProjet)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer les participations par utilisateur
    public List<ParticipationProjetDTO> getByUser(Integer idUtilisateur){
        return participationProjetRepository.findByUtilisateurIdUtilisateur(idUtilisateur)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer les participations par date d'ajout
    public List<ParticipationProjetDTO> getByDateAjout(LocalDateTime dateAjout){
        return participationProjetRepository.findByDateAjout(dateAjout)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // Mettre à jour une participation
    public ParticipationProjetDTO updateById(Integer idProjet, Integer idUtilisateur, ParticipationProjetDTO participationProjetDTO){
        ParticipationProjet participation = participationProjetRepository.findByProjetIdProjetAndUtilisateurIdUtilisateur(idProjet,
                                            idUtilisateur)
        .orElseThrow(()-> new RuntimeException("Participation non trouvée"));

        participation.setRole(participationProjetDTO.getRole());

        return mapper.toDto(participationProjetRepository.save(participation));
    }

    // Supprimer une Participation
    public boolean deleteById(Integer idProjet, Integer idUtilisateur){
        ParticipationProjetId id = new ParticipationProjetId(idProjet, idUtilisateur);
        if(participationProjetRepository.existsById(id)){
            participationProjetRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
