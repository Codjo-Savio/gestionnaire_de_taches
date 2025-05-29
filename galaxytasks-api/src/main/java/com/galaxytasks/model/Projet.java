package com.galaxytasks.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjet;

    @Column(nullable = false, length = 100)
    private String intitule;

    @Column(nullable = true)
    private String descriptionProjet;

    @Column(nullable = false)
    private LocalDateTime date_creation = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "idProprietaire", nullable = false)
    private Utilisateur idUtilisateur;

    // constructeurs
    private Projet(){
        // constructeur vide pour JPA
    }

    private Projet(Integer idProjet, String intitule, String descriptionProjet, LocalDateTime date_creation, Utilisateur idUtilisateur){
        this.idProjet = idProjet;
        this.intitule = intitule;
        this.descriptionProjet = descriptionProjet;
        this.date_creation = date_creation;
        this.idUtilisateur = idUtilisateur;
    }

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

    // setters et getters
    public void setIdProjet(Integer idProjet){
        this.idProjet = idProjet;
    }

    public Integer getIdProjet(){
        return idProjet;
    }

    public void setIntitule(String intitule){
        this.intitule = intitule;
    }

    public String getIntitule(){
        return intitule;
    }

    public void setDescription(String descriptionProjet){
        this.descriptionProjet = descriptionProjet;
    }

    public String getDescription(){
        return descriptionProjet;
    }

    public void setDateCreation(LocalDateTime date_creation){
        this.date_creation = date_creation;
    }

    public LocalDateTime getDateCreation(){
        return date_creation;
    }

    public void setProprietaire(Utilisateur idUtilisateur){
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur getProprietaire(){
        return idUtilisateur;
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