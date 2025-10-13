package com.galaxytasks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationProjetId implements Serializable{
    private Integer idProjet;
    private Integer idUtilisateur;
}
