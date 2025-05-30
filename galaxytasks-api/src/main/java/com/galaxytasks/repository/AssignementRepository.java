package com.galaxytasks.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxytasks.model.Assignement;

public interface AssignementRepository extends JpaRepository<Assignement, Integer>{

    
}
