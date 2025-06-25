package com.galaxytasks.dto;

import com.galaxytasks.model.ParticipationProjet.Role;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationProjetDTO {
    @NotNull
    private Integer idProjet;

    @NotNull
    private Integer idUtilisateur;

    @NotNull
    private Role role;
}
