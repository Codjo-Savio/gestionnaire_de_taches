package com.galaxytasks.repository;

import com.galaxytasks.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    
    // Recherche d’un utilisateur par email (utile pour la connexion)
    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur>findByidUtilisateur(Integer idUtilisateur);

    Optional<Utilisateur>findBynomUtilisateur(String nomUtilisateur);

    boolean existsByEmail(String email);
}