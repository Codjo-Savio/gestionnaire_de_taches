package com.galaxytasks.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxytasks.dto.AssignementDTO;
import com.galaxytasks.mappers.AssignementMapper;
import com.galaxytasks.model.Assignement;
import com.galaxytasks.model.AssignementId;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.AssignementRepository;
import com.galaxytasks.repository.TacheRepository;
import com.galaxytasks.repository.UtilisateurRepository;

@Service
public class AssignementService {
    private AssignementRepository assignementRepository;
    private TacheRepository tacheRepository;
    private UtilisateurRepository utilisateurRepository;
    private AssignementMapper mapper;

    @Autowired // Instanciation automatiique
    public AssignementService(AssignementRepository assignementRepository,
                            TacheRepository tacheRepository,
                            UtilisateurRepository utilisateurRepository,
                            AssignementMapper mapper){
        this.assignementRepository = assignementRepository;
        this.tacheRepository = tacheRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.mapper = mapper;
    }

    // créer un nouvel assignement
    public AssignementDTO create(AssignementDTO assignementDTO){
        // On cherche la tâche
        Tache tache = tacheRepository.findById(assignementDTO.getIdTache())
        .orElseThrow(()-> new RuntimeException("Tâche non trouvée"));

        // On cherche le participant
        Utilisateur participant = utilisateurRepository.findById(assignementDTO.getIdParticipant())
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        // On définit l'identifiant
        AssignementId id = new AssignementId();
        id.setIdTache(assignementDTO.getIdTache());
        id.setIdParticipant(assignementDTO.getIdParticipant());

        // On met à jour les valeurs
        Assignement created =  mapper.toEntity(assignementDTO);
        created.setId(id);
        created.setTache(tache);
        created.setParticipant(participant);

        return mapper.toDto(assignementRepository.save(created));
    }

    // récupérer tous les assignement
    public List<AssignementDTO> getAllAssignement(){
        return assignementRepository.findAll()
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer par id
    public Optional<AssignementDTO> getById(Integer idTache, Integer idParticipant){
        return assignementRepository.findByTacheIdTacheAndParticipantIdUtilisateur(idTache, idParticipant)
        .map(mapper::toDto);
    }

    // récupérer par tâche
    public List<AssignementDTO> getByTache(Integer idTache){
        // On cherche la tâche
        Tache tache = tacheRepository.findById(idTache)
        .orElseThrow(()-> new RuntimeException("Tâche non trouvée"));

        return assignementRepository.findByTache(tache)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer par participant
    public List<AssignementDTO> getByParticipant(Integer idParticipant){
        // On cherche la tâche
        Utilisateur utilisateur = utilisateurRepository.findById(idParticipant)
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        return assignementRepository.findByParticipant(utilisateur)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // Mettre à jour un assignement
    public AssignementDTO updateById(Integer idTache, Integer idParticipant, AssignementDTO assignementDTO){
        // On cherche la tâche
        Tache tache = tacheRepository.findById(assignementDTO.getIdTache())
        .orElseThrow(()-> new RuntimeException("Tâche non trouvée"));

        // On cherche le participant
        Utilisateur participant = utilisateurRepository.findById(assignementDTO.getIdParticipant())
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        // On définit l'identifiant
        AssignementId id = new AssignementId();
        id.setIdTache(assignementDTO.getIdTache());
        id.setIdParticipant(assignementDTO.getIdParticipant());

        Assignement assignement = mapper.toEntity(assignementDTO);
        // On met à jour les valeurs
        assignement.setId(id);
        assignement.setTache(tache);
        assignement.setParticipant(participant);

        return mapper.toDto(assignementRepository.save(assignement));
    }

    // Supprimer un assignement
    public boolean deleteById(Integer idTache, Integer idParticipant){
        AssignementId id = new AssignementId(idTache, idParticipant);
        if(assignementRepository.existsById(id)){
            assignementRepository.deleteById(id);;
            return true;
        }
        else{
            return false;
        }
    }
}
