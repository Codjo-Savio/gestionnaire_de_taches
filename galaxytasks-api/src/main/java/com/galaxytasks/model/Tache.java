package com.galaxytasks.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity // entité déjà définie dans MySql
@Table(name = "tache")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor
@AllArgsConstructor
// Utilisation de Column pour spécifier les contraintes
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTache;

    @Column(nullable = false, length = 100)
    private String titre;

    @Column(nullable = true)
    private String description_tache;
    
    @Column(nullable = true)
    private LocalDateTime date_echeance;// LocalDateTime pour la date et l'heure

    /* Type ENUM */
    public enum Priorite{
        UN(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5);

        private final int value;

        Priorite(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priorite priorite;

    @Column (nullable = false)
    private boolean est_termine = false;

    // clés étrangères
    @ManyToOne // plusieurs tâches peuvent appartenir à un même projet
    @JoinColumn(name = "idProjet", nullable = false)
    private Projet idProjet;

    @ManyToOne // plusieurs tâches peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idProprietaire", nullable = false)
    private Utilisateur proprietaire;
    // afficher l'entité
    @Override
    public String toString(){
        return "Tache{"+
        "Id tache = '" + idTache + '\''+
        ", titre='" + titre + '\'' + 
        ",Description='" + description_tache + '\'' +
        ",Echéance='" + date_echeance + '\'' +
        ",Priorité='" + priorite + '\'' +
        ",est_terminé='" + est_termine + '\'' +
        ",id Projet='" + idProjet + '\'' +
        ",id Propriétaire='" + proprietaire + '\''+
        '}';
    }
    
    // getters et setters

}


/*
 * CREATE TABLE tache(
    idTache INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    titre VARCHAR(100) NOT NULL,
    description_tache TEXT,
    date_echeance DATE,
    priorite ENUM('1','2','3','4','5') NOT NULL, -- Liste des valeurs autorisées
    est_termine BOOLEAN DEFAULT FALSE,
    idProjet INT NOT NULL,
    idProprietaire INT,
    FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur)
        ON DELETE SET NULL
        ON UPDATE CASCADE
)ENGINE=InnoDB;
 */