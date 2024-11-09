DROP DATABASE IF EXISTS Empresa;
CREATE DATABASE Empresa;
USE Empresa;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(500) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20)
);

CREATE TABLE transacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(500) NOT NULL,
    fecha DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    tipo ENUM('deposito', 'retiro') NOT NULL
);