-- Datos iniciales para la base de datos viajefeliz en MySQL
    USE viajefeliz;
-- Datos tabla ciudad_ubicacion
INSERT INTO pais_ubicacion (pais) VALUES ('Colombia');
-- Datos tabla ciudad_ubicacion
    LOAD DATA INFILE '/var/lib/csv/ciudades.csv'
    INTO TABLE ciudad_ubicacion
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;
-- Datos tabla region_ubicacion
    LOAD DATA INFILE '/var/lib/csv/regiones.csv'
    INTO TABLE region_ubicacion
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;
-- Datos tabla temporada
INSERT INTO temporada (temporada,porcentaje_aumento) VALUES ('Alta', 0.30);
INSERT INTO temporada (temporada,porcentaje_aumento) VALUES ('Baja', 0.10);
-- Datos tabla tipo_propiedad
INSERT INTO tipo_propiedad (tipo) VALUES ('Cabaña');
INSERT INTO tipo_propiedad (tipo) VALUES ('Casa');
-- Datos tabla rol_usuario
INSERT INTO rol_usuario (rol) VALUES ('Administrador');
INSERT INTO rol_usuario (rol) VALUES ('Cliente');
-- Datos tabla usuario
   LOAD DATA INFILE '/var/lib/csv/usuarios.csv'
    INTO TABLE usuario
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;
-- Datos tabla telefonos_usuario
    LOAD DATA INFILE '/var/lib/csv/telefonos_usuarios.csv'
    INTO TABLE telefonos_usuario
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;
-- Datos tabla propiedad
    LOAD DATA INFILE '/var/lib/csv/propiedades.csv'
    INTO TABLE propiedad
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;


