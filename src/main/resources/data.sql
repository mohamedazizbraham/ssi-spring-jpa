INSERT INTO personne (matricule, nom, prenom, poste) VALUES
                                                         (1, 'Dupont', 'Jean', 'Directeur'),
                                                         (2, 'Martin', 'Sophie', 'Chef de projet'),
                                                         (3, 'Bernard', 'Pierre', 'Développeur'),
                                                         (4, 'Thomas', 'Marie', 'Analyste');

-- Mise à jour des relations hiérarchiques
UPDATE personne SET superieur_matricule = 1 WHERE matricule = 2;
UPDATE personne SET superieur_matricule = 2 WHERE matricule IN (3, 4);

-- Insertion des projets
INSERT INTO projet (code, nom, date_debut, date_fin) VALUES
                                                         (1, 'Projet A', '2024-01-01', '2024-12-31'),
                                                         (2, 'Projet B', '2024-02-01', NULL),
                                                         (3, 'Projet C', '2024-03-01', '2024-09-30');

-- Insertion des participations initiales
INSERT INTO participation (personne_matricule, projet_code, role, pourcentage) VALUES
                                                                                   (2, 1, 'Chef de projet', 50),
                                                                                   (3, 1, 'Développeur', 70),
                                                                                   (4, 2, 'Analyste', 60);