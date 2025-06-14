package com.galaxytasks.dto;

import com.galaxytasks.model.HistoriqueTache.TypeAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueTacheDTO {
    private Integer id;
    private Integer idTache;
    private Integer idParticipant;
    private TypeAction action;
    private String ancienneValeur;
    private String nouvelleValeur;
}
