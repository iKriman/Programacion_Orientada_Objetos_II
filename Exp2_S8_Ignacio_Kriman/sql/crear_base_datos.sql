-- Script simple para crear la base de datos del Sistema Cine Magenta
-- Exp2_S7_Grupo14 - Bimestre 3

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS Cine_DB;

-- Seleccionar la base de datos
USE Cine_DB;

DROP TABLE Cartelera;

-- Crear tabla Cartelera
CREATE TABLE Cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    genero VARCHAR(20) NOT NULL
);

-- Insertar datos de prueba
INSERT INTO Cartelera (titulo, director, anio, duracion, genero) VALUES
('Avengers', 'Joss Whedon', 2012, 143, 'Acción'),
('Titanic', 'James Cameron', 1997, 195, 'Romance'),
('El Rey León', 'Roger Allers', 1994, 88, 'Animación'),
('Jurassic Park', 'Steven Spielberg', 1993, 127, 'Aventura'),
('Scary Movie', 'Keenen Wayans', 2000, 88, 'Comedia'),
('El Conjuro', 'James Wan', 2013, 112, 'Terror');

-- Consultar los datos insertados
SELECT * FROM Cartelera;