package com.galaxytasks.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurCreateDTO {
    private Integer idUtilisateur;
    private String nomUtilisateur;
    private String email;
}
