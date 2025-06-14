package com.galaxytasks.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxytasks.model.Commentaire;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer>{
    List<Commentaire> findByUtilisateur(Utilisateur utilisateur);
    
    List<Commentaire> findByTache(Tache tache);
}
