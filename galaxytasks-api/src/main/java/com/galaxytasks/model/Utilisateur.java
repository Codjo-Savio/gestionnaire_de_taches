/*Savio AMOUSSOUVI */
/* Entité Utilisateur */
package com.galaxytasks.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // entité déjà definie dans MySql
@Table(name = "utilisateur")
@Data // générer automatiquement des getters et des setters
@NoArgsConstructor
@AllArgsConstructor

// Utilisation de Column pour spécifier les contraintes
public class Utilisateur{
    // entités
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Spécification  de l'auto-incrémentation
    @Column(name = "idUtilisateur", nullable = false)
    private Integer idUtilisateur;

    @Column(name = "nomUtilisateur", nullable = false, length = 100)
    private String nomUtilisateur;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    public enum Statut{
        UTILISATEUR("utilisateur"),
        SUPER_UTILISATEUR("super utilisateur");

        private final String libelle;
        Statut(String libelle) {
            this.libelle = libelle;
        }

        public String getLibelle() {
            return libelle;
        }
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Statut statut;

    @PrePersist
    public void prePersist(){
        this.statut = Statut.UTILISATEUR;
    }

    @Column(name = "motDePasse", nullable = false, length = 255)
    private String motDePasse;

    // afficher l'entité 
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + idUtilisateur +
                ", nom='" + nomUtilisateur + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

/*
 * CREATE TABLE utilisateur(
    idUtilisateur INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    nomUtilisateur VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE, -- UNIQUE pour unicité du champ email
    motDePasse VARCHAR(255) NOT NULL
) ENGINE=InnoDB;
 */