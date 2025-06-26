package com.galaxytasks.repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.galaxytasks.model.ParticipationProjet;
import com.galaxytasks.model.ParticipationProjetId;

public interface ParticipationProjetRepository extends JpaRepository<ParticipationProjet, ParticipationProjetId>{

    boolean existsByUtilisateurIdUtilisateur(Integer idUtilisateur);
    boolean existsByProjetIdProjet(Integer idProjet);
    boolean existsByProjetIdProjetAndUtilisateurIdUtilisateur(Integer idProjet, Integer idUtilisateur);

    List<ParticipationProjet> findByUtilisateurIdUtilisateur(Integer idUtilisateur);

    List<ParticipationProjet> findByProjetIdProjet(Integer idProjet);

    List<ParticipationProjet> findByDateAjout(LocalDateTime dateAjout);

    Void deleteByProjetIdProjetAndUtilisateurIdUtilisateur(Integer idProjet, Integer idUtilisateur);

    @Query("SELECT p FROM ParticipationProjet p WHERE p.id.idProjet=?1 AND p.id.idUtilisateur=?2")
    Optional<ParticipationProjet> findByProjetIdProjetAndUtilisateurIdUtilisateur(Integer idProjet, Integer idUtilisateur);

} 