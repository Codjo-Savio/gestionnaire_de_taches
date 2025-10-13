package com.galaxytasks.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxytasks.dto.HistoriqueTacheDTO;
import com.galaxytasks.mappers.HistoriqueTacheMapper;
import com.galaxytasks.model.HistoriqueTache;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.model.HistoriqueTache.TypeAction;
import com.galaxytasks.repository.HistoriqueTacheRepository;
import com.galaxytasks.repository.TacheRepository;
import com.galaxytasks.repository.UtilisateurRepository;


@Service
public class HistoriqueTacheService {
    @Autowired
    private HistoriqueTacheRepository historiqueTacheRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private HistoriqueTacheMapper mapper;


    // créer un nouvel historique
    public HistoriqueTacheDTO create(HistoriqueTacheDTO historiqueTacheDTO){
        // On cherche l'historique
        HistoriqueTache historique = mapper.toEntity(historiqueTacheDTO);

        // On cherche la tâche
        Tache tache = tacheRepository.findById(historiqueTacheDTO.getIdTache())
        .orElseThrow(()-> new RuntimeException("Tache non trouvée"));

        // On cherche l'utilisateur
        Utilisateur user = utilisateurRepository.findById(historiqueTacheDTO.getIdParticipant())
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        // On met à jour la tâche et l'utilisateur
        historique.setTache(tache);
        historique.setParticipant(user);

        // Puis on sauvegarde la nouvelle tâche crée
        return mapper.toDto(historiqueTacheRepository.save(historique));
    }

    // récupérer l'historique des tâches
    public List<HistoriqueTacheDTO> getAllHistoryNotArchived(){
        return historiqueTacheRepository.findByArchivedFalse() // On récupère chaqun des historiques
        .stream() // On les transforme en flux
        .map(mapper::toDto) // On les mappe
        .collect(Collectors.toList()); // Puis on les enregistre dans une liste qui sera retournée
    }

    // récupérer l'historique par tâche
    public List<HistoriqueTacheDTO> getByTacheNotArchived(Integer idTache){
        // On cherche la tâche
        Tache tache = tacheRepository.findById(idTache)
        .orElseThrow(()-> new RuntimeException("Tâche non trouvée"));

        // On retourne l'historique stockée dans une liste
        return historiqueTacheRepository.findByTacheAndArchivedFalse(tache)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer l'historique par participant
    public List<HistoriqueTacheDTO> getByParticipantNotArchived(Integer idParticipant){
        // On cherche la tâche
        Utilisateur participant = utilisateurRepository.findByidUtilisateur(idParticipant)
        .orElseThrow(()-> new RuntimeException("Participant non trouvé"));

        // On retourne l'historique stockée dans une liste
        return historiqueTacheRepository.findByParticipantAndArchivedFalse(participant)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // récupérer l'historique par l'action effectuée
    public List<HistoriqueTacheDTO> getByActionNotArchived(TypeAction action){
        // On retourne l'historique stockée dans une liste
        return historiqueTacheRepository.findByActionAndArchivedFalse(action)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }

    // archiver un historique
    public HistoriqueTacheDTO archiverById(Integer id){
        HistoriqueTache history = historiqueTacheRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Historique non trouvé"));

        history.setArchived(true);
        return mapper.toDto(historiqueTacheRepository.save(history));
    }

    // restaurer l'un historique archivé
    public HistoriqueTacheDTO restaurerById(Integer id){
        HistoriqueTache history = historiqueTacheRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Historique non trouvé"));

        history.setArchived(false);
        return mapper.toDto(historiqueTacheRepository.save(history));
    }

    // récupérer tous les historiques archivés
    public List<HistoriqueTacheDTO> getAllArchived(){
        return historiqueTacheRepository.findByArchivedTrue()
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }
}