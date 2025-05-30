package com.galaxytasks.repository;

import com.galaxytasks.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer>{

    
} 
