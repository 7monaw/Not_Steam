CONNECT TO COMP421;

-- User
INSERT INTO User (username, password, avatar, age, location) VALUES ('user_1', 'password123', NULL, 26, 'North America');
INSERT INTO User (username, password, avatar, age, location) VALUES ('user_2', 'password123', NULL, 21, 'Europe');
INSERT INTO User (username, password, avatar, age, location) VALUES ('user_3', 'password123', NULL, 23, 'Asia');
INSERT INTO User (username, password, avatar, age, location) VALUES ('user_4', 'password123', NULL, 24, 'Asia');
INSERT INTO User (username, password, avatar, age, location) VALUES ('user_5', 'password123', NULL, 27, 'Australia');
INSERT INTO User (username, password, avatar, age, location) VALUES ('user_6', 'password123', NULL, 24, 'Asia');
INSERT INTO User (username, password, avatar, age, location) VALUES ('user_7', 'password123', NULL, 27, 'Europe');

-- Location 
INSERT INTO Location (loc_name) VALUES ('Asia');
INSERT INTO Location (loc_name) VALUES ('North America');
INSERT INTO Location (loc_name) VALUES ('Europe');
INSERT INTO Location (loc_name) VALUES ('Australia');

-- Game
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (1, 60, 'Battle Quest', 'E');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (2, 50, 'Space Raiders', 'T');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (3, 40, 'Mystic Lands', 'E');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (4, 30, 'Speed Racer X', 'E');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (5, 20, 'My Adventure', 'E');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (6, 70, 'Dragon Kingdom', 'M');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (7, 45, 'Cyber Strike', 'T');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (8, 25, 'Apocalypsic I', 'M');
INSERT INTO Game (gameID, price, game_name, age_rating) VALUES (9, 55, 'Apocalypsic II', 'M');

-- System
INSERT INTO System (deviceID, processor_requirement, os, min_ram, storage_requirement) VALUES ('SYS001', 'Intel i5', 'Windows 10', 8, '50GB');
INSERT INTO System (deviceID, processor_requirement, os, min_ram, storage_requirement) VALUES ('SYS002', 'Intel i7', 'Windows 11', 16, '100GB');
INSERT INTO System (deviceID, processor_requirement, os, min_ram, storage_requirement) VALUES ('SYS003', 'AMD Ryzen 5', 'Windows 10', 8, '60GB');
INSERT INTO System (deviceID, processor_requirement, os, min_ram, storage_requirement) VALUES ('SYS004', 'AMD Ryzen 7', 'Windows 11', 16, '120GB');
INSERT INTO System (deviceID, processor_requirement, os, min_ram, storage_requirement) VALUES ('SYS005', 'Intel i3', 'Windows 8', 4, '40GB');

-- Genre
INSERT INTO Genre (genre_name) VALUES ('Action');
INSERT INTO Genre (genre_name) VALUES ('Horror');
INSERT INTO Genre (genre_name) VALUES ('RPG');
INSERT INTO Genre (genre_name) VALUES ('Simulation');
INSERT INTO Genre (genre_name) VALUES ('Strategy');
INSERT INTO Genre (genre_name) VALUES ('Adventure');
INSERT INTO Genre (genre_name) VALUES ('survival');
INSERT INTO Genre (genre_name) VALUES ('puzzle');

-- Game Developer
INSERT INTO GameDeveloper (dev_name) VALUES ('EpicGames');
INSERT INTO GameDeveloper (dev_name) VALUES ('Nintendo');
INSERT INTO GameDeveloper (dev_name) VALUES ('Rockstar');
INSERT INTO GameDeveloper (dev_name) VALUES ('Mojang');
INSERT INTO GameDeveloper (dev_name) VALUES ('Bethesda');
INSERT INTO GameDeveloper (dev_name) VALUES ('Blizzard');
INSERT INTO GameDeveloper (dev_name) VALUES ('RiotGames');
INSERT INTO GameDeveloper (dev_name) VALUES ('FromSoftware Inc.');
INSERT INTO GameDeveloper (dev_name) VALUES ('NaughtyDog');

-- Publisher
INSERT INTO Publisher (publisher_name) VALUES ('Ubisoft');
INSERT INTO Publisher (publisher_name) VALUES ('EA');
INSERT INTO Publisher (publisher_name) VALUES ('Activision');
INSERT INTO Publisher (publisher_name) VALUES ('Sony');
INSERT INTO Publisher (publisher_name) VALUES ('Microsoft');
INSERT INTO Publisher (publisher_name) VALUES ('Nintendo');


-- ==========================================
-- Relational Schema 
-- ==========================================

