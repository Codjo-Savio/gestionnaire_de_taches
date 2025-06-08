/* Savio AMOUSSOUVI */
/* Base de données améliorée - Gestionnaire de tâches collaboratif - GalaxyTasks */

/* Création de la base de donnée */
/* CREATE DATABASE GalaxyTasks CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE GalaxyTasks; */

-- Suppression des tables dans le bon ordre (contraintes de clés étrangères)
DROP TABLE IF EXISTS fichierJoint;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS tacheEtiquette;
DROP TABLE IF EXISTS etiquette;
DROP TABLE IF EXISTS commentaire;
DROP TABLE IF EXISTS historiqueTache;
DROP TABLE IF EXISTS assignement;
DROP TABLE IF EXISTS tache;
DROP TABLE IF EXISTS participationProjet;
DROP TABLE IF EXISTS projet;
DROP TABLE IF EXISTS utilisateur;

/* Création des tables MySQL améliorées */

-- Pour l'enregistrement des utilisateurs (amélioré)
CREATE TABLE utilisateur(
    idUtilisateur INT PRIMARY KEY AUTO_INCREMENT,
    nomUtilisateur VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    motDePasse VARCHAR(255) NOT NULL,
    avatar VARCHAR(255) DEFAULT NULL, -- URL ou chemin de l'avatar
    dateInscription DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    derniereConnexion DATETIME DEFAULT NULL,
    isActive BOOLEAN DEFAULT TRUE, -- Pour soft delete
    fuseau_horaire VARCHAR(50) DEFAULT 'Europe/Paris',
    preferences JSON DEFAULT NULL -- Préférences utilisateur (notifications, thème, etc.)
) ENGINE=InnoDB;

-- Pour la création des projets (amélioré)
CREATE TABLE projet(
    idProjet INT PRIMARY KEY AUTO_INCREMENT,
    intitule VARCHAR(100) NOT NULL,
    descriptionProjet TEXT,
    dateCreation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateModification DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    dateEcheance DATETIME DEFAULT NULL,
    couleur VARCHAR(7) DEFAULT '#3498db', -- Code couleur hexadécimal
    isTemplate BOOLEAN DEFAULT FALSE, -- Projet modèle
    isActive BOOLEAN DEFAULT TRUE, -- Pour soft delete
    progression DECIMAL(5,2) DEFAULT 0.00, -- Pourcentage d'avancement
    budget DECIMAL(10,2) DEFAULT NULL,
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur) 
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    -- Contrainte métier : échéance après création
    CONSTRAINT chk_projet_echeance CHECK (dateEcheance IS NULL OR dateEcheance >= dateCreation)
) ENGINE=InnoDB;

-- Pour la collaboration sur les projets (amélioré)
CREATE TABLE participationProjet(
    idProjet INT NOT NULL, 
    idUtilisateur INT NOT NULL,
    role ENUM('proprietaire', 'admin', 'membre', 'observateur') DEFAULT 'membre',
    dateAjout DATETIME DEFAULT CURRENT_TIMESTAMP,
    dateModification DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    isActive BOOLEAN DEFAULT TRUE, -- Pour retirer quelqu'un sans supprimer l'historique
    FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (idProjet, idUtilisateur)
) ENGINE=InnoDB;

-- Table des étiquettes (nouveau)
CREATE TABLE etiquette(
    idEtiquette INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    couleur VARCHAR(7) DEFAULT '#95a5a6',
    idProjet INT NOT NULL,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    UNIQUE KEY unique_etiquette_projet (nom, idProjet)
) ENGINE=InnoDB;

-- Pour la création des tâches (amélioré)
CREATE TABLE tache(
    idTache INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(100) NOT NULL,
    descriptionTache TEXT,
    dateCreation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateModification DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    dateEcheance DATETIME DEFAULT NULL,
    priorite ENUM('TRES_BASSE','BASSE','MOYENNE','URGENT','CRITIQUE') NOT NULL DEFAULT 'MOYENNE',
    estTermine ENUM ('TODO', 'EN_COURS', 'TERMINE', 'SUSPENDU', 'ANNULE') DEFAULT 'TODO',
    estimationHeures DECIMAL(6,2) DEFAULT NULL, -- Estimation en heures
    tempsReel DECIMAL(6,2) DEFAULT 0.00, -- Temps réellement passé
    ordre INT DEFAULT 0, -- Pour le tri personnalisé
    progression DECIMAL(5,2) DEFAULT 0.00, -- % d'avancement
    idProjet INT NOT NULL,
    idProprietaire INT NOT NULL,
    idTacheParent INT DEFAULT NULL, -- Pour les sous-tâches
    FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idProprietaire) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idTacheParent) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    -- Contraintes métier
    CONSTRAINT chk_tache_echeance CHECK (dateEcheance IS NULL OR dateEcheance >= dateCreation),
    CONSTRAINT chk_progression CHECK (progression >= 0 AND progression <= 100),
    CONSTRAINT chk_temps_positif CHECK (tempsReel >= 0 AND (estimationHeures IS NULL OR estimationHeures >= 0))
) ENGINE=InnoDB;

