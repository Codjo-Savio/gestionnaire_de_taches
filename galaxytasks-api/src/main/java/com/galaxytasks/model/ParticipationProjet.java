package com.galaxytasks.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "particiationProjet")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationProjet {
    // clés étrangères
    @ManyToOne // plusieurs projets peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idProjet", nullable = false)
    private Projet idProjet;

    @ManyToOne // plusieurs projets peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idUtilisateur", nullable = false)
    private Utilisateur idUtilisateur;

    /* Type ENUM */
    public enum Role{
        PROPRIETAIRE("proprietaire"), 
        ADMIN("admin"), 
        MEMBRE("membre");

        private final String value;

        Role(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.MEMBRE;

    @Column(name = "date_ajout", nullable = true)
    private LocalDateTime date_ajout ;
    @PrePersist
    public void prePersist() {
        this.date_ajout = LocalDateTime.now();
    }


}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class ParticipationProjetId implements java.io.Serializable {
    private Long idProjet;
    private Long idUtilisateur;
}

/*
 * -- Pour la collaboration sur les projets
CREATE TABLE participationProjet(
    idProjet INT NOT NULL, 
    idUtilisateur INT NOT NULL,
    role ENUM('proprietaire', 'admin', 'membre') DEFAULT 'membre',
    date_ajout DATE DEFAULT CURRENT_DATE,
     FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    PRIMARY KEY (idProjet, idUtilisateur)
)ENGINE=InnoDB;
 */