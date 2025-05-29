/*Savio AMOUSSOUVI */
/* Entité Utilisateur */
package com.galaxytasks.model;
import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur{
    // entités
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;

    @Column(nullable = false, length = 100)
    private String nomUtilisateur;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String motDePasse;

    // Constructeur
    public Utilisateur() {
        // Constructeur vide requis par JPA
    }
    public Utilisateur(Integer idUtilisateur, String nomUtilisateur, String email, String motDePasse){
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + idUtilisateur +
                ", nom='" + nomUtilisateur + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //getters, setters
    public void setIdUtilisateur(Integer idUtilisateur){
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdUtilisateur(){
        return idUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur){
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getNomUtilisateur(){
        return nomUtilisateur;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse(){
        return motDePasse;
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