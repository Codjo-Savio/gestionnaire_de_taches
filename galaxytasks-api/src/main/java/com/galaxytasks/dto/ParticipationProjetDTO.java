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
    private Integer idUtilisateur;
    private Role role;
}
