USE viajefeliz;

-- Datos tabla propiedad
    LOAD DATA INFILE '/var/lib/csv/propiedades.csv'
    INTO TABLE propiedad
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;