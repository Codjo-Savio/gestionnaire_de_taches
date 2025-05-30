package com.galaxytasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assignement")
@Data // pour les getters et les setters
@NoArgsConstructor // constructeur vide
@AllArgsConstructor // constructeur
public class Assignement {
    // clés étrangères
    @ManyToOne // plusieurs tâches peuvent appartenir à un même projet
    @JoinColumn(name = "idTache", nullable = false)
    private Tache tache;

    @ManyToOne // plusieurs tâches peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idParticipant", nullable = false)
    private Utilisateur participant;
}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class AssignementId implements java.io.Serializable {
    private Integer idTache;
    private Integer idParticipant;
}

/*
 * CREATE TABLE assignement(
    idTache INT NOT NULL,
    idParticipant INT NOT NULL,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idParticipant) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (idTache, idParticipant)
)ENGINE=InnoDB;
 */
