-- Insertion des données de test

-- 1. Table utilisateur (20 entrées)
INSERT INTO utilisateur (nomUtilisateur, email, motDePasse) VALUES
('Alice Martin', 'alice.martin@email.com', '$2y$10$abcdef123456789'),
('Bob Dupont', 'bob.dupont@email.com', '$2y$10$bcdefg234567890'),
('Claire Moreau', 'claire.moreau@email.com', '$2y$10$cdefgh345678901'),
('David Laurent', 'david.laurent@email.com', '$2y$10$defghi456789012'),
('Emma Bernard', 'emma.bernard@email.com', '$2y$10$efghij567890123'),
('Fabien Petit', 'fabien.petit@email.com', '$2y$10$fghijk678901234'),
('Gabrielle Roux', 'gabrielle.roux@email.com', '$2y$10$ghijkl789012345'),
('Hugo Vincent', 'hugo.vincent@email.com', '$2y$10$hijklm890123456'),
('Isabelle Fournier', 'isabelle.fournier@email.com', '$2y$10$ijklmn901234567'),
('Julien Morel', 'julien.morel@email.com', '$2y$10$jklmno012345678'),
('Karine Girard', 'karine.girard@email.com', '$2y$10$klmnop123456789'),
('Louis Andre', 'louis.andre@email.com', '$2y$10$lmnopq234567890'),
('Marie Lefebvre', 'marie.lefebvre@email.com', '$2y$10$mnopqr345678901'),
('Nicolas Simon', 'nicolas.simon@email.com', '$2y$10$nopqrs456789012'),
('Olivia Michel', 'olivia.michel@email.com', '$2y$10$opqrst567890123'),
('Pierre Garcia', 'pierre.garcia@email.com', '$2y$10$pqrstu678901234'),
('Quentin Blanc', 'quentin.blanc@email.com', '$2y$10$qrstuv789012345'),
('Rose Guerin', 'rose.guerin@email.com', '$2y$10$rstuvw890123456'),
('Sophie Muller', 'sophie.muller@email.com', '$2y$10$stuvwx901234567'),
('Thomas Boyer', 'thomas.boyer@email.com', '$2y$10$tuvwxy012345678');

-- 2. Table projet (20 entrées)
INSERT INTO projet (intitule, descriptionProjet, dateCreation, idProprietaire) VALUES
('Site Web E-commerce', 'Développement d\'un site de vente en ligne moderne', '2024-01-15 09:00:00', 1),
('Application Mobile TaskManager', 'App de gestion de tâches pour smartphones', '2024-01-20 14:30:00', 2),
('Refonte Interface Utilisateur', 'Modernisation de l\'interface du système existant', '2024-02-01 10:15:00', 3),
('Migration Base de Données', 'Migration vers une nouvelle architecture BDD', '2024-02-10 16:45:00', 4),
('Système de Reporting', 'Création d\'un module de rapports automatisés', '2024-02-15 11:20:00', 5),
('API RESTful Services', 'Développement d\'APIs pour intégrations tierces', '2024-02-20 13:00:00', 6),
('Formation Équipe DevOps', 'Programme de formation interne DevOps', '2024-03-01 08:30:00', 7),
('Optimisation Performance', 'Amélioration des performances système', '2024-03-05 15:10:00', 8),
('Sécurité Application', 'Audit et renforcement sécuritaire', '2024-03-10 12:45:00', 9),
('Documentation Technique', 'Rédaction complète de la documentation', '2024-03-15 09:20:00', 10),
('Tests Automatisés', 'Implémentation des tests unitaires et e2e', '2024-03-20 14:15:00', 11),
('Intégration Continue', 'Mise en place pipeline CI/CD', '2024-03-25 10:50:00', 12),
('Monitoring Système', 'Installation outils de surveillance', '2024-04-01 16:30:00', 13),
('Backup et Recovery', 'Stratégie de sauvegarde et récupération', '2024-04-05 11:40:00', 14),
('Interface Admin', 'Panneau d\'administration complet', '2024-04-10 13:25:00', 15),
('Analytics Dashboard', 'Tableau de bord analytique avancé', '2024-04-15 09:55:00', 16),
('Chat en Temps Réel', 'Système de messagerie instantanée', '2024-04-20 15:20:00', 17),
('Notifications Push', 'Service de notifications mobiles', '2024-04-25 12:10:00', 18),
('Géolocalisation Service', 'Intégration services de géolocalisation', '2024-05-01 14:45:00', 19),
('Machine Learning Module', 'Module d\'intelligence artificielle', '2024-05-05 10:35:00', 20);

