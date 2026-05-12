CONNECT TO COMP421;

-- ========
-- Entities
-- ========

CREATE TABLE User
(
	    username VARCHAR(30) NOT NULL,
	    password VARCHAR(50) NOT NULL,
	    CONSTRAINT pwd_len CHECK (LENGTH(password) >= 8),
	    avatar BLOB(1M),
	    age INTEGER,
	    location VARCHAR(100),
	    PRIMARY KEY (username)
);

CREATE TABLE Game
(
	    gameID INTEGER NOT NULL,
	    price INT DEFAULT 0,
	    game_name VARCHAR(200) DEFAULT NULL,
	    age_rating VARCHAR(10) DEFAULT 'E',
	    PRIMARY KEY (gameID)
);

CREATE TABLE System
(
	    deviceID VARCHAR(50) NOT NULL,
	    processor_requirement VARCHAR(100) DEFAULT NULL,
	    os VARCHAR(200) DEFAULT NULL,
	    min_ram INTEGER,
	    storage_requirement VARCHAR(10) DEFAULT NULL,
	    PRIMARY KEY (deviceID)
);

CREATE TABLE Genre
(
	    genre_name VARCHAR(20) NOT NULL,
	    PRIMARY KEY (genre_name)
);

CREATE TABLE SteamWallet
(
	    walletID INTEGER NOT NULL,
	    credit DECIMAL(10, 2),
	    username VARCHAR(30) NOT NULL,
	    PRIMARY KEY (walletID),
	    FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE Award
(
	    award_name VARCHAR(15) NOT NULL,
	    description VARCHAR(200),
	    username VARCHAR(30) NOT NULL,
	    PRIMARY KEY (award_name),
	    FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE Achievement
(
	    achievement_name VARCHAR(15) NOT NULL,
	    description VARCHAR(200),
	    avatar BLOB(1M),
	    gameID INTEGER NOT NULL,
	    PRIMARY KEY (achievement_name),
	    FOREIGN KEY (gameID) REFERENCES Game(gameID)
);

CREATE TABLE GameDeveloper
(
	    dev_name VARCHAR(20) NOT NULL,
	    PRIMARY KEY (dev_name)
);

CREATE TABLE Publisher
(
	    publisher_name VARCHAR(20) NOT NULL,
	    PRIMARY KEY (publisher_name)
);

CREATE TABLE Location
(
	loc_name VARCHAR(100) NOT NULL,
	PRIMARY KEY (loc_name)
);


-- =============
-- Relationships
-- =============

CREATE TABLE SequelOf
(
	    main_game INTEGER NOT NULL,
	    sequel_game INTEGER NOT NULL,
	    PRIMARY KEY (main_game, sequel_game),
	    FOREIGN KEY (main_game) REFERENCES Game(gameID),
	    FOREIGN KEY (sequel_game) REFERENCES Game(gameID)
);

CREATE TABLE Friend
(
	    main_name VARCHAR(30) NOT NULL,
	    friend_name VARCHAR(30) NOT NULL,
	    PRIMARY KEY (main_name, friend_name),
	    FOREIGN KEY (main_name) REFERENCES User(username),
	    FOREIGN KEY (friend_name) REFERENCES User(username)
);

CREATE TABLE Blocks
(
	    main_name VARCHAR(30) NOT NULL,
	    blocked_name VARCHAR(30) NOT NULL,
	    PRIMARY KEY (main_name, blocked_name),
	    FOREIGN KEY (main_name) REFERENCES User(username),
	    FOREIGN KEY (blocked_name) REFERENCES User(username)
);

CREATE TABLE Buys
(
	    gameID INTEGER NOT NULL,
	    username VARCHAR(30) NOT NULL,
	    purchase_time TIME,
	    PRIMARY KEY (gameID, username),
	    FOREIGN KEY (gameID) REFERENCES Game(gameID),
	    FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE Wishes
(
	    gameID INTEGER NOT NULL,
	    username VARCHAR(30) NOT NULL,
	    PRIMARY KEY (gameID, username),
	    FOREIGN KEY (gameID) REFERENCES Game(gameID),
	    FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE InterestedIn
(
	    genre_name VARCHAR(20) NOT NULL,
	    username VARCHAR(30) NOT NULL,
	    PRIMARY KEY (genre_name, username),
	    FOREIGN KEY (genre_name) REFERENCES Genre(genre_name),
	    FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE Associates
(
	    genre_name VARCHAR(20) NOT NULL,
	    gameID INTEGER NOT NULL,
	    PRIMARY KEY (genre_name, gameID),
	    FOREIGN KEY (genre_name) REFERENCES Genre(genre_name),
	    FOREIGN KEY (gameID) REFERENCES Game(gameID)
);

CREATE TABLE Contracts
(
	    dev_name VARCHAR(20) NOT NULL,
	    publisher_name VARCHAR(20) NOT NULL,
	    monetary_amount DECIMAL(10, 2),
	    duration INTEGER, -- in months
	    PRIMARY KEY (dev_name, publisher_name),
	    FOREIGN KEY (dev_name) REFERENCES GameDeveloper(dev_name),
	    FOREIGN KEY (publisher_name) REFERENCES Publisher(publisher_name)
);

CREATE TABLE Releases
(
	    gameID INTEGER NOT NULL,
	    dev_name VARCHAR(20) NOT NULL,
	    publisher_name VARCHAR(20) NOT NULL,
	    release_time DATE,
	    PRIMARY KEY (gameID, dev_name, publisher_name),
	    FOREIGN KEY (gameID) REFERENCES Game(gameID),
	    FOREIGN KEY (dev_name) REFERENCES GameDeveloper(dev_name),
	    FOREIGN KEY (publisher_name) REFERENCES Publisher(publisher_name)
);

CREATE TABLE Requires
(
	    deviceID VARCHAR(50) NOT NULL,
	    gameID INTEGER NOT NULL,
	    PRIMARY KEY (deviceID, gameID),
	    FOREIGN KEY (deviceID) REFERENCES System(deviceID),
	    FOREIGN KEY (gameID) REFERENCES Game(gameID)
);

CREATE TABLE Available_In
(
	gameID INTEGER NOT NULL,
	loc_name VARCHAR(100) NOT NULL,
	PRIMARY KEY (gameID, loc_name),
	FOREIGN KEY (gameID) REFERENCES Game(gameID),
	FOREIGN KEY (loc_name) REFERENCES Location(loc_name)
);
