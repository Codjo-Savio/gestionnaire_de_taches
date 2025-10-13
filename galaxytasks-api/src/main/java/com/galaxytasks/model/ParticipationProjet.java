package com.galaxytasks.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "participationProjet")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationProjet {
    @EmbeddedId
    private ParticipationProjetId id;
    // clés étrangères
    @ManyToOne // plusieurs projets peuvent appartenir à un même utilisateur
    @MapsId("idProjet") // lie à la clé composite
    @JoinColumn(name = "idProjet", nullable = false)
    private Projet projet;

    @ManyToOne // plusieurs projets peuvent appartenir à un même utilisateur
    @MapsId("idUtilisateur") // lie à la clé composite
    @JoinColumn(name = "idUtilisateur", nullable = false)
    private Utilisateur utilisateur;

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
    private Role role;

    @Column(name = "dateAjout", nullable = true)
    private LocalDateTime dateAjout ;
    @PrePersist
    public void prePersist() {
        this.role = Role.MEMBRE;
        this.dateAjout = LocalDateTime.now();
    }


}
/*
 * -- Pour la collaboration sur les projets
CREATE TABLE participationProjet(
    idProjet INT NOT NULL, 
    idUtilisateur INT NOT NULL,
    role ENUM('proprietaire', 'admin', 'membre') DEFAULT 'membre',
    dateAjout DATE DEFAULT CURRENT_DATE,
     FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    PRIMARY KEY (idProjet, idUtilisateur)
)ENGINE=InnoDB;
 */