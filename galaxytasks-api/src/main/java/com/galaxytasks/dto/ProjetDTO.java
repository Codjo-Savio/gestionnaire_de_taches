package com.galaxytasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetDTO {
    @NotNull
    Integer idProjet;

    String intitule;
    String descriptionProjet;

    @NotNull
    LocalDateTime dateCreation;
    
    @NotNull
    UtilisateurCreateDTO proprietaire;
}
