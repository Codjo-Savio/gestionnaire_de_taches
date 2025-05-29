package com.galaxytasks.model;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "tache")
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTache;

    @Column(nullable = false, length = 100)
    private String titre;

    @Column(nullable = true)
    private String description_tache;
    
    @Column(nullable = true)
    private LocalDate date_echeance;

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

    @ManyToOne
    @JoinColumn(name = "idProjet", nullable = false)
    private Projet idProjet;

    @ManyToOne
    @JoinColumn(name = "idProprietaire", nullable = false)
    private Utilisateur proprietaire;

    // constructeurs
    public Tache(){
        // constructeur vide pour JPA
    }

    public Tache(Integer idTache, String titre, String description_tache, LocalDate date_echeance, Priorite priorite, boolean est_termine, Projet idProjet, Utilisateur proprietaire){
        this.idTache = idTache;
        this.titre = titre;
        this.description_tache = description_tache;
        this.date_echeance = date_echeance;
        this.priorite = priorite;
        this.est_termine = est_termine;
        this.idProjet = idProjet;
        this.proprietaire = proprietaire;
    }
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