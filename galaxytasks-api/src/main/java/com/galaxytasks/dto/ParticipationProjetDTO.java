package com.galaxytasks.dto;

import com.galaxytasks.model.ParticipationProjet.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationProjetDTO {
    private Integer idProjet;
    private String intitule;
    private Integer idUtilisateur;
    private String nomUtilisateur;
    private Role role;
}
