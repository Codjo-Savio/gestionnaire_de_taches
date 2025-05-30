package com.galaxytasks.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxytasks.model.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {

    
}