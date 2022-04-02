-- Initialisation des tables
INSERT INTO Country(code, name) VALUES
    ('FR', 'France'), -- Les clés sont auto-générées
    ('UK', 'United Kingdom'),
    ('US', 'United States of America');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, SELECT id FROM Country WHERE code = 'FR'),
    ('Lyon', 2, SELECT id FROM Country WHERE code = 'FR'),
    ('Marseille', 2, SELECT id FROM Country WHERE code = 'FR'),
    ('London', 18, SELECT id FROM Country WHERE code = 'UK'),
    ('Oxford', 1, SELECT id FROM Country WHERE code = 'UK'),
    ('Cambridge', 2, SELECT id FROM Country WHERE code = 'UK'),
    ('New York', 27, SELECT id FROM Country WHERE code = 'US'),
    ('Los Angeles', 11, SELECT id FROM Country WHERE code = 'US'),
    ('San Francisco', 7, SELECT id FROM Country WHERE code = 'US');

INSERT INTO ROLE(nom) VALUES
    ('Étudiant'),
    ('Tuteur'),
    ('Responsable des stages');

INSERT INTO ETAT_STAGE(nom) VALUES
    ('Proposition en attente de validation'),
    ('Proposition validée'),
    ('Étudiant assigné'),
    ('Convention signée par l''entreprise'),
    ('Convention signée par l''école'),
    ('Convention signée par l''étudiant'),
    ('Soutenance planifiée');

INSERT INTO UTILISATEUR(identifiant, mot_de_passe, prenom, nom, num_tel, email, role_id) VALUES
    ('tnivoix', 'supertoto', 'Thomas', 'Nivoix', '0642424242', 'thomas.nivoix@gmail.com', SELECT id FROM ROLE WHERE nom = 'Étudiant'),
    ('glasservarie', 'superbinbin', 'Gabin', 'Lasservarie', '0643434343', 'gabin.lasservarie@gmail.com', SELECT id FROM ROLE WHERE nom = 'Étudiant'),
    ('cbourdarias', 'superclecle', 'Cléa', 'Bourdarias', '0644444444', 'clea.bourdarias@gmail.com', SELECT id FROM ROLE WHERE nom = 'Étudiant'),
    ('dkeskin', 'superdede', 'Defne', 'Keskin', '0645454545', 'defne.keskin@gmail.com', SELECT id FROM ROLE WHERE nom = 'Étudiant'),
    ('pchaubet', 'superpopo', 'Paul', 'Chaubet', '0646464646', 'paul.chaubet@gmail.com', SELECT id FROM ROLE WHERE nom = 'Étudiant'),
    ('ecarayol', 'superriri', 'Éric', 'Carayol', '0647474747', 'eric.carayol@gmail.com', SELECT id FROM ROLE WHERE nom = 'Responsable des stages'),
    ('rbastide', 'superbaba', 'Rémi', 'Bastide', '0648484848', 'remi.bastide@gmail.com', SELECT id FROM ROLE WHERE nom = 'Tuteur'),
    ('lgregoire', 'superlolo', 'Laurent', 'Gregoire', '0649494949', 'laurent.gregoire@gmail.com', SELECT id FROM ROLE WHERE nom = 'Tuteur'),
    ('hpingaud', 'superpinpin', 'Hervé', 'Pingaud', '0650505050', 'herve.pingaud@gmail.com', SELECT id FROM ROLE WHERE nom = 'Tuteur');

INSERT INTO ENTREPRISE(nom, num_tel, email, secteur_activite) VALUES
    ('Ubisoft', '0742424242', 'ubisoft@gmail.com', 'Jeux vidéo'),
    ('Pierre Fabre', '0743434343', 'pierre.fabre@gmail.com', 'Santé'),
    ('CHU Pellegrin', '0744444444', 'chu.pellegrin@gmail.com', 'Santé'),
    ('Thales', '0745454545', 'thales@gmail.com', 'Aéronautique');

INSERT INTO STAGE(annee_etude, adresse, ville, code_postal, pays, contexte, sujet, details, activites, competences, date_debut, duree, remuneration, service, horaires, maitre_de_stage, fonction, etat_stage_id, entreprise_id) VALUES
    (3,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 8, 0, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Proposition en attente de validation', SELECT id FROM ENTREPRISE WHERE nom = 'CHU Pellegrin'),
    (3,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 8, 0, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Étudiant assigné', SELECT id FROM ENTREPRISE WHERE nom = 'Pierre Fabre'),
    (3,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 8, 0, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Étudiant assigné', SELECT id FROM ENTREPRISE WHERE nom = 'Pierre Fabre'),
    (4,'ici', 'Castres', '81100', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('02-05-2022', 'dd-MM-yy'), 10, 3.9, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Étudiant assigné', SELECT id FROM ENTREPRISE WHERE nom = 'Pierre Fabre'),
    (5,'ici', 'Bordeaux', '33000', 'France', 'contexte', 'sujet', 'details', 'activites', 'competences', PARSEDATETIME('01-01-2024', 'dd-MM-yy'), 24, 4.2, 'service', 'horaires', 'maitreDeStage', 'fonction', SELECT id FROM ETAT_STAGE WHERE nom = 'Proposition en attente de validation', SELECT id FROM ENTREPRISE WHERE nom = 'Ubisoft');

INSERT INTO STAGE_UTILISATEUR(stage_id, utilisateur_id) VALUES
    (2, SELECT id FROM UTILISATEUR WHERE identifiant = 'glasservarie'),
    (2, SELECT id FROM UTILISATEUR WHERE identifiant = 'hpingaud'),
    (3, SELECT id FROM UTILISATEUR WHERE identifiant = 'cbourdarias');