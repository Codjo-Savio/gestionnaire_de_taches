package com.galaxytasks.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.galaxytasks.model.Assignement;
import com.galaxytasks.model.AssignementId;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;

public interface AssignementRepository extends JpaRepository<Assignement, AssignementId>{
    List<Assignement> findByTache(Tache tache);

    List<Assignement> findByParticipant(Utilisateur participant);

    @Query("SELECT a FROM Assignement a WHERE a.id.idTache=?1 AND a.id.idParticipant=?2")
    Optional<Assignement> findByTacheIdTacheAndParticipantIdUtilisateur(Integer idTache, Integer idParticipant);

    boolean existsByTacheIdTacheAndParticipantIdUtilisateur(Integer idTache, Integer idParticipant);

    Void deleteByTacheIdTacheAndParticipantIdUtilisateur(Integer idTache, Integer idParticipant);
    
}