-- 3. Table participationProjet (20 entrées)
INSERT INTO participationProjet (idProjet, idUtilisateur, role, dateAjout) VALUES
(1, 1, 'PROPRIETAIRE', '2024-01-15 09:00:00'),
(1, 2, 'ADMIN', '2024-01-16 10:30:00'),
(1, 3, 'MEMBRE', '2024-01-17 14:15:00'),
(2, 2, 'PROPRIETAIRE', '2024-01-20 14:30:00'),
(2, 4, 'ADMIN', '2024-01-21 09:45:00'),
(3, 3, 'PROPRIETAIRE', '2024-02-01 10:15:00'),
(3, 5, 'MEMBRE', '2024-02-02 11:20:00'),
(4, 4, 'PROPRIETAIRE', '2024-02-10 16:45:00'),
(4, 6, 'ADMIN', '2024-02-11 08:30:00'),
(5, 5, 'PROPRIETAIRE', '2024-02-15 11:20:00'),
(5, 7, 'MEMBRE', '2024-02-16 13:40:00'),
(6, 6, 'PROPRIETAIRE', '2024-02-20 13:00:00'),
(6, 8, 'MEMBRE', '2024-02-21 15:25:00'),
(7, 7, 'PROPRIETAIRE', '2024-03-01 08:30:00'),
(7, 9, 'ADMIN', '2024-03-02 10:15:00'),
(8, 8, 'PROPRIETAIRE', '2024-03-05 15:10:00'),
(8, 10, 'MEMBRE', '2024-03-06 12:45:00'),
(9, 9, 'PROPRIETAIRE', '2024-03-10 12:45:00'),
(9, 11, 'MEMBRE', '2024-03-11 14:20:00'),
(10, 10, 'PROPRIETAIRE', '2024-03-15 09:20:00');

-- 4. Table tache (20 entrées)
INSERT INTO tache (titre, descriptionTache, dateCreation, dateModification, dateEcheance, priorite, estTermine, idProjet, idProprietaire) VALUES
('Conception base de données', 'Modéliser la structure de données e-commerce', '2024-01-16 09:30:00', '2024-01-16 09:30:00', '2024-01-25 17:00:00', 'URGENT', 'TERMINE', 1, 1),
('Design interface login', 'Créer les maquettes de la page de connexion', '2024-01-17 14:20:00', '2024-01-18 10:15:00', '2024-01-30 17:00:00', 'MOYENNE', 'EN_COURS', 1, 2),
('Développement API user', 'Implémenter les endpoints utilisateurs', '2024-01-21 10:45:00', '2024-01-22 16:30:00', '2024-02-05 17:00:00', 'URGENT', 'EN_COURS', 2, 2),
('Tests unitaires login', 'Écrire les tests pour le module d\'authentification', '2024-01-22 11:15:00', '2024-01-22 11:15:00', '2024-02-01 17:00:00', 'MOYENNE', 'TODO', 2, 4),
('Refactoring code legacy', 'Moderniser l\'ancien code de l\'interface', '2024-02-02 13:45:00', '2024-02-03 09:20:00', '2024-02-20 17:00:00', 'BASSE', 'EN_COURS', 3, 3),
('Migration données users', 'Transférer les données utilisateurs vers nouvelle BDD', '2024-02-11 15:30:00', '2024-02-11 15:30:00', '2024-02-28 17:00:00', 'CRITIQUE', 'TODO', 4, 4),
('Création rapports ventes', 'Développer les rapports de statistiques de vente', '2024-02-16 12:10:00', '2024-02-17 14:25:00', '2024-03-01 17:00:00', 'MOYENNE', 'EN_COURS', 5, 5),
('Documentation API REST', 'Rédiger la documentation technique des APIs', '2024-02-21 16:40:00', '2024-02-21 16:40:00', '2024-03-10 17:00:00', 'BASSE', 'TODO', 6, 6),
('Formation Jenkins', 'Organiser session de formation sur Jenkins', '2024-03-02 09:15:00', '2024-03-02 09:15:00', '2024-03-15 14:00:00', 'MOYENNE', 'TERMINE', 7, 7),
('Optimisation requêtes SQL', 'Améliorer les performances des requêtes lentes', '2024-03-06 14:50:00', '2024-03-07 10:30:00', '2024-03-20 17:00:00', 'URGENT', 'EN_COURS', 8, 8),
('Audit sécurité OWASP', 'Effectuer un audit selon les standards OWASP', '2024-03-11 11:25:00', '2024-03-11 11:25:00', '2024-03-25 17:00:00', 'CRITIQUE', 'TODO', 9, 9),
('Rédaction guide utilisateur', 'Créer le manuel d\'utilisation pour les end-users', '2024-03-16 13:35:00', '2024-03-17 15:20:00', '2024-04-01 17:00:00', 'BASSE', 'EN_COURS', 10, 10),
('Implémentation tests E2E', 'Développer les tests end-to-end avec Cypress', '2024-03-21 08:45:00', '2024-03-21 08:45:00', '2024-04-05 17:00:00', 'MOYENNE', 'TODO', 11, 11),
('Configuration pipeline GitLab', 'Mettre en place le CI/CD sur GitLab', '2024-03-26 12:20:00', '2024-03-27 09:10:00', '2024-04-10 17:00:00', 'URGENT', 'EN_COURS', 12, 12),
('Installation Prometheus', 'Déployer et configurer Prometheus pour monitoring', '2024-04-02 15:55:00', '2024-04-02 15:55:00', '2024-04-15 17:00:00', 'MOYENNE', 'TODO', 13, 13),
('Script sauvegarde auto', 'Créer scripts automatisés de backup quotidien', '2024-04-06 10:30:00', '2024-04-07 14:15:00', '2024-04-20 17:00:00', 'URGENT', 'EN_COURS', 14, 14),
('Interface gestion users', 'Développer le CRUD utilisateurs pour admin', '2024-04-11 16:25:00', '2024-04-11 16:25:00', '2024-04-25 17:00:00', 'MOYENNE', 'TODO', 15, 15),
('Intégration Google Analytics', 'Connecter GA4 et créer dashboards personnalisés', '2024-04-16 11:40:00', '2024-04-17 13:30:00', '2024-05-01 17:00:00', 'BASSE', 'EN_COURS', 16, 16),
('WebSocket implementation', 'Implémenter WebSocket pour chat temps réel', '2024-04-21 14:15:00', '2024-04-21 14:15:00', '2024-05-05 17:00:00', 'URGENT', 'TODO', 17, 17),
('Service Firebase FCM', 'Intégrer Firebase Cloud Messaging', '2024-04-26 09:50:00', '2024-04-27 11:45:00', '2024-05-10 17:00:00', 'MOYENNE', 'EN_COURS', 18, 18);

-- 5. Table assignement (20 entrées)
INSERT INTO assignement (idTache, idParticipant) VALUES
(1, 1), (1, 2), (2, 2), (2, 3), (3, 2), (3, 4), (4, 4), (5, 3), (5, 5), (6, 4), 
(6, 6), (7, 5), (7, 7), (8, 6), (8, 8), (9, 7), (9, 9), (10, 8), (10, 10), (11, 9);

-- 6. Table historiqueTache (20 entrées)
INSERT INTO historiqueTache (idTache, idParticipant, action, ancienneValeur, nouvelleValeur, dateAction, archived) VALUES
(1, 1, 'CREE', NULL, 'Conception base de données', '2024-01-16 09:30:00', FALSE),
(1, 2, 'ASSIGNE', NULL, 'Assigné à Alice Martin', '2024-01-16 10:00:00', FALSE),
(2, 2, 'CREE', NULL, 'Design interface login', '2024-01-17 14:20:00', FALSE),
(2, 2, 'MODIFIE', 'Description initiale', 'Description mise à jour avec détails', '2024-01-18 10:15:00', FALSE),
(1, 1, 'TERMINE', 'EN_COURS', 'TERMINE', '2024-01-24 16:45:00', FALSE),
(3, 2, 'CREE', NULL, 'Développement API user', '2024-01-21 10:45:00', FALSE),
(3, 4, 'ASSIGNE', NULL, 'Assigné à David Laurent', '2024-01-21 11:00:00', FALSE),
(4, 4, 'CREE', NULL, 'Tests unitaires login', '2024-01-22 11:15:00', FALSE),
(5, 3, 'CREE', NULL, 'Refactoring code legacy', '2024-02-02 13:45:00', FALSE),
(5, 3, 'PRIORITE_MODIFIEE', 'MOYENNE', 'BASSE', '2024-02-03 09:20:00', FALSE),
(6, 4, 'CREE', NULL, 'Migration données users', '2024-02-11 15:30:00', FALSE),
(7, 5, 'CREE', NULL, 'Création rapports ventes', '2024-02-16 12:10:00', FALSE),
(7, 5, 'MODIFIE', 'Description courte', 'Description détaillée avec spécifications', '2024-02-17 14:25:00', FALSE),
(8, 6, 'CREE', NULL, 'Documentation API REST', '2024-02-21 16:40:00', FALSE),
(9, 7, 'CREE', NULL, 'Formation Jenkins', '2024-03-02 09:15:00', FALSE),
(9, 7, 'TERMINE', 'EN_COURS', 'TERMINE', '2024-03-14 17:30:00', FALSE),
(10, 8, 'CREE', NULL, 'Optimisation requêtes SQL', '2024-03-06 14:50:00', FALSE),
(10, 8, 'MODIFIE', 'Optimisation basique', 'Optimisation avancée avec indexation', '2024-03-07 10:30:00', FALSE),
(11, 9, 'CREE', NULL, 'Audit sécurité OWASP', '2024-03-11 11:25:00', FALSE),
(12, 10, 'CREE', NULL, 'Rédaction guide utilisateur', '2024-03-16 13:35:00', FALSE);

-- 7. Table commentaire (20 entrées)
INSERT INTO commentaire (contenu, dateCommentaire, idUtilisateur, idTache) VALUES
('Excellente modélisation de la base de données, très claire et bien structurée !', '2024-01-17 10:30:00', 2, 1),
('J\'ai quelques suggestions pour améliorer les relations entre tables', '2024-01-18 14:15:00', 3, 1),
('Le design de la page de login est moderne et intuitif', '2024-01-19 09:45:00', 1, 2),
('Pourrions-nous ajouter une option de connexion via réseaux sociaux ?', '2024-01-20 16:20:00', 3, 2),
('L\'API utilisateur fonctionne parfaitement, tous les endpoints répondent bien', '2024-01-23 11:30:00', 4, 3),
('J\'ai identifié un petit bug sur l\'endpoint de mise à jour du profil', '2024-01-24 13:45:00', 2, 3),
('Les tests unitaires couvrent bien tous les cas d\'usage principaux', '2024-01-25 15:20:00', 4, 4),
('Il faudrait ajouter des tests pour les cas d\'erreur aussi', '2024-01-26 10:10:00', 2, 4),
('Le refactoring améliore considérablement la lisibilité du code', '2024-02-04 12:35:00', 5, 5),
('Bonne idée d\'utiliser des design patterns plus modernes', '2024-02-05 14:50:00', 3, 5),
('La migration des données s\'est bien passée, aucune perte constatée', '2024-02-12 16:25:00', 6, 6),
('Les performances sont nettement meilleures avec la nouvelle structure', '2024-02-13 09:40:00', 4, 6),
('Les rapports de vente sont très détaillés et faciles à comprendre', '2024-02-18 11:15:00', 7, 7),
('Serait-il possible d\'ajouter des graphiques interactifs ?', '2024-02-19 13:30:00', 5, 7),
('La documentation de l\'API est complète et bien organisée', '2024-02-22 15:45:00', 8, 8),
('Les exemples de code sont très utiles pour l\'intégration', '2024-02-23 10:20:00', 6, 8),
('Formation très enrichissante, Jenkins n\'a plus de secrets !', '2024-03-14 16:00:00', 9, 9),
('Merci pour les tips sur l\'optimisation des pipelines', '2024-03-15 08:30:00', 7, 9),
('L\'optimisation des requêtes a réduit le temps de réponse de 40%', '2024-03-08 14:10:00', 10, 10),
('Impressionnant ! Les utilisateurs vont remarquer la différence', '2024-03-09 12:25:00', 8, 10);