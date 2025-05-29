package com.galaxytasks.model;

import java.sql.Date;

import jakarta.persistence.*;
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjet;

    @Column(nullable = false, length = 100)
    private String intitule;

    @Column(nullable = true)
    private String descriptionProjet;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer idProprietaire;
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