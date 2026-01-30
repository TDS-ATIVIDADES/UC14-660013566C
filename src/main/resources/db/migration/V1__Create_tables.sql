-- Migration: Create initial tables for Cinema application
-- Version: 1.0
-- Description: Creates tables for Filme and Analise entities

CREATE DATABASE IF NOT EXISTS cinema_db;

USE cinema_db;

CREATE TABLE filme (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    sinopse TEXT,
    genero VARCHAR(100),
    ano_lancamento INT
);

CREATE TABLE analise (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    analise TEXT,
    nota INT NOT NULL CHECK (nota >= 1 AND nota <= 5),
    filme_id BIGINT NOT NULL,
    FOREIGN KEY (filme_id) REFERENCES filme(id) ON DELETE CASCADE
);