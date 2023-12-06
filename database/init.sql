CREATE DATABASE IF NOT EXISTS ep4project;
USE ep4project;

CREATE TABLE evento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha VARCHAR(255) NOT NULL,
    hora VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    duracion DOUBLE NOT NULL,
    precio DOUBLE NOT NULL,
    fechaCreacion TIMESTAMP,
    fechaActualizacion TIMESTAMP,
    UNIQUE KEY unique_nombre(nombre)
);


CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    nomCompleto VARCHAR(510),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'USER') NOT NULL,
    UNIQUE KEY unique_email (email)
);


CREATE TABLE facturas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idUsuario BIGINT NOT NULL,
    id_evento BIGINT,
    total DOUBLE,
    fechaCreacion TIMESTAMP,
    FOREIGN KEY (id_evento) REFERENCES evento(id),
    FOREIGN KEY (idUsuario) REFERENCES users(id)
);

INSERT INTO users (nombres, apellidos, nomCompleto, email, password, rol)
VALUES ('Juan', 'Pérez', 'Juan Pérez', 'juan@example.com', 'password123', 'ADMIN');

INSERT INTO evento (nombre, fecha, hora, capacidad, duracion, precio, fechaCreacion, fechaActualizacion)
VALUES ('Concierto de rock', '2023-12-15', '19:00', 500, 2.5, 50.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO evento (nombre, fecha, hora, capacidad, duracion, precio, fechaCreacion, fechaActualizacion)
VALUES ('Feria de arte', '2024-02-20', '10:00', 300, 5.0, 20.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
