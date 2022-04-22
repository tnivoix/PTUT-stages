-- Initialisation des tables
INSERT INTO ROLE(nom) VALUES
    ('ROLE_ETUDIANT'),
    ('ROLE_TUTEUR'),
    ('ROLE_RESPONSABLESTAGES');

INSERT INTO ETAT_STAGE(nom) VALUES
    ('Proposition en attente de validation'),
    ('Proposition validée'),
    ('Étudiant assigné'),
    ('Convention signée par l''entreprise'),
    ('Convention signée par l''école'),
    ('Convention signée par l''étudiant'),
    ('Soutenance planifiée'),
    ('Stage terminé');
/*MDP : supertoto, superbinbin, superclecle, superdede, superpopo, superriri, superbaba, superlolo, superpinpin*/
INSERT INTO UTILISATEUR(identifiant, mot_de_passe, prenom, nom, num_tel, email, role_id) VALUES
    ('tnivoix', '$2a$12$Ur81/XqeMwSZKjvcKtW7euAQc0m.QLGZU7NkYtyWNSbQteJ0La89q', 'Thomas', 'Nivoix', '0642424242', 'thomas.nivoix@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('glasservarie', '$2a$12$eCvUkUjWQuoLl0IK/FPi3erPO84OKmICTGKJjfHXyXY3.jscskUyS', 'Gabin', 'Lasservarie', '0643434343', 'gabin.lasservarie@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('cbourdarias', '$2a$12$2SuMs1NC902.ZXBAlzJUTe8d/pbaJbM.Q1BDIGUMbvgHyTQoDSDKa', 'Cléa', 'Bourdarias', '0644444444', 'clea.bourdarias@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('dkeskin', '$2a$12$C1.t9ffGwK73MjDpqWixIu8cNvVc99WwU0ju7fEGBDm9ujBpK/IX6', 'Defne', 'Keskin', '0645454545', 'defne.keskin@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('pchaubet', '$2a$12$0QKG.SbqnhndQhRU8ud2humPJeK7tv58WV7QV7Tc6NLjKXnaGcpq6', 'Paul', 'Chaubet', '0646464646', 'paul.chaubet@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('ecarayol', '$2a$12$5IhV0ldc81FTSw0.qWzGQOZrfX95cJyJbVjMnjb3ewyuA1wH6rqWq', 'Éric', 'Carayol', '0647474747', 'eric.carayol@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_RESPONSABLESTAGES'),
    ('rbastide', '$2a$12$RTHRQsfZDynNYTJM4QAh/eyNQMU5w/QmTANT/Lt7EpAY1w1Wou./C', 'Rémi', 'Bastide', '0648484848', 'remi.bastide@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_TUTEUR'),
    ('lgregoire', '$2a$12$ynOO9pvBm9Qe8tzV77Ev2ug71w6f1cw7/EOJBtKufXCHoqbpBUuR2', 'Laurent', 'Gregoire', '0649494949', 'laurent.gregoire@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_TUTEUR'),
    ('hpingaud', '$2a$12$pyNMIbHztynrEo/M5AGEv.1XgLcPn/DOvIIv/8TnUoxEhc3SnO7Ea', 'Hervé', 'Pingaud', '0650505050', 'herve.pingaud@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_TUTEUR');

INSERT INTO ENTREPRISE(nom, num_tel, email, secteur_activite) VALUES
    ('Ubisoft', '0742424242', 'ubisoft@gmail.com', 'Jeux vidéo'),
    ('Pierre Fabre', '0743434343', 'pierre.fabre@gmail.com', 'Santé'),
    ('CHU Pellegrin', '0744444444', 'chu.pellegrin@gmail.com', 'Santé'),
    ('Thales', '0745454545', 'thales@gmail.com', 'Aéronautique');

INSERT INTO STAGE(annee_etude, adresse, ville, code_postal, pays, contexte, sujet, details, activites, competences, date_debut, duree, remuneration, service, horaires, maitre_de_stage, fonction, etat_stage_id, entreprise_id, num_tel, email) VALUES
    (3,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 8, 0, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Proposition en attente de validation', SELECT id FROM ENTREPRISE WHERE nom = 'CHU Pellegrin','9827367287','coucou@gmail.com'),
    (3,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 8, 0, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Étudiant assigné', SELECT id FROM ENTREPRISE WHERE nom = 'Pierre Fabre','9827367287','coucou@gmail.com'),
    (3,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 8, 0, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Étudiant assigné', SELECT id FROM ENTREPRISE WHERE nom = 'Pierre Fabre','9827367287','coucou@gmail.com'),
    (4,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 10, 3.9, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Proposition validée', SELECT id FROM ENTREPRISE WHERE nom = 'Pierre Fabre','9827367287','coucou@gmail.com'),
    (5,'ici', 'Bordeaux', '33000', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('01-01-2024', 'dd-MM-yy'), 24, 4.2, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Proposition en attente de validation', SELECT id FROM ENTREPRISE WHERE nom = 'Ubisoft','9827367287','coucou@gmail.com');

INSERT INTO STAGE_UTILISATEUR(stage_id, utilisateur_id) VALUES
    (2, SELECT id FROM UTILISATEUR WHERE identifiant = 'glasservarie'),
    (2, SELECT id FROM UTILISATEUR WHERE identifiant = 'hpingaud'),
    (3, SELECT id FROM UTILISATEUR WHERE identifiant = 'cbourdarias'),
    (3, SELECT id FROM UTILISATEUR WHERE identifiant = 'ecarayol');