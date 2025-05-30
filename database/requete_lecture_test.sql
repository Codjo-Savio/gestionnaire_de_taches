-- Voir tous les utilisateurs
SELECT * FROM utilisateur;

-- Voir tous les projets avec leur propriétaire
SELECT p.intitule, u.nomUtilisateur AS proprietaire
FROM projet p
JOIN utilisateur u ON p.idProprietaire = u.idUtilisateur;

-- Voir les participants du projet 1
SELECT u.nomUtilisateur, pp.role
FROM participationProjet pp
JOIN utilisateur u ON pp.idUtilisateur = u.idUtilisateur
WHERE pp.idProjet = 1;

-- Voir les tâches du projet 2
SELECT t.titre, t.est_termine
FROM tache t
WHERE t.idProjet = 2;
