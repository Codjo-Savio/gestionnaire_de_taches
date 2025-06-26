package com.galaxytasks.dto;

import com.galaxytasks.model.HistoriqueTache.TypeAction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueTacheDTO {
    @NotNull
    private Integer id;

    @NotNull
    private Integer idTache;

    @NotNull
    private Integer idParticipant;
    private TypeAction action;
    private String ancienneValeur;

    @NotBlank
    private String nouvelleValeur;
}
