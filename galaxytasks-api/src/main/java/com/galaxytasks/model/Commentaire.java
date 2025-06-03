package com.galaxytasks.model;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "historique_tache")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor // constructeur vide
@AllArgsConstructor // constructeur
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCommentaire")
    private Integer idCommentaire;

    @Column(name = "contenu", nullable = true, columnDefinition = "TEXT")
    private String contenu;

    @Column(name = "date_commentaire", nullable = false)
    private LocalDateTime date_commentaire;

     // clés étrangères
    @ManyToOne // plusieurs tâches peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idUtilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne // plusieurs tâches peuvent appartenir à un même projet
    @JoinColumn(name = "idTache", nullable = false)
    private Tache tache;
}
/*
 * CREATE TABLE commentaire(
    idCommentaire INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    contenu TEXT,
    date_commentaire DATETIME NOT NULL,
    idUtilisateur INT NOT NULL,
    idTache INT NOT NULL,
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idTache)  REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)ENGINE=InnoDB;
 */