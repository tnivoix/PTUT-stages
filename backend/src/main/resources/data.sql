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

INSERT INTO UTILISATEUR(identifiant, mot_de_passe, prenom, nom, num_tel, email, role_id) VALUES
    ('tnivoix', 'supertoto', 'Thomas', 'Nivoix', '0642424242', 'thomas.nivoix@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('glasservarie', 'superbinbin', 'Gabin', 'Lasservarie', '0643434343', 'gabin.lasservarie@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('cbourdarias', 'superclecle', 'Cléa', 'Bourdarias', '0644444444', 'clea.bourdarias@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('dkeskin', 'superdede', 'Defne', 'Keskin', '0645454545', 'defne.keskin@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('pchaubet', 'superpopo', 'Paul', 'Chaubet', '0646464646', 'paul.chaubet@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_ETUDIANT'),
    ('ecarayol', 'superriri', 'Éric', 'Carayol', '0647474747', 'eric.carayol@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_RESPONSABLESTAGES'),
    ('rbastide', 'superbaba', 'Rémi', 'Bastide', '0648484848', 'remi.bastide@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_TUTEUR'),
    ('lgregoire', 'superlolo', 'Laurent', 'Gregoire', '0649494949', 'laurent.gregoire@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_TUTEUR'),
    ('hpingaud', 'superpinpin', 'Hervé', 'Pingaud', '0650505050', 'herve.pingaud@gmail.com', SELECT id FROM ROLE WHERE nom = 'ROLE_TUTEUR');

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
    (3, SELECT id FROM UTILISATEUR WHERE identifiant = 'cbourdarias');