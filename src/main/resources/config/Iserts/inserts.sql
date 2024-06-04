-- Usar la base de datos viajefeliz
USE viajefeliz;

-- Insertar datos en la tabla pais_ubicacion
INSERT INTO pais_ubicacion (pais) VALUES ('Colombia');

-- Insertar datos en la tabla ciudad_ubicacion desde el archivo CSV
LOAD DATA INFILE '/var/lib/csv/ciudades.csv'
INTO TABLE ciudad_ubicacion
CHARACTER SET utf8

FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Insertar datos en la tabla region_ubicacion desde el archivo CSV
LOAD DATA INFILE '/var/lib/csv/regiones.csv'
INTO TABLE region_ubicacion
CHARACTER SET utf8

FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Insertar datos en la tabla temporada
INSERT INTO temporada (temporada, porcentaje_aumento) VALUES ('Alta', 0.30);
INSERT INTO temporada (temporada, porcentaje_aumento) VALUES ('Baja', 0.10);

-- Insertar datos en la tabla tipo_propiedad
INSERT INTO tipo_propiedad (tipo) VALUES ('Cabaña');
INSERT INTO tipo_propiedad (tipo) VALUES ('Casa');

-- Insertar datos en la tabla rol_usuario
INSERT INTO rol_usuario (rol) VALUES ('Administrador');
INSERT INTO rol_usuario (rol) VALUES ('Cliente');

-- Insertar datos en la tabla usuario desde el archivo CSV
LOAD DATA INFILE '/var/lib/csv/usuarios.csv'
INTO TABLE usuario
CHARACTER SET utf8
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Insertar datos en la tabla telefonos_usuario desde el archivo CSV
LOAD DATA INFILE '/var/lib/csv/telefonos_usuarios.csv'
INTO TABLE telefonos_usuario
CHARACTER SET utf8

FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Insertar datos en la tabla propiedad desde el archivo CSV
LOAD DATA INFILE '/var/lib/csv/propiedades.csv'
INTO TABLE propiedad
CHARACTER SET utf8

FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;
