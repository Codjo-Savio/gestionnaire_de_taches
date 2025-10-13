package com.galaxytasks.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projet")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor
@AllArgsConstructor
// Utilisation de Column pour spécifier les contraintes
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //spécification de l'auto-incrémentation
    @Column(name = "idProjet", nullable = false)
    private Integer idProjet;

    @Column(name = "intitule", nullable = false, length = 100)
    private String intitule;

    @Column(name = "descriptionProjet", nullable = true, columnDefinition = "TEXT")
    private String descriptionProjet;

    @Column(name = "dateCreation",nullable = false)
    private LocalDateTime dateCreation; // LocalDateTime pour la date et l'heure

    //est directement appelée avant l'insertion - pour initialiser la date par défaut
    // à la date actuelle
    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
    }

    // clés étrangères
    @ManyToOne // plusieurs projets peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idProprietaire", nullable = false)
    private Utilisateur proprietaire;

    // afficher l'entité
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + idProjet +
                ", intitulé='" + intitule + '\'' +
                ",Description='" + descriptionProjet + '\'' +
                ",Date de création='" + dateCreation + '\'' +
                ",id Utilisateur='" + proprietaire + '\''+
                '}';
    }
}
/*
 * CREATE TABLE projet(
    idProjet INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    intitule VARCHAR(100) NOT NULL,
    descriptionProjet TEXT,
    date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur) 
        ON DELETE CASCADE
        ON UPDATE CASCADE
)ENGINE=InnoDB;
 */