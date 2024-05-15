INSERT INTO developer (name, founded, country)
VALUES
    ('Valve Corporation', '1996-08-24', 'USA'),
    ('Hidden Path Entertainment', '2006-08-24', 'USA'),
    ('Forgotten Empires', '2013-08-24', 'NETHERLANDS'),
    ('Santa Monica Studio', '1999-08-24', 'USA');

INSERT INTO game (title, developer_id)
VALUES
    ('Dota 2', 1),
    ('Counter-Strike: Global Offensive', 2),
    ('Age of Empires II: Definitive Edition', 3),
    ('God of War', 4);

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
