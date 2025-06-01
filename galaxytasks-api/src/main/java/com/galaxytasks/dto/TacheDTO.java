package com.galaxytasks.dto;

import java.time.LocalDateTime;

import com.galaxytasks.model.Projet;
import com.galaxytasks.model.Tache.EstTermine;
import com.galaxytasks.model.Tache.Priorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacheDTO {
    private String titre;
    private String descriptionTache;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private LocalDateTime dateEcheance;
    private Priorite priorite;
    private EstTermine est_termine;
    private Projet projet;
}