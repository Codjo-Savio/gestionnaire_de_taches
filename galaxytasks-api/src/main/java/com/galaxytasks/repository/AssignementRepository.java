package com.galaxytasks.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxytasks.model.Assignement;
import com.galaxytasks.model.AssignementId;

public interface AssignementRepository extends JpaRepository<Assignement, AssignementId>{

    
}
