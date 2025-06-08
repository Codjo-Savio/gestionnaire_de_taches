package com.galaxytasks.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxytasks.dto.TacheDTO;
import com.galaxytasks.mappers.TacheMapper;
import com.galaxytasks.model.Projet;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.repository.ProjetRepository;
import com.galaxytasks.repository.TacheRepository;
import com.galaxytasks.repository.UtilisateurRepository;


@Service
public class TacheService {
    private TacheRepository tacheRepository;
    private ProjetRepository projetRepository;
    private UtilisateurRepository utilisateurRepository;
    private TacheMapper mapper;

    @Autowired
    public TacheService(TacheRepository tacheRepository, ProjetRepository projetRepository, UtilisateurRepository utilisateurRepository, TacheMapper mapper){
        this.tacheRepository = tacheRepository;
        this.projetRepository = projetRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.mapper = mapper;
    }

    // créer une nouvelle tache
    public TacheDTO create(TacheDTO tacheDTO){
        // On cherche le projet
        Projet project = projetRepository.findByidProjet(tacheDTO.getIdProjet())
        .orElseThrow(()-> new RuntimeException("Projet non trouvé"));

        // Puis l'utilisateur
        Utilisateur proprietaire = utilisateurRepository.findById(tacheDTO.getIdProprietaire())
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        Tache created = mapper.toEntity(tacheDTO);
        created.setProjet(project); // On met à jour le projet
        created.setProprietaire(proprietaire); // puis l'utilisateur
        return mapper.toDto(tacheRepository.save(created)); // et on enregistre
    }

    // récupérer toutes les tâches
    public List<TacheDTO> getAllTasks(){
        return tacheRepository.findAll()
        .stream() // On transforme les tâches reçues en flux
        .map(mapper::toDto) // On les mappe
        .collect(Collectors.toList()); // puis on les enregistre dans une liste
    }

    // récupérer une tâche par son identifiant
    public Optional<TacheDTO> getById(Integer idTache){
        return tacheRepository.findById(idTache)
        .map(mapper::toDto);
    }

    // récupérer une tâche par son intitulé
    public Optional<TacheDTO> getByTitle(String title){
        return tacheRepository.findByTitre(title)
        .map(mapper::toDto);
    }

    // récupérer une liste de tâches en fonction du projet
    public List<TacheDTO> getByProject(Integer idProjet){
        // On cherche le projet
        Projet project = projetRepository.findByidProjet(idProjet)
        .orElseThrow(()-> new RuntimeException("Projet non trouvé"));

        // A partir de là, on cherche les tâches correspondantes
        return tacheRepository.findByProjet(project)
        .stream() // Qu'on transforme en flux
        .map(mapper::toDto) // Qu'on mappe
        .collect(Collectors.toList()); // Puis qu'on stocke dans une liste
    }

    // récupérer une liste de tâches en fonction de l'utilisateur
    public List<TacheDTO> getByUser(Integer idUtilisateur){
        // On cherche l'utilisateur
        Utilisateur user = utilisateurRepository.findByidUtilisateur(idUtilisateur)
        .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));

        // A partir de là on cherche les tâches correspondantes
        return tacheRepository.findByProprietaire(user)
        .stream() // Qu'on transforme en flux
        .map(mapper::toDto) // Qu'on mappe
        .collect(Collectors.toList()); // puis q'on enregistre dans une liste
    }

    // récupérer une liste de tâches en fonction de la date de création
    // récupérer une liste de tâches en fonction du projet
    public List<TacheDTO> getByDateCreation(LocalDateTime dateCreation){
        return tacheRepository.findByDateCreation(dateCreation)
        .stream() // Qu'on transforme en flux
        .map(mapper::toDto) // Qu'on mappe
        .collect(Collectors.toList()); // Puis qu'on stocke dans une liste
    }

   // récupérer une liste de tâches en fonction de la date de création
    // récupérer une liste de tâches en fonction du projet
    public List<TacheDTO> getByDateEcheance(LocalDateTime dateEcheance){
        return tacheRepository.findByDateEcheance(dateEcheance)
        .stream() // Qu'on transforme en flux
        .map(mapper::toDto) // Qu'on mappe
        .collect(Collectors.toList()); // Puis qu'on stocke dans une liste
    }


    // Mise à jour d'une tâche à partir de son identifiant
    public TacheDTO updateById(Integer idTache, TacheDTO tacheDto){
        Tache task = tacheRepository.findById(idTache)
        .orElseThrow(()-> new RuntimeException("Tâche non trouvée"));

        task.setTitre(tacheDto.getTitre());
        task.setDescriptionTache(tacheDto.getDescriptionTache());
        task.setDateEcheance(tacheDto.getDateEcheance());
        task.setPriorite(tacheDto.getPriorite());
        task.setEstTermine(tacheDto.getEstTermine());

        return mapper.toDto(tacheRepository.save(task));

    }

    // supprimer une tâche à partir de son identifiant
    public boolean deleteTaskById(Integer idTache){
        if(tacheRepository.existsById(idTache)){
            tacheRepository.deleteById(idTache);
            return true;
        }
        else{
            return false;
        }
    }
}
