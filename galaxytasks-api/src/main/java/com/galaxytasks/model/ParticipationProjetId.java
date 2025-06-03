package com.galaxytasks.model;

import lombok.*;
import jakarta.persistence.Embeddable;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationProjetId implements java.io.Serializable {
    private Integer idProjet;
    private Integer idUtilisateur;
}
