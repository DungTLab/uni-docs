CREATE DATABASE Q2;
GO
USE Q2;
GO

-- 1. Strong Entity: Artist
CREATE TABLE Artist (
    artist_id VARCHAR(10) PRIMARY KEY,
    artist_name VARCHAR(100) NOT NULL
);

-- 2. Weak Entity: Album (owned by Artist)
CREATE TABLE Album (
    artist_id VARCHAR(10) NOT NULL,
    album_id VARCHAR(10) NOT NULL,
    album_name VARCHAR(100) NOT NULL,
    
    PRIMARY KEY (artist_id, album_id),
    
    FOREIGN KEY (artist_id)
        REFERENCES Artist(artist_id)
);

-- 3. Weak Entity: Track (owned by Album)
CREATE TABLE Track (
    artist_id VARCHAR(10) NOT NULL,
    album_id VARCHAR(10) NOT NULL,
    track_id VARCHAR(10) NOT NULL,
    
    track_name VARCHAR(100) NOT NULL,
    time INT NOT NULL,   -- duration in seconds
    
    PRIMARY KEY (artist_id, album_id, track_id),
    
    FOREIGN KEY (artist_id, album_id)
        REFERENCES Album(artist_id, album_id)
);

-- 4. Weak Entity: Played (owned by Track)
CREATE TABLE Played (
    artist_id VARCHAR(10) NOT NULL,
    album_id VARCHAR(10) NOT NULL,
    track_id VARCHAR(10) NOT NULL,
    played DATETIME NOT NULL,
    
    PRIMARY KEY (artist_id, album_id, track_id, played),
    
    FOREIGN KEY (artist_id, album_id, track_id)
        REFERENCES Track(artist_id, album_id, track_id)
);