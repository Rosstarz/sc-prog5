INSERT INTO developer (name, founded, country)
VALUES
    ('Valve Corporation', '1996-08-24', 'USA'),
    ('Hidden Path Entertainment', '2006-08-24', 'USA'),
    ('Forgotten Empires', '2013-08-24', 'NETHERLANDS'),
    ('Santa Monica Studio', '1999-08-24', 'USA');

INSERT INTO game (title, developer_id, description)
VALUES
    ('Counter-Strike: Global Offensive', '1', 'Counter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago.'),
    ('Counter-Strike: Source', '1', 'Counter-Strike: Source blends Counter-Strike''s award-winning teamplay action with the advanced technology of Source technology.'),
    ('Age of Empires II: Definitive Edition', '3', 'Age of Empires II: Definitive Edition celebrates the 20th anniversary of one of the most popular strategy games ever with stunning 4K Ultra HD graphics, a new and fully remastered soundtrack, and brand-new content.'),
    ('God of War', '4', 'His vengeance against the gods of Olympus far behind him, Kratos now lives in the lands of Norse Gods and monsters. It is in this harsh, unforgiving world that he must fight to survive… and teach his son to do the same.');

INSERT INTO store (name, is_library_online)
VALUES
    ('Steam', 'true'),
    ('Epic Games', 'true'),
    ('Origin', 'true'),
    ('Uplay', 'false');

INSERT INTO game_store (game_id, store_id, release_date, price)
VALUES
    ('1', '1', '2011-08-23', '0.0'),
    ('2', '1', '2012-08-21', '0.0'),
    ('3', '1', '2019-11-14', '19.99'),
    ('4', '2', '2022-01-14', '49.99');

INSERT INTO app_user(username, password, role)
VALUES ('ross', '$2a$10$hiRHbNcO.iJJc3oHZw.Couccct8n4dks6Il/QBgwoWOW8h2H9l/7.', 1),/*ross, xV4Jv-VA!b12, admin*/
    ('admin', '$2a$10$oh3PI3jPslmkcbE9PBt9gulveiyMQDSKp4dMbWNkpDlv9CokbSLyW', 1),/*admin, 58HZY(Y4q}fV, admin*/
    ('user', '$2a$10$43bXL.dF00Z9PXiXOzBOeuMjmvki09gIC2Lp6u65J0PTQs0rAadca', 0); /*user, .qD6w4\;3gF6, user*/
