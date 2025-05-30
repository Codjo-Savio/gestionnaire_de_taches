package com.galaxytasks.model;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "historique_tache")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor // constructeur vide
@AllArgsConstructor // constructeur
public class HistoriqueTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // clés étrangères
    @ManyToOne // plusieurs tâches peuvent appartenir à un même projet
    @JoinColumn(name = "idTache", nullable = false)
    private Tache tache;

    @ManyToOne // plusieurs tâches peuvent appartenir à un même utilisateur
    @JoinColumn(name = "idParticipant", nullable = false)
    private Utilisateur participant;

    // enum pour les types d'action
    public enum TypeAction {
        CREE("créé"),
        MODIFIE("modifié"),
        ASSIGNE("assigné"),
        DESASSIGNE("désassigné"),
        TERMINE("terminé"),
        ROUVERT("rouvert"),
        SUPPRIME("supprimé"),
        PRIORITE_MODIFIEE("priorité modifiée"),
        ECHEANCE_MODIFIEE("échéance modifiée");

        private final String libelle;

        TypeAction(String libelle) {
            this.libelle = libelle;
        }

        public String getLibelle() {
            return libelle;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = true)
    private TypeAction action;

    @Column(name = "ancienne_valeur", nullable = true, columnDefinition = "TEXT")
    private String ancienne_valeur;

    @Column(name = "nouvelle_valeur", nullable = true, columnDefinition = "TEXT")
    private String nouvelle_valeur;

    @Column(name = "date_action", nullable = true)
    private LocalDateTime date_action;

    public void prePersist() {
        this.date_action = LocalDateTime.now();
    }

}
 /*
     * CREATE TABLE historique_tache(
    id INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    idTache INT NOT NULL,
    idParticipant INT NOT NULL,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idParticipant) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    action VARCHAR(50), -- 'créé', 'modifié', 'assigné', etc.
    ancienne_valeur TEXT,
    nouvelle_valeur TEXT,
    date_action DATETIME
)ENGINE=InnoDB;
     */