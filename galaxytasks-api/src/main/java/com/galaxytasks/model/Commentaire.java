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
    private Integer idCommentaire;

    /*// clés étrangères
    @ManyToOne // plusieurs tâches peuvent appartenir à un même projet
    @JoinColumn(name = "idTache", nullable = false)
    private Tache tache;

    @ManyToOne // plusieurs tâches peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idParticipant", nullable = false)
    private Utilisateur participant;

    @Column(name = "ancienne_valeur", nullable = true)
    private String ancienne_valeur;

    @Column(name = "nouvelle_valeur", nullable = true)
    private String nouvelle_valeur;

    @Column(name = "date_creation", nullable = true)
    private LocalDateTime date_creation;
    */
    
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