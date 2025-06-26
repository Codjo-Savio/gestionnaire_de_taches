package com.galaxytasks.dto;

import java.time.LocalDateTime;

import com.galaxytasks.model.Tache.EstTermine;
import com.galaxytasks.model.Tache.Priorite;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacheDTO {
    @NotNull
    private Integer idTache;

    private String titre;
    private String descriptionTache;

    @NotNull
    private LocalDateTime dateEcheance;

    @NotNull
    private Priorite priorite;

    @NotNull
    private EstTermine estTermine;

    @NotNull
    private Integer idProjet;

    @NotNull
    private Integer idProprietaire;
}
