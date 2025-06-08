package com.galaxytasks.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxytasks.model.Projet;
import com.galaxytasks.model.Utilisateur;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    Optional<Projet> findByidProjet(Integer idProjet);

    List<Projet> findByProprietaire(Optional<Utilisateur> proprietaire);

    Optional<Projet> findByIntitule(String intitule);

    Optional<Projet> findByDateCreation(LocalDateTime dateCreation);

    boolean existsByIdProjet(Integer idProjet);

    boolean existsByIntitule(String intitule);
}

