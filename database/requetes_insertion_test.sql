INSERT INTO utilisateur (nomUtilisateur, email, motDePasse)
VALUES 
  ('Alice Martin', 'alice@example.com', 'motdepasse1'),
  ('Bob Dupont', 'bob@example.com', 'motdepasse2'),
  ('Charlie Hugo', 'charlie@example.com', 'motdepasse3');

INSERT INTO projet (intitule, descriptionProjet, idProprietaire)
VALUES 
  ('Projet Alpha', 'Migration des serveurs', 1),
  ('Projet Beta', 'Développement de l’application GalaxyTasks', 2);

INSERT INTO participationProjet (idProjet, idUtilisateur, role)
VALUES 
  (1, 1, 'proprietaire'),
  (1, 2, 'membre'),
  (2, 2, 'proprietaire'),
  (2, 3, 'membre');

INSERT INTO tache (titre, description_tache, priorite, est_termine, idProjet, idProprietaire)
VALUES 
  ('Configurer la base', 'Créer les schémas SQL', '3', 'A faire', 1, 1),
  ('Définir les wireframes', 'Préparer les maquettes HTML/CSS', '2', 'En cours', 2, 2);

INSERT INTO assignement (idTache, idParticipant)
VALUES 
  (1, 2),
  (2, 3);

INSERT INTO commentaire (contenu, date_commentaire, idUtilisateur, idTache)
VALUES 
  ('Bonne idée pour le formulaire de connexion.', NOW(), 2, 2),
  ('Ajoutez une vérification des doublons.', NOW(), 3, 2);

INSERT INTO historique_tache (idTache, idParticipant, action, ancienne_valeur, nouvelle_valeur)
VALUES 
  (1, 1, 'créé', NULL, 'Créer les schémas SQL'),
  (2, 3, 'assigné', NULL, 'Charlie assigné');
