DROP DATABASE IF EXISTS viajefeliz;

CREATE DATABASE IF NOT EXISTS viajefeliz;
USE viajefeliz;

CREATE TABLE IF NOT EXISTS usuario (
    identificacion INT NOT NULL,
    direccion VARCHAR(255),
    nacionalidad VARCHAR(100),
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    PRIMARY KEY(identificacion)
);
CREATE TABLE IF NOT EXISTS telefonos_usuario (
    id_usuario INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_usuario, telefono),
    FOREIGN KEY (id_usuario)
 REFERENCES usuario(identificacion)
);
CREATE TABLE IF NOT EXISTS temporada (
    id_temporada INT AUTO_INCREMENT,
    temporada VARCHAR(255) NOT NULL,
    porcentaje_aumento FLOAT NOT NULL,
    PRIMARY KEY(id_temporada, temporada),
    UNIQUE KEY unique_temporada (temporada)
);
CREATE TABLE IF NOT EXISTS pagos (
    id_pago INT AUTO_INCREMENT,
    fecha_pago DATE NOT NULL,
    monto FLOAT NOT NULL,
    metodo_pago VARCHAR(255) NOT NULL,
    PRIMARY KEY(id_pago, fecha_pago)
);
CREATE TABLE IF NOT EXISTS fotografia (
    id_fotografia INT NOT NULL,
    fotografia longblob NOT NULL,
    PRIMARY KEY(id_fotografia)
);
CREATE TABLE IF NOT EXISTS region_ubicacion (
    id_region_ubi INT AUTO_INCREMENT ,
    region VARCHAR(255) NOT NULL,
    PRIMARY KEY(id_region_ubi),
    UNIQUE KEY unique_region (region)
);
CREATE TABLE IF NOT EXISTS ciudad_ubicacion (
    id_ciudad_ubi INT AUTO_INCREMENT,
    ciudad VARCHAR(255) NOT NULL,
    PRIMARY KEY(id_ciudad_ubi),
    UNIQUE KEY unique_ciudad (ciudad)
);
CREATE TABLE IF NOT EXISTS pais_ubicacion (
    id_pais_ubi INT AUTO_INCREMENT,
    pais VARCHAR(255) NOT NULL,
    PRIMARY KEY(id_pais_ubi),
    UNIQUE KEY unique_pais (pais)
);
 CREATE TABLE IF NOT EXISTS tipo_propiedad (
    id_tipo_propiedad INT AUTO_INCREMENT,
    tipo VARCHAR(55) NOT NULL,
    PRIMARY KEY (id_tipo_propiedad),
    UNIQUE KEY unique_tipo (tipo)
);
CREATE TABLE IF NOT EXISTS propiedad (
    id_propiedad INT NOT NULL,
    num_habitaciones INT NOT NULL,
    precio_base FLOAT NOT NULL,
    acepta_mascotas BOOLEAN,
    num_banos INT NOT NULL,
    calefaccion BOOLEAN,
    aire_acondicionado BOOLEAN,
    ubicacion_direccion VARCHAR(255) NOT NULL,
    id_tipo_propiedad INT NOT NULL,
    id_pais_ubi INT NOT NULL,
    id_ciudad_ubi INT NOT NULL,
    id_region_ubi INT NOT NULL,
    id_fotografia INT NOT NULL,
    capacidad INT NOT NULL,
    PRIMARY KEY (id_propiedad),
    FOREIGN KEY (id_tipo_propiedad)
 REFERENCES tipo_propiedad(id_tipo_propiedad),
    FOREIGN KEY (id_pais_ubi)
 REFERENCES pais_ubicacion(id_pais_ubi),
    FOREIGN KEY (id_ciudad_ubi)
 REFERENCES ciudad_ubicacion(id_ciudad_ubi),
    FOREIGN KEY (id_region_ubi)
 REFERENCES region_ubicacion(id_region_ubi),
    FOREIGN KEY (id_fotografia)
 REFERENCES fotografia(id_fotografia)
);
CREATE TABLE  IF NOT EXISTS reserva (
    id_reserva INT AUTO_INCREMENT,
    comentarios_encuesta VARCHAR(255),
    calif_encuesta INT NOT NULL,
    fecha_ini DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    precio_total FLOAT NOT NULL,
    mascotas BOOLEAN,
    num_personas INT NOT NULL,
    id_usuario INT NOT NULL,
    id_temporada INT NOT NULL,
    id_pago INT NOT NULL,
    PRIMARY KEY(id_reserva),
    FOREIGN KEY (id_usuario)
 REFERENCES usuario(identificacion),
    FOREIGN KEY (id_temporada)
 REFERENCES temporada(id_temporada),
    FOREIGN KEY (id_pago)
 REFERENCES pagos(id_pago)
);

INSERT INTO region_ubicacion (region) VALUES ('Caribe');
INSERT INTO region_ubicacion (region) VALUES ('Andina');
INSERT INTO region_ubicacion (region) VALUES ('Pacífica');

INSERT INTO ciudad_ubicacion (ciudad) VALUES ('Barranquilla');
INSERT INTO ciudad_ubicacion (ciudad) VALUES ('Medellín');
INSERT INTO ciudad_ubicacion (ciudad) VALUES ('Cali');

INSERT INTO pais_ubicacion (pais) VALUES ('Colombia');

-- Inserta tipo cabaña
INSERT INTO tipo_propiedad (tipo) VALUES ('Cabaña');

-- Inserta tipo casa
INSERT INTO tipo_propiedad (tipo) VALUES ('Casa');

INSERT INTO propiedad (id_propiedad, num_habitaciones, precio_base, acepta_mascotas, num_banos, calefaccion, aire_acondicionado, ubicacion_direccion, id_tipo_propiedad, id_pais_ubi, id_ciudad_ubi, id_region_ubi, id_fotografia, capacidad)
VALUES (1, 3, 150.00, TRUE, 2, TRUE, FALSE, 'Calle Principal 123', 1, 1, 1, 1, 1, 6);

INSERT INTO usuario (identificacion, direccion, nacionalidad, nombres, apellidos, email, contrasena)
VALUES (123456789, 'Calle Principal 123', 'Colombiano', 'Juan', 'Pérez', 'juan@example.com', 'contraseña123');

INSERT INTO telefonos_usuario (id_usuario, telefono)
VALUES (123456789, '1234567890'); -- Teléfono para el hogar

INSERT INTO telefonos_usuario (id_usuario, telefono)
VALUES (123456789, '0987654321'); -- Teléfono para el trabajo

-- Inserta temporada alta
INSERT INTO temporada (temporada,porcentaje_aumento) VALUES ('Alta', 0.25);

-- Inserta temporada baja
INSERT INTO temporada (temporada,porcentaje_aumento) VALUES ('Baja', 0.10);
