package com.galaxytasks.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxytasks.model.Commentaire;;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer>{

    
}
