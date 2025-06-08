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
    @Column(name = "idTache")
    private Integer idTache;

    @Column(name = "titre", nullable = false, length = 100)
    private String titre;

    @Column(name = "descriptionTache", nullable = true, columnDefinition = "TEXT")
    private String descriptionTache;

    @Column(name = "dateCreation", nullable = false)
    private LocalDateTime dateCreation;// LocalDateTime pour la date et l'heure
   
    @Column(name = "dateModification", nullable = true)
    private LocalDateTime dateModification;// LocalDateTime pour la date et l'heure
    
    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateModification = LocalDateTime.now();
    }
    
    @Column(name = "dateEcheance", nullable = true)
    private LocalDateTime dateEcheance;// LocalDateTime pour la date et l'heure

    /* Type ENUM */
    public enum Priorite{
        TRES_BASSE(1), 
        BASSE(2), 
        MOYENNE(3), 
        URGENT(4), 
        CRITIQUE(5);

        private final int value;

        Priorite(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "priorite", nullable = false)
    private Priorite priorite;

    public enum EstTermine {
        TODO("A faire"),
        EN_COURS("En cours"),
        TERMINE("Terminé"),
        SUSPENDU("Suspendu");

        private final String libelle;

        EstTermine(String libelle) {
            this.libelle = libelle;
        }

        public String getLibelle() {
            return libelle;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column (name = "estTermine", nullable = false)
    private EstTermine estTermine = EstTermine.TODO;

    // clés étrangères
    @ManyToOne // plusieurs tâches peuvent appartenir à un même projet
    @JoinColumn(name = "idProjet", nullable = false)
    private Projet projet;

    @ManyToOne // plusieurs tâches peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idProprietaire", nullable = false)
    private Utilisateur proprietaire;
    // afficher l'entité
    @Override
    public String toString(){
        return "Tache{"+
        "Id tache = '" + idTache + '\''+
        ", titre='" + titre + '\'' + 
        ",Description='" + descriptionTache + '\'' +
        ",Echéance='" + dateEcheance + '\'' +
        ",Priorité='" + priorite + '\'' +
        ",est_terminé='" + estTermine + '\'' +
        ",id Projet='" + projet + '\'' +
        ",id Propriétaire='" + proprietaire + '\''+
        '}';
    }
}


/*
 CREATE TABLE tache(
    idTache INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    titre VARCHAR(100) NOT NULL,
    description_tache TEXT,
    date_creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_modification DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_echeance DATETIME,
    priorite ENUM('1','2','3','4','5') NOT NULL, -- Liste des valeurs autorisées
    est_termine ENUM ('A faire', 'En cours', 'Terminé', 'Suspendu'),
    idProjet INT NOT NULL,
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)ENGINE=InnoDB;
*/
