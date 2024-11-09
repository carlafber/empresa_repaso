DROP DATABASE IF EXISTS Empresa;
CREATE DATABASE Empresa;
USE Empresa;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(500) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20)
) AUTO_INCREMENT=1;

CREATE TABLE transacciones (
    id_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(500) NOT NULL,
    fecha DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    tipo ENUM('deposito', 'retiro') NOT NULL
) AUTO_INCREMENT=1;


INSERT INTO cliente (nombre, apellidos, email, telefono) VALUES
    ('Juan', 'Pérez López', 'juan.perez@example.com', '123456789'),
    ('María', 'González Ruiz', 'maria.gonzalez@example.com', '987654321'),
    ('Carlos', 'Sánchez Martínez', 'carlos.sanchez@example.com', '456123789'),
    ('Ana', 'López Gómez', 'ana.lopez@example.com', '789123456');


INSERT INTO transacciones (cliente, fecha, valor, tipo) VALUES
    (1, '2024-11-01', 500.00, 'DEPOSITO'),
    (1, '2024-11-02', 200.00, 'RETIRO'),
    (2, '2024-11-03', 1000.00, 'DEPOSITO'),
    (3, '2024-11-04', 750.00, 'DEPOSITO'),
    (3, '2024-11-05', 300.00, 'RETIRO'),
    (4, '2024-11-06', 450.00, 'DEPOSITO'),
    (4, '2024-11-07', 150.00, 'RETIRO');
