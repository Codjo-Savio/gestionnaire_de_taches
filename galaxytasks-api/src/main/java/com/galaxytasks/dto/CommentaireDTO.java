package com.galaxytasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireDTO {
    private Integer idCommentaire;
    private String contenu;
    private Integer idUtilisateur;
    private Integer idTache;
}
