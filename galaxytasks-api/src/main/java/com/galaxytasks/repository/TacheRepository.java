package com.galaxytasks.repository;

import com.galaxytasks.model.Projet;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.model.Tache.EstTermine;
import com.galaxytasks.model.Tache.Priorite;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer>{

    boolean existsById(Integer idTache);

    List<Tache> findByProjet(Projet projet);

    List<Tache> findByPriorite(Priorite priorite);

     List<Tache> findByEstTermine(EstTermine status);

    List<Tache> findByProprietaire(Utilisateur proprietaire);

    Optional<Tache> findByTitre(String titre);

    Optional<Tache> findByDateCreation(LocalDateTime dateCreation);

    Optional<Tache> findByDateEcheance(LocalDateTime dateEcheance);
} 
