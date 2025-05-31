package com.galaxytasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetDTO {
    String intitule;
    String descriptionProjet;
    LocalDateTime dateCreation;
}
