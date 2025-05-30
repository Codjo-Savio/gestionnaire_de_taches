package com.galaxytasks.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projet")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor
@AllArgsConstructor
// Utilisation de Column pour spécifier les contraintes
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //spécification de l'auto-incrémentation
    private Integer idProjet;

    @Column(nullable = false, length = 100)
    private String intitule;

    @Column(nullable = true)
    private String descriptionProjet;

    @Column(nullable = false)
    private LocalDateTime date_creation; // LocalDateTime pour la date et l'heure

    //est directement appelée avant l'insertion - pour initialiser la date par défaut
    // à la date actuelle
    @PrePersist
    public void prePersist() {
        this.date_creation = LocalDateTime.now();
    }

    // clés étrangère
    @ManyToOne // plusieurs projets peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idProprietaire", nullable = false)
    private Utilisateur idUtilisateur;

    // afficher l'entité
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + idProjet +
                ", intitulé='" + intitule + '\'' +
                ",Description='" + descriptionProjet + '\'' +
                ",Date de création='" + date_creation + '\'' +
                ",id Utilisateur='" + idUtilisateur + '\''+
                '}';
    }
}
/*
 * CREATE TABLE projet(
    idProjet INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    intitule VARCHAR(100) NOT NULL,
    descriptionProjet TEXT,
    date_creation DATE NOT NULL DEFAULT CURRENT_DATE,
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur) 
        ON DELETE CASCADE
        ON UPDATE CASCADE
)ENGINE=InnoDB;
 */