-- Table de liaison tâche-étiquette (nouveau)
CREATE TABLE tacheEtiquette(
    idTache INT NOT NULL,
    idEtiquette INT NOT NULL,
    dateAjout DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idEtiquette) REFERENCES etiquette(idEtiquette)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (idTache, idEtiquette)
) ENGINE=InnoDB;

-- Pour l'attribution des tâches (amélioré)
CREATE TABLE assignement(
    idTache INT NOT NULL,
    idParticipant INT NOT NULL,
    dateAssignement DATETIME DEFAULT CURRENT_TIMESTAMP,
    isActive BOOLEAN DEFAULT TRUE, -- Pour l'historique des assignements
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idParticipant) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (idTache, idParticipant)
) ENGINE=InnoDB;

-- Pour l'enregistrement de l'historique des tâches (amélioré)
CREATE TABLE historiqueTache(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idTache INT NOT NULL,
    idUtilisateur INT NOT NULL,
    action ENUM('créé', 'modifié', 'assigné', 'désassigné', 'terminé', 'réouvert', 
                'supprimé', 'priorité_modifiée', 'échéance_modifiée', 'progression_modifiée',
                'temps_ajouté', 'etiquette_ajoutée', 'etiquette_supprimée') NOT NULL,
    ancienneValeur TEXT DEFAULT NULL,
    nouvelleValeur TEXT DEFAULT NULL,
    detailsSupplementaires JSON DEFAULT NULL, -- Pour stocker des infos complexes
    dateAction DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Pour les commentaires (amélioré)
CREATE TABLE commentaire(
    idCommentaire INT PRIMARY KEY AUTO_INCREMENT,
    contenu TEXT NOT NULL,
    dateCommentaire DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateModification DATETIME DEFAULT NULL, -- Pour les commentaires édités
    isEdited BOOLEAN DEFAULT FALSE,
    idUtilisateur INT NOT NULL,
    idTache INT NOT NULL,
    idCommentaireParent INT DEFAULT NULL, -- Pour les réponses aux commentaires
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idCommentaireParent) REFERENCES commentaire(idCommentaire)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table des fichiers joints (nouveau)
CREATE TABLE fichierJoint(
    idFichier INT PRIMARY KEY AUTO_INCREMENT,
    nomOriginal VARCHAR(255) NOT NULL,
    nomStocke VARCHAR(255) NOT NULL UNIQUE, -- Nom unique pour éviter les conflits
    cheminFichier VARCHAR(500) NOT NULL,
    tailleFichier BIGINT NOT NULL, -- Taille en octets
    typeMime VARCHAR(100) NOT NULL,
    dateUpload DATETIME DEFAULT CURRENT_TIMESTAMP,
    idUtilisateur INT NOT NULL, -- Qui a uploadé le fichier
    idTache INT DEFAULT NULL, -- Attaché à une tâche
    idCommentaire INT DEFAULT NULL, -- Attaché à un commentaire
    FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idCommentaire) REFERENCES commentaire(idCommentaire)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    -- Au moins une des deux FK doit être renseignée
    CONSTRAINT chk_fichier_attachment CHECK (idTache IS NOT NULL OR idCommentaire IS NOT NULL)
) ENGINE=InnoDB;

-- Table des notifications (nouveau)
CREATE TABLE notification(
    idNotification INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    type ENUM('info', 'tache_assignée', 'tache_terminée', 'commentaire', 'echeance_proche', 
              'projet_modifié', 'mention') NOT NULL,
    isLue BOOLEAN DEFAULT FALSE,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    dateLecture DATETIME DEFAULT NULL,
    idDestinataire INT NOT NULL,
    idEmetteur INT DEFAULT NULL,
    idTache INT DEFAULT NULL, -- Notification liée à une tâche
    idProjet INT DEFAULT NULL, -- Notification liée à un projet
    lienAction VARCHAR(500) DEFAULT NULL, -- URL pour l'action
    FOREIGN KEY (idDestinataire) REFERENCES utilisateur(idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idEmetteur) REFERENCES utilisateur(idUtilisateur)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (idTache) REFERENCES tache(idTache)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idProjet) REFERENCES projet(idProjet)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Création des index pour optimiser les performances
