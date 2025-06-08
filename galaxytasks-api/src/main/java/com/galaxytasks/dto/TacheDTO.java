package com.galaxytasks.dto;

import java.time.LocalDateTime;

import com.galaxytasks.model.Tache.EstTermine;
import com.galaxytasks.model.Tache.Priorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacheDTO {
    private Integer idTache;
    private String titre;
    private String descriptionTache;
    private LocalDateTime dateEcheance;
    private Priorite priorite;
    private EstTermine estTermine;
    private Integer idProjet;
    private Integer idProprietaire;
}
