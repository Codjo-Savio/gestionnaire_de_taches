package com.galaxytasks.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetCreateDTO {
    Integer idProjet;
    String intitule;
    String descriptionProjet;
    LocalDateTime dateCreation;
    Integer idProprietaire;
}