-- Index sur les requêtes fréquentes
ALTER TABLE tache ADD INDEX idx_projet_statut (idProjet, estTermine);
ALTER TABLE tache ADD INDEX idx_echeance (dateEcheance);
ALTER TABLE tache ADD INDEX idx_priorite (priorite);
ALTER TABLE tache ADD INDEX idx_proprietaire (idProprietaire);

ALTER TABLE historiqueTache ADD INDEX idx_tache_date (idTache, dateAction);
ALTER TABLE historiqueTache ADD INDEX idx_utilisateur_date (idUtilisateur, dateAction);

ALTER TABLE commentaire ADD INDEX idx_tache_date (idTache, dateCommentaire);
ALTER TABLE commentaire ADD INDEX idx_utilisateur (idUtilisateur);

ALTER TABLE notification ADD INDEX idx_destinataire_non_lue (idDestinataire, isLue);
ALTER TABLE notification ADD INDEX idx_date_creation (dateCreation);

ALTER TABLE assignement ADD INDEX idx_participant (idParticipant);
ALTER TABLE assignement ADD INDEX idx_tache_active (idTache, isActive);

ALTER TABLE participationProjet ADD INDEX idx_utilisateur_actif (idUtilisateur, isActive);
ALTER TABLE participationProjet ADD INDEX idx_projet_role (idProjet, role);

ALTER TABLE fichierJoint ADD INDEX idx_tache (idTache);
ALTER TABLE fichierJoint ADD INDEX idx_utilisateur_date (idUtilisateur, dateUpload);

ALTER TABLE utilisateur ADD INDEX idx_email_actif (email, isActive);
ALTER TABLE utilisateur ADD INDEX idx_derniere_connexion (derniereConnexion);

ALTER TABLE projet ADD INDEX idx_proprietaire_actif (idProprietaire, isActive);
ALTER TABLE projet ADD INDEX idx_date_creation (dateCreation);

-- Vues utiles pour simplifier les requêtes courantes

-- Vue pour les tâches avec informations complètes
CREATE VIEW vue_taches_completes AS
SELECT 
    t.*,
    p.intitule as nomProjet,
    u.nomUtilisateur as nomProprietaire,
    COUNT(DISTINCT a.idParticipant) as nombreAssignes,
    COUNT(DISTINCT c.idCommentaire) as nombreCommentaires,
    COUNT(DISTINCT f.idFichier) as nombreFichiers
FROM tache t
JOIN projet p ON t.idProjet = p.idProjet
JOIN utilisateur u ON t.idProprietaire = u.idUtilisateur
LEFT JOIN assignement a ON t.idTache = a.idTache AND a.isActive = TRUE
LEFT JOIN commentaire c ON t.idTache = c.idTache
LEFT JOIN fichierJoint f ON t.idTache = f.idTache
WHERE p.isActive = TRUE
GROUP BY t.idTache;

-- Vue pour les projets avec statistiques
CREATE VIEW vue_projets_stats AS
SELECT 
    p.*,
    u.nomUtilisateur as nomProprietaire,
    COUNT(DISTINCT pp.idUtilisateur) as nombreParticipants,
    COUNT(DISTINCT t.idTache) as nombreTaches,
    COUNT(DISTINCT CASE WHEN t.estTermine = 'TERMINE' THEN t.idTache END) as tachesTerminees,
    COALESCE(AVG(CASE WHEN t.estTermine = 'TERMINE' THEN t.progression END), 0) as progressionMoyenne
FROM projet p
JOIN utilisateur u ON p.idProprietaire = u.idUtilisateur
LEFT JOIN participationProjet pp ON p.idProjet = pp.idProjet AND pp.isActive = TRUE
LEFT JOIN tache t ON p.idProjet = t.idProjet
WHERE p.isActive = TRUE
GROUP BY p.idProjet;

-- Triggers pour maintenir la cohérence des données

DELIMITER //

-- Trigger pour mettre à jour la progression du projet basée sur les tâches
CREATE TRIGGER update_projet_progression 
AFTER UPDATE ON tache
FOR EACH ROW
BEGIN
    DECLARE total_taches INT DEFAULT 0;
    DECLARE taches_terminees INT DEFAULT 0;
    DECLARE nouvelle_progression DECIMAL(5,2) DEFAULT 0;
    
    -- Compter les tâches du projet
    SELECT COUNT(*) INTO total_taches 
    FROM tache 
    WHERE idProjet = NEW.idProjet;
    
    -- Compter les tâches terminées
    SELECT COUNT(*) INTO taches_terminees 
    FROM tache 
    WHERE idProjet = NEW.idProjet AND estTermine = 'TERMINE';
    
    -- Calculer la progression
    IF total_taches > 0 THEN
        SET nouvelle_progression = (taches_terminees / total_taches) * 100;
    END IF;
    
    -- Mettre à jour le projet
    UPDATE projet 
    SET progression = nouvelle_progression 
    WHERE idProjet = NEW.idProjet;
