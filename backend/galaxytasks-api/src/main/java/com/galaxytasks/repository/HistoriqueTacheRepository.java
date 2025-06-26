package com.galaxytasks.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxytasks.model.HistoriqueTache;
import com.galaxytasks.model.Tache;
import com.galaxytasks.model.Utilisateur;
import com.galaxytasks.model.HistoriqueTache.TypeAction;;

public interface HistoriqueTacheRepository extends JpaRepository<HistoriqueTache, Integer> {
    List<HistoriqueTache> findByArchivedFalse();

    List<HistoriqueTache> findByArchivedTrue();

    List<HistoriqueTache> findByDateAction(LocalDateTime dateAction);

    List<HistoriqueTache> findByActionAndArchivedFalse(TypeAction action);

    List<HistoriqueTache> findByTacheAndArchivedFalse(Tache tache);

    List<HistoriqueTache> findByParticipantAndArchivedFalse(Utilisateur user);
} 
