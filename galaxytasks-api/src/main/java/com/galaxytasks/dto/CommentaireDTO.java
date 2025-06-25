package com.galaxytasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireDTO {
    @NotNull
    private Integer idCommentaire;

    @NotBlank
    private String contenu;

    @NotNull
    private Integer idUtilisateur;

    @NotNull
    private Integer idTache;
}