END//

-- Trigger pour créer une notification lors de l'assignement d'une tâche
CREATE TRIGGER notification_assignement 
AFTER INSERT ON assignement
FOR EACH ROW
BEGIN
    DECLARE titre_tache VARCHAR(100);
    DECLARE nom_assigneur VARCHAR(100);
    
    -- Récupérer le titre de la tâche
    SELECT titre INTO titre_tache FROM tache WHERE idTache = NEW.idTache;
    
    -- Récupérer le nom du propriétaire de la tâche
    SELECT u.nomUtilisateur INTO nom_assigneur 
    FROM tache t 
    JOIN utilisateur u ON t.idProprietaire = u.idUtilisateur 
    WHERE t.idTache = NEW.idTache;
    
    -- Créer la notification
    INSERT INTO notification (titre, message, type, idDestinataire, idEmetteur, idTache)
    SELECT 
        CONCAT('Nouvelle tâche assignée: ', titre_tache),
        CONCAT('Vous avez été assigné(e) à la tâche "', titre_tache, '" par ', nom_assigneur),
        'tache_assignée',
        NEW.idParticipant,
        t.idProprietaire,
        NEW.idTache
    FROM tache t 
    WHERE t.idTache = NEW.idTache;
END//

DELIMITER ;

-- Procédures stockées utiles

DELIMITER //

-- Procédure pour archiver un projet (soft delete avec toutes ses données)
CREATE PROCEDURE ArchiveProjet(IN p_idProjet INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    -- Désactiver le projet
    UPDATE projet SET isActive = FALSE WHERE idProjet = p_idProjet;
    
    -- Désactiver les participations
    UPDATE participationProjet SET isActive = FALSE WHERE idProjet = p_idProjet;
    
    -- Marquer les assignements comme inactifs
    UPDATE assignement a
    JOIN tache t ON a.idTache = t.idTache
    SET a.isActive = FALSE
    WHERE t.idProjet = p_idProjet;
    
    COMMIT;
END//

-- Procédure pour obtenir les statistiques d'un utilisateur
CREATE PROCEDURE GetUserStats(
    IN p_idUtilisateur INT,
    OUT p_projetsActifs INT,
    OUT p_tachesAssignees INT,
    OUT p_tachesTerminees INT,
    OUT p_tachesEnRetard INT
)
BEGIN
    -- Projets actifs où l'utilisateur participe
    SELECT COUNT(DISTINCT pp.idProjet) INTO p_projetsActifs
    FROM participationProjet pp
    JOIN projet p ON pp.idProjet = p.idProjet
    WHERE pp.idUtilisateur = p_idUtilisateur 
    AND pp.isActive = TRUE 
    AND p.isActive = TRUE;
    
    -- Tâches assignées actives
    SELECT COUNT(DISTINCT a.idTache) INTO p_tachesAssignees
    FROM assignement a
    JOIN tache t ON a.idTache = t.idTache
    JOIN projet p ON t.idProjet = p.idProjet
    WHERE a.idParticipant = p_idUtilisateur 
    AND a.isActive = TRUE 
    AND p.isActive = TRUE
    AND t.estTermine NOT IN ('TERMINE', 'ANNULE');
    
    -- Tâches terminées
    SELECT COUNT(DISTINCT a.idTache) INTO p_tachesTerminees
    FROM assignement a
    JOIN tache t ON a.idTache = t.idTache
    WHERE a.idParticipant = p_idUtilisateur 
    AND a.isActive = TRUE
    AND t.estTermine = 'TERMINE';
    
    -- Tâches en retard
    SELECT COUNT(DISTINCT a.idTache) INTO p_tachesEnRetard
    FROM assignement a
    JOIN tache t ON a.idTache = t.idTache
    JOIN projet p ON t.idProjet = p.idProjet
    WHERE a.idParticipant = p_idUtilisateur 
    AND a.isActive = TRUE 
    AND p.isActive = TRUE
    AND t.dateEcheance < NOW()
    AND t.estTermine NOT IN ('TERMINE', 'ANNULE');
END//

DELIMITER ;