/* Savio AMOUSSOUVI*/
/* Base de données - Gestionnaire de tâches collaboratif - GalaxyTasks*/

/*Création de la base de donnée*/
/*CREATE DATABASE GalaxyTasks CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE GalaxyTasks;*/
DROP TABLE IF EXISTS commentaire;
DROP TABLE IF EXISTS historiqueTache;
DROP TABLE IF EXISTS assignement;
DROP TABLE IF EXISTS tache;
DROP TABLE IF EXISTS participationProjet;
DROP TABLE IF EXISTS projet;
DROP TABLE IF EXISTS utilisateur;

/*Création des tables MySql*/

-- Contrainte NOT NULL pour valeur obligatoire
-- DEFAULT pour les valeurs par défaut
-- ON DELETE CASCADE suppression automatique des lignes enfant suite à la suppression de la ligne parent
-- ON DELETE SET NULL Mise à NULL si parent  supprimé
-- ON UPDATE CASCADE Mise à jour synchronisée

-- Pour l'enregistrement des utilisateurs
CREATE TABLE utilisateur(
    idUtilisateur INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    nomUtilisateur VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE, -- UNIQUE pour unicité du champ email
    motDePasse VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

-- Pour la création des projets
CREATE TABLE projet(
    idProjet INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    intitule VARCHAR(100) NOT NULL,
    descriptionProjet TEXT,
    dateCreation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur) 
        ON DELETE CASCADE
        ON UPDATE CASCADE
)ENGINE=InnoDB;

-- Pour la collaboration sur les projets
CREATE TABLE participationProjet(
    idProjet INT NOT NULL, 
    idUtilisateur INT NOT NULL,
    role ENUM('PROPRIETAIRE', 'ADMIN', 'MEMBRE') DEFAULT 'MEMBRE',
    dateAjout DATETIME DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (idProjet, idUtilisateur)
)ENGINE=InnoDB;

-- Pour la création des tâches
CREATE TABLE tache(
    idTache INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    titre VARCHAR(100) NOT NULL,
    descriptionTache TEXT,
    dateCreation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateModification DATETIME DEFAULT CURRENT_TIMESTAMP,
    dateEcheance DATETIME,
    priorite ENUM('TRES_BASSE','BASSE','MOYENNE','URGENT','CRITIQUE') NOT NULL, -- Liste des valeurs autorisées
    estTermine ENUM ('TODO', 'EN_COURS', 'TERMINE', 'SUSPENDU'),
    idProjet INT NOT NULL,
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)ENGINE=InnoDB;

-- Pour l'attribution des tâches
CREATE TABLE assignement(
    idTache INT NOT NULL,
    idParticipant INT NOT NULL,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idParticipant) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (idTache, idParticipant)
)ENGINE=InnoDB;

-- Pour l'enregistrement de l'historique des tâches
CREATE TABLE historiqueTache(
    id INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    idTache INT NOT NULL,
    idParticipant INT NOT NULL,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idParticipant) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    action ENUM('CREE', 'MODIFIE', 'ASSIGNE', 'DESASSIGNE', 'TERMINE', 'REOUVERT', 'SUPPRIMER', 'PRIORITE_MODIFIEE', 'ECHEANCE MODIFIEE'),
    ancienneValeur TEXT,
    nouvelleValeur TEXT,
    dateAction DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    archived BOOLEAN NOT NULL DEFAULT FALSE
)ENGINE=InnoDB;

-- Pour les commentaires
CREATE TABLE commentaire(
    idCommentaire INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT pour générer un identifiant unique à chaque ligne
    contenu TEXT,
    dateCommentaire DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idUtilisateur INT NOT NULL,
    idTache INT NOT NULL,
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idTache)  REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)ENGINE=InnoDB;