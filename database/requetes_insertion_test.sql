-- Données de test pour GalaxyTasks
-- 20 entrées par table avec des données réalistes

-- 1. Table utilisateur (20 utilisateurs)
INSERT INTO utilisateur (nomUtilisateur, email, motDePasse) VALUES
('Alice Martin', 'alice.martin@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Bob Dupont', 'bob.dupont@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Claire Rousseau', 'claire.rousseau@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('David Moreau', 'david.moreau@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Emma Lambert', 'emma.lambert@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('François Simon', 'francois.simon@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Gabrielle Petit', 'gabrielle.petit@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Henri Dubois', 'henri.dubois@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Isabelle Leroy', 'isabelle.leroy@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Julien Roux', 'julien.roux@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Karine Blanc', 'karine.blanc@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Lucas Garnier', 'lucas.garnier@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Marie Fournier', 'marie.fournier@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Nicolas Chevalier', 'nicolas.chevalier@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Océane Girard', 'oceane.girard@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Pierre André', 'pierre.andre@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Quentin Bernard', 'quentin.bernard@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Rachelle Thomas', 'rachelle.thomas@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Sébastien Bonnet', 'sebastien.bonnet@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
('Tiffany Mercier', 'tiffany.mercier@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi');

-- 2. Table projet (20 projets)
INSERT INTO projet (intitule, descriptionProjet, dateCreation, idProprietaire) VALUES
('Site Web E-commerce', 'Développement d\'un site de vente en ligne pour une boutique de vêtements', '2024-01-15 09:00:00', 1),
('Application Mobile Fitness', 'App de suivi d\'entraînement et nutrition', '2024-01-20 14:30:00', 2),
('Système CRM', 'Gestion de la relation client pour une PME', '2024-02-01 08:15:00', 3),
('Plateforme E-learning', 'Site de formation en ligne pour étudiants', '2024-02-10 11:45:00', 4),
('Jeu Mobile Puzzle', 'Jeu de réflexion pour smartphones', '2024-02-15 16:20:00', 5),
('Dashboard Analytics', 'Tableau de bord pour visualisation de données', '2024-03-01 10:30:00', 6),
('Chatbot IA', 'Assistant virtuel pour service client', '2024-03-05 13:15:00', 7),
('Système Réservation', 'Plateforme de réservation pour restaurants', '2024-03-12 15:45:00', 8),
('Blog Personnel', 'Site de blog avec CMS personnalisé', '2024-03-18 09:30:00', 9),
('App Météo', 'Application météorologique avec géolocalisation', '2024-03-25 12:00:00', 10),
('Plateforme Streaming', 'Service de streaming vidéo', '2024-04-02 14:15:00', 11),
('Système Inventaire', 'Gestion de stock pour entrepôt', '2024-04-08 08:45:00', 12),
('Réseau Social Local', 'Plateforme communautaire pour quartier', '2024-04-15 11:30:00', 13),
('API REST Banking', 'API pour services bancaires', '2024-04-22 16:00:00', 14),
('Marketplace Artisans', 'Plateforme pour artisans locaux', '2024-05-01 10:15:00', 15),
('App Covoiturage', 'Application de partage de trajets', '2024-05-08 13:45:00', 16),
('Portfolio Designer', 'Site vitrine pour designer graphique', '2024-05-15 09:00:00', 17),
('Système Paie RH', 'Logiciel de gestion de paie', '2024-05-22 14:30:00', 18),
('App Méditation', 'Application de relaxation et méditation', '2024-05-29 11:15:00', 19),
('Plateforme Freelance', 'Site de mise en relation freelances-clients', '2024-06-05 15:30:00', 20);

-- 3. Table participationProjet (20 participations)
INSERT INTO participationProjet (idProjet, idUtilisateur, role, dateAjout) VALUES
(1, 1, 'PROPRIETAIRE', '2024-01-15 09:00:00'),
(1, 2, 'ADMIN', '2024-01-16 10:30:00'),
(1, 3, 'MEMBRE', '2024-01-17 14:15:00'),
(2, 2, 'PROPRIETAIRE', '2024-01-20 14:30:00'),
(2, 4, 'MEMBRE', '2024-01-21 09:45:00'),
(3, 3, 'PROPRIETAIRE', '2024-02-01 08:15:00'),
(3, 5, 'ADMIN', '2024-02-02 11:30:00'),
(4, 4, 'PROPRIETAIRE', '2024-02-10 11:45:00'),
(4, 6, 'MEMBRE', '2024-02-11 16:20:00'),
(5, 5, 'PROPRIETAIRE', '2024-02-15 16:20:00'),
(6, 6, 'PROPRIETAIRE', '2024-03-01 10:30:00'),
(6, 7, 'MEMBRE', '2024-03-02 13:45:00'),
(7, 7, 'PROPRIETAIRE', '2024-03-05 13:15:00'),
(8, 8, 'PROPRIETAIRE', '2024-03-12 15:45:00'),
(8, 9, 'ADMIN', '2024-03-13 10:00:00'),
(9, 9, 'PROPRIETAIRE', '2024-03-18 09:30:00'),
(10, 10, 'PROPRIETAIRE', '2024-03-25 12:00:00'),
(11, 11, 'PROPRIETAIRE', '2024-04-02 14:15:00'),
(12, 12, 'PROPRIETAIRE', '2024-04-08 08:45:00'),
(13, 13, 'PROPRIETAIRE', '2024-04-15 11:30:00');

-- 4. Table tache (20 tâches)
INSERT INTO tache (titre, descriptionTache, dateCreation, dateModification, dateEcheance, priorite, estTermine, idProjet, idProprietaire) VALUES
('Conception interface utilisateur', 'Créer les maquettes et wireframes pour l\'interface', '2024-01-16 10:00:00', '2024-01-16 10:00:00', '2024-01-25 18:00:00', 'MOYENNE', 'TERMINE', 1, 1),
('Développement backend API', 'Implémenter les endpoints REST pour l\'API', '2024-01-17 09:30:00', '2024-01-20 14:15:00', '2024-02-15 17:00:00', 'URGENT', 'EN_COURS', 1, 2),
('Tests unitaires', 'Écrire les tests pour les fonctionnalités principales', '2024-01-18 11:00:00', '2024-01-18 11:00:00', '2024-02-20 16:00:00', 'BASSE', 'TODO', 1, 3),
('Design logo application', 'Création du logo et identité visuelle', '2024-01-21 08:45:00', '2024-01-22 10:30:00', '2024-02-05 12:00:00', 'MOYENNE', 'TERMINE', 2, 2),
('Intégration GPS', 'Ajouter la géolocalisation dans l\'app', '2024-01-22 14:20:00', '2024-01-25 09:15:00', '2024-02-28 15:30:00', 'CRITIQUE', 'EN_COURS', 2, 4),
('Base de données clients', 'Modélisation et création de la BDD', '2024-02-02 09:00:00', '2024-02-02 09:00:00', '2024-02-12 17:00:00', 'URGENT', 'TERMINE', 3, 3),
('Module de reporting', 'Développer les rapports statistiques', '2024-02-03 13:30:00', '2024-02-08 11:45:00', '2024-02-25 16:00:00', 'MOYENNE', 'EN_COURS', 3, 5),
('Système authentification', 'Mise en place login/logout sécurisé', '2024-02-11 10:15:00', '2024-02-11 10:15:00', '2024-02-22 14:00:00', 'CRITIQUE', 'TODO', 4, 4),
('Contenu pédagogique', 'Rédaction des cours et exercices', '2024-02-12 15:45:00', '2024-02-15 12:30:00', '2024-03-10 18:00:00', 'BASSE', 'EN_COURS', 4, 6),
('Mécaniques de jeu', 'Programmation des règles du puzzle', '2024-02-16 11:30:00', '2024-02-16 11:30:00', '2024-03-01 16:30:00', 'MOYENNE', 'TERMINE', 5, 5),
('Graphiques interactifs', 'Création des charts et visualisations', '2024-03-02 08:45:00', '2024-03-05 14:20:00', '2024-03-20 17:00:00', 'URGENT', 'EN_COURS', 6, 6),
('Optimisation performances', 'Améliorer les temps de réponse', '2024-03-03 16:00:00', '2024-03-03 16:00:00', '2024-03-25 15:00:00', 'BASSE', 'TODO', 6, 7),
('Entraînement modèle IA', 'Formation du chatbot avec données', '2024-03-06 12:15:00', '2024-03-10 09:30:00', '2024-03-30 16:00:00', 'CRITIQUE', 'EN_COURS', 7, 7),
('Interface réservation', 'Formulaire de réservation tables', '2024-03-13 09:45:00', '2024-03-13 09:45:00', '2024-03-28 18:00:00', 'MOYENNE', 'TODO', 8, 8),
('Notifications push', 'Système d\'alertes automatiques', '2024-03-14 14:30:00', '2024-03-18 11:15:00', '2024-04-05 16:30:00', 'BASSE', 'EN_COURS', 8, 9),
('Éditeur articles', 'Interface de rédaction WYSIWYG', '2024-03-19 10:00:00', '2024-03-19 10:00:00', '2024-04-10 17:00:00', 'MOYENNE', 'TERMINE', 9, 9),
('API météo externe', 'Intégration services météorologiques', '2024-03-26 13:45:00', '2024-03-29 15:20:00', '2024-04-15 14:00:00', 'URGENT', 'EN_COURS', 10, 10),
('Lecteur vidéo', 'Player HTML5 personnalisé', '2024-04-03 11:20:00', '2024-04-03 11:20:00', '2024-04-25 16:00:00', 'CRITIQUE', 'TODO', 11, 11),
('Gestion codes-barres', 'Scanner et reconnaissance produits', '2024-04-09 08:30:00', '2024-04-12 14:45:00', '2024-05-01 17:30:00', 'MOYENNE', 'EN_COURS', 12, 12),
('Forum communautaire', 'Espace discussion entre voisins', '2024-04-16 15:15:00', '2024-04-16 15:15:00', '2024-05-10 18:00:00', 'BASSE', 'TODO', 13, 13);

-- 5. Table assignement (20 assignations)
INSERT INTO assignement (idTache, idParticipant) VALUES
(1, 1), (1, 2), (2, 2), (2, 3), (3, 3), (4, 2), (4, 4), (5, 4), (6, 3), (6, 5),
(7, 5), (8, 4), (8, 6), (9, 6), (10, 5), (11, 6), (11, 7), (12, 7), (13, 7), (14, 8);

-- 6. Table historiqueTache (20 historiques)
INSERT INTO historiqueTache (idTache, idParticipant, action, ancienneValeur, nouvelleValeur, dateAction) VALUES
(1, 1, 'créé', NULL, 'Conception interface utilisateur', '2024-01-16 10:00:00'),
(1, 1, 'assigné', NULL, 'Alice Martin, Bob Dupont', '2024-01-16 10:15:00'),
(1, 2, 'modifié', 'TODO', 'EN_COURS', '2024-01-18 14:30:00'),
(1, 2, 'terminé', 'EN_COURS', 'TERMINE', '2024-01-24 16:45:00'),
(2, 2, 'créé', NULL, 'Développement backend API', '2024-01-17 09:30:00'),
(2, 2, 'priorité modifiée', 'MOYENNE', 'URGENT', '2024-01-19 11:20:00'),
(2, 3, 'assigné', 'Bob Dupont', 'Bob Dupont, Claire Rousseau', '2024-01-20 14:15:00'),
(3, 3, 'créé', NULL, 'Tests unitaires', '2024-01-18 11:00:00'),
(4, 2, 'créé', NULL, 'Design logo application', '2024-01-21 08:45:00'),
(4, 2, 'terminé', 'EN_COURS', 'TERMINE', '2024-01-22 10:30:00'),
(5, 4, 'créé', NULL, 'Intégration GPS', '2024-01-22 14:20:00'),
(5, 4, 'priorité modifiée', 'URGENT', 'CRITIQUE', '2024-01-25 09:15:00'),
(6, 3, 'créé', NULL, 'Base de données clients', '2024-02-02 09:00:00'),
(6, 3, 'terminé', 'EN_COURS', 'TERMINE', '2024-02-10 17:30:00'),
(7, 5, 'créé', NULL, 'Module de reporting', '2024-02-03 13:30:00'),
(7, 5, 'modifié', 'TODO', 'EN_COURS', '2024-02-08 11:45:00'),
(8, 4, 'créé', NULL, 'Système authentification', '2024-02-11 10:15:00'),
(9, 6, 'créé', NULL, 'Contenu pédagogique', '2024-02-12 15:45:00'),
(10, 5, 'créé', NULL, 'Mécaniques de jeu', '2024-02-16 11:30:00'),
(10, 5, 'terminé', 'EN_COURS', 'TERMINE', '2024-02-28 14:20:00');

-- 7. Table commentaire (20 commentaires)
INSERT INTO commentaire (contenu, dateCommentaire, idUtilisateur, idTache) VALUES
('Les maquettes sont très réussies, beau travail !', '2024-01-19 14:30:00', 2, 1),
('Il faudrait ajouter une page d\'erreur 404 personnalisée', '2024-01-20 09:15:00', 3, 1),
('L\'API fonctionne bien, mais il manque la validation des données', '2024-01-22 16:45:00', 3, 2),
('Peux-tu documenter les endpoints dans Swagger ?', '2024-01-23 11:20:00', 1, 2),
('Les tests unitaires couvrent 85% du code, c\'est suffisant', '2024-01-25 13:10:00', 2, 3),
('Le logo est parfait, il reflète bien l\'esprit de l\'app', '2024-01-23 17:30:00', 4, 4),
('Attention, l\'intégration GPS ne fonctionne pas sur iOS', '2024-01-26 10:45:00', 2, 5),
('La géolocalisation est maintenant opérationnelle', '2024-01-28 14:20:00', 4, 5),
('La structure de la BDD est optimale pour nos besoins', '2024-02-05 09:30:00', 5, 6),
('Excellents rapports, très détaillés et utiles', '2024-02-12 15:45:00', 3, 7),
('Le système d\'auth est sécurisé avec JWT', '2024-02-15 11:30:00', 6, 8),
('Le contenu pédagogique est de grande qualité', '2024-02-18 16:15:00', 4, 9),
('Les mécaniques de jeu sont addictives, bravo !', '2024-02-20 12:45:00', 6, 10),
('Les graphiques sont interactifs et fluides', '2024-03-08 14:30:00', 7, 11),
('Il faut optimiser les requêtes SQL pour de meilleures performances', '2024-03-10 10:20:00', 6, 12),
('Le chatbot comprend bien les demandes complexes', '2024-03-15 13:45:00', 8, 13),
('L\'interface de réservation est intuitive', '2024-03-20 11:30:00', 9, 14),
('Les notifications arrivent bien en temps réel', '2024-03-22 16:00:00', 8, 15),
('L\'éditeur WYSIWYG est parfait pour les rédacteurs', '2024-03-25 14:15:00', 10, 16),
('L\'API météo renvoie des données précises', '2024-04-02 12:30:00', 11, 17);