-- Available_In 
INSERT INTO Available_In (gameID, loc_name) VALUES (1, 'Australia');
INSERT INTO Available_In (gameID, loc_name) VALUES (1, 'Asia');
INSERT INTO Available_In (gameID, loc_name) VALUES (1, 'North America');
INSERT INTO Available_In (gameID, loc_name) VALUES (2, 'Asia');
INSERT INTO Available_In (gameID, loc_name) VALUES (2, 'Europe');
INSERT INTO Available_In (gameID, loc_name) VALUES (2, 'North America');
INSERT INTO Available_In (gameID, loc_name) VALUES (3, 'North America');
INSERT INTO Available_In (gameID, loc_name) VALUES (4, 'Europe');
INSERT INTO Available_In (gameID, loc_name) VALUES (5, 'Australia');
INSERT INTO Available_In (gameID, loc_name) VALUES (5, 'Asia');
INSERT INTO Available_In (gameID, loc_name) VALUES (6, 'Europe');
INSERT INTO Available_In (gameID, loc_name) VALUES (6, 'North America');
INSERT INTO Available_In (gameID, loc_name) VALUES (7, 'Asia');
INSERT INTO Available_In (gameID, loc_name) VALUES (8, 'Asia');
INSERT INTO Available_In (gameID, loc_name) VALUES (8, 'North America');
INSERT INTO Available_In (gameID, loc_name) VALUES (9, 'Australia');
INSERT INTO Available_In (gameID, loc_name) VALUES (9, 'Asia');
INSERT INTO Available_In (gameID, loc_name) VALUES (9, 'North America');

-- SteamWallet 
INSERT INTO SteamWallet (walletID, credit, username) VALUES (1, 836.13, 'user_1');
INSERT INTO SteamWallet (walletID, credit, username) VALUES (2, 235.29, 'user_2');
INSERT INTO SteamWallet (walletID, credit, username) VALUES (3, 235.29, 'user_3');
INSERT INTO SteamWallet (walletID, credit, username) VALUES (4, 235.29, 'user_4');
INSERT INTO SteamWallet (walletID, credit, username) VALUES (5, 235.29, 'user_5');
INSERT INTO SteamWallet (walletID, credit, username) VALUES (6, 235.29, 'user_6');
INSERT INTO SteamWallet (walletID, credit, username) VALUES (7, 235.29, 'user_7');

-- Award
INSERT INTO Award (award_name, description, username) VALUES ('Award_12', 'pharmacology prudential surplices Ediths Barranquilla yeahs teletypes Bjorks scollops hermaphrodites ', 'user_1');
INSERT INTO Award (award_name, description, username) VALUES ('Award_1', 'bisons percolations Vela necromancys wright dark excrescence Eds furtively Estonias ', 'user_2');
INSERT INTO Award (award_name, description, username) VALUES ('Award_54', 'correspondence depths Gazas caricatured sweetbriers federalist infancy taxonomic type sheriffs ', 'user_3');
INSERT INTO Award (award_name, description, username) VALUES ('Award_47', 'increments knockwurst grimes brag eyeballing codependents encoding Greensboro carryalls Jodi ', 'user_4');
INSERT INTO Award (award_name, description, username) VALUES ('Award_17', 'Spirograph respecting tributes Dustbusters refiners philander radioisotope jitterss functioning Boulezs ', 'user_5');
INSERT INTO Award (award_name, description, username) VALUES ('Award_73', 'plungers Reva Terries stormed idlers sunbeams Tamikas hide communal revocation ', 'user_6');
INSERT INTO Award (award_name, description, username) VALUES ('Award_11', 'hibernated brakes pamphlets jugglers marred organics layered Jeffrys disassembles Yamagatas ', 'user_7');

-- Achievement 
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('First Kill', 'Defeat your first enemy', NULL, 1);
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('Treasure Hunter', 'Find 10 hidden treasures', NULL, 1);
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('Amateur', 'Raid the first space shuttle', NULL, 2);
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('Dwarf', 'Discover a dwarf planet', NULL, 2);
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('Good Racer', 'Win a race under 2 minutes', NULL, 4);
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('Dragon Slayer', 'Defeat Dargeon Konungur', NULL, 6);
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('Sneaky', 'Survive 1 day', NULL, 8);
INSERT INTO Achievement (achievement_name, description, avatar, gameID) VALUES ('Sacrifice', 'Sacrifice yourself for Lily', NULL, 8);

-- Requires
INSERT INTO Requires (deviceID, gameID) VALUES ('SYS001', 1);
INSERT INTO Requires (deviceID, gameID) VALUES ('SYS002', 2);
INSERT INTO Requires (deviceID, gameID) VALUES ('SYS003', 3);
INSERT INTO Requires (deviceID, gameID) VALUES ('SYS004', 4);
INSERT INTO Requires (deviceID, gameID) VALUES ('SYS005', 5);

-- Releases
INSERT INTO Releases (gameID, dev_name, publisher_name, release_time) VALUES (1, 'EpicGames', 'EA', '2023-10-15');
INSERT INTO Releases (gameID, dev_name, publisher_name, release_time) VALUES (2, 'Nintendo', 'Nintendo', '2022-03-21');
INSERT INTO Releases (gameID, dev_name, publisher_name, release_time) VALUES (3, 'Bethesda', 'Microsoft', '2021-11-11');
INSERT INTO Releases (gameID, dev_name, publisher_name, release_time) VALUES (4, 'Rockstar', 'Sony', '2024-01-05');
INSERT INTO Releases (gameID, dev_name, publisher_name, release_time) VALUES (8, 'NaughtyDog', 'Sony', '2019-05-10');
INSERT INTO Releases (gameID, dev_name, publisher_name, release_time) VALUES (9, 'NaughtyDog', 'Sony', '2023-09-12');

-- Contracts
INSERT INTO Contracts (dev_name, publisher_name, monetary_amount, duration) VALUES ('EpicGames', 'EA', 500000.00, 24);
INSERT INTO Contracts (dev_name, publisher_name, monetary_amount, duration) VALUES ('Nintendo', 'Nintendo', 0.00, 120);
INSERT INTO Contracts (dev_name, publisher_name, monetary_amount, duration) VALUES ('Bethesda', 'Microsoft', 750000.00, 36);
INSERT INTO Contracts (dev_name, publisher_name, monetary_amount, duration) VALUES ('Rockstar', 'Sony', 1000000.00, 48);
INSERT INTO Contracts (dev_name, publisher_name, monetary_amount, duration) VALUES ('NaughtyDog', 'Sony', 800000.00, 60);


-- Associates
INSERT INTO Associates (genre_name, gameID) VALUES ('Action', 1);
INSERT INTO Associates (genre_name, gameID) VALUES ('RPG', 1);
INSERT INTO Associates (genre_name, gameID) VALUES ('Simulation', 2);
INSERT INTO Associates (genre_name, gameID) VALUES ('Adventure', 3);
INSERT INTO Associates (genre_name, gameID) VALUES ('RPG', 3);
INSERT INTO Associates (genre_name, gameID) VALUES ('survival', 8);
INSERT INTO Associates (genre_name, gameID) VALUES ('puzzle', 4);
INSERT INTO Associates (genre_name, gameID) VALUES ('Horror', 8);
INSERT INTO Associates (genre_name, gameID) VALUES ('Action', 8);
INSERT INTO Associates (genre_name, gameID) VALUES ('survival', 9);
INSERT INTO Associates (genre_name, gameID) VALUES ('RPG', 9);
INSERT INTO Associates (genre_name, gameID) VALUES ('Horror', 9);
INSERT INTO Associates (genre_name, gameID) VALUES ('Adventure', 5);
INSERT INTO Associates (genre_name, gameID) VALUES ('Action', 7);


-- InterestedIn
INSERT INTO InterestedIn (genre_name, username) VALUES ('Action', 'user_1');
INSERT INTO InterestedIn (genre_name, username) VALUES ('RPG', 'user_1');
INSERT INTO InterestedIn (genre_name, username) VALUES ('Simulation', 'user_2');
INSERT INTO InterestedIn (genre_name, username) VALUES ('Horror', 'user_3');
INSERT INTO InterestedIn (genre_name, username) VALUES ('Strategy', 'user_4');
INSERT INTO InterestedIn (genre_name, username) VALUES ('RPG', 'user_4');

-- Wishes
INSERT INTO Wishes (gameID, username) VALUES (4, 'user_1');
INSERT INTO Wishes (gameID, username) VALUES (6, 'user_1');
INSERT INTO Wishes (gameID, username) VALUES (1, 'user_2');
INSERT INTO Wishes (gameID, username) VALUES (9, 'user_3');
INSERT INTO Wishes (gameID, username) VALUES (7, 'user_4');
INSERT INTO Wishes (gameID, username) VALUES (9, 'user_1');

-- Buys
INSERT INTO Buys (gameID, username, purchase_time) VALUES (1, 'user_1', '14:30:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (3, 'user_1', '09:15:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (2, 'user_2', '18:45:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (1, 'user_2', '10:00:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (8, 'user_3', '23:59:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (5, 'user_5', '10:00:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (2, 'user_7', '12:30:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (4, 'user_7', '12:30:00');
INSERT INTO Buys (gameID, username, purchase_time) VALUES (9, 'user_1', '23:59:00');

-- Blocks
INSERT INTO Blocks (main_name, blocked_name) VALUES ('user_1', 'user_4');
INSERT INTO Blocks (main_name, blocked_name) VALUES ('user_2', 'user_5');
INSERT INTO Blocks (main_name, blocked_name) VALUES ('user_3', 'user_6');
INSERT INTO Blocks (main_name, blocked_name) VALUES ('user_4', 'user_7');
INSERT INTO Blocks (main_name, blocked_name) VALUES ('user_7', 'user_1');

-- Friend
INSERT INTO Friend (main_name, friend_name) VALUES ('user_1', 'user_2');
INSERT INTO Friend (main_name, friend_name) VALUES ('user_2', 'user_1');
INSERT INTO Friend (main_name, friend_name) VALUES ('user_1', 'user_3');
INSERT INTO Friend (main_name, friend_name) VALUES ('user_3', 'user_1');
INSERT INTO Friend (main_name, friend_name) VALUES ('user_4', 'user_5');
INSERT INTO Friend (main_name, friend_name) VALUES ('user_5', 'user_4');

-- SequelOf
INSERT INTO SequelOf (main_game, sequel_game) VALUES (8, 9);
