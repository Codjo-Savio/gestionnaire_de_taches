package com.galaxytasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurCreateDTO {
    private Integer idUtilisateur;
    private String nomUtilisateur;
    private String email;
}
