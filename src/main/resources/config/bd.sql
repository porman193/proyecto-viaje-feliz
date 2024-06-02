DROP DATABASE IF EXISTS viajefeliz;

CREATE DATABASE IF NOT EXISTS viajefeliz;

USE viajefeliz;

CREATE TABLE IF NOT EXISTS rol_usuario (
    id_rol INT AUTO_INCREMENT,
    rol VARCHAR(30) NOT NULL,
    PRIMARY KEY(id_rol),
    UNIQUE KEY unique_rol (rol)
);
CREATE TABLE IF NOT EXISTS usuario (
    identificacion INT NOT NULL,
    direccion VARCHAR(30),
    nacionalidad VARCHAR(30),
    nombres VARCHAR(30) NOT NULL,
    apellidos VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    id_rol INT NOT NULL,
    UNIQUE KEY unique_email (email),
    PRIMARY KEY(identificacion),
    FOREIGN KEY (id_rol)
    REFERENCES rol_usuario(id_rol)
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
    temporada VARCHAR(30) NOT NULL,
    porcentaje_aumento FLOAT NOT NULL,
    PRIMARY KEY(id_temporada, temporada),
    UNIQUE KEY unique_temporada (temporada)
);

CREATE TABLE IF NOT EXISTS region_ubicacion (
    id_region_ubi INT AUTO_INCREMENT ,
    region VARCHAR(30) NOT NULL,
    PRIMARY KEY(id_region_ubi),
    UNIQUE KEY unique_region (region)
);
CREATE TABLE IF NOT EXISTS ciudad_ubicacion (
    id_ciudad_ubi INT AUTO_INCREMENT,
    ciudad VARCHAR(30) NOT NULL,
    PRIMARY KEY(id_ciudad_ubi),
    UNIQUE KEY unique_ciudad (ciudad)
);
CREATE TABLE IF NOT EXISTS pais_ubicacion (
    id_pais_ubi INT AUTO_INCREMENT,
    pais VARCHAR(30) NOT NULL,
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
    ubicacion_direccion VARCHAR(30) NOT NULL,
    id_tipo_propiedad INT NOT NULL,
    id_pais_ubi INT NOT NULL,
    id_ciudad_ubi INT NOT NULL,
    id_region_ubi INT NOT NULL,
    capacidad INT NOT NULL,
    PRIMARY KEY (id_propiedad),
    FOREIGN KEY (id_tipo_propiedad)
 REFERENCES tipo_propiedad(id_tipo_propiedad),
    FOREIGN KEY (id_pais_ubi)
 REFERENCES pais_ubicacion(id_pais_ubi),
    FOREIGN KEY (id_ciudad_ubi)
 REFERENCES ciudad_ubicacion(id_ciudad_ubi),
    FOREIGN KEY (id_region_ubi)
 REFERENCES region_ubicacion(id_region_ubi)
);

CREATE TABLE IF NOT EXISTS fotografia (
    id_fotografia INT NOT NULL,
    id_propiedad INT NOT NULL,
    fotografia longblob NOT NULL,
    PRIMARY KEY(id_fotografia),
    FOREIGN KEY (id_propiedad)
    REFERENCES propiedad(id_propiedad)
);
CREATE TABLE  IF NOT EXISTS reserva (
    id_reserva INT AUTO_INCREMENT,
    comentarios_encuesta VARCHAR(255),
    calif_encuesta INT,
    fecha_ini DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    precio_total FLOAT NOT NULL,
    mascotas BOOLEAN,
    num_personas INT NOT NULL,
    id_usuario INT NOT NULL,
    id_temporada INT NOT NULL,
    id_propiedad INT NOT NULL,
    PRIMARY KEY(id_reserva),
    FOREIGN KEY (id_usuario)
 REFERENCES usuario(identificacion),
    FOREIGN KEY (id_temporada)
 REFERENCES temporada(id_temporada),
    FOREIGN KEY (id_propiedad)
REFERENCES propiedad(id_propiedad)
);

CREATE TABLE IF NOT EXISTS pagos (
    id_pago INT AUTO_INCREMENT,
    fecha_pago DATE NOT NULL,
    id_reserva INT NOT NULL,
    monto FLOAT NOT NULL,
    metodo_pago VARCHAR(30) NOT NULL,
    PRIMARY KEY(id_pago, id_reserva),
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

-- Vistas
CREATE VIEW vista_propiedades_mas_reservadas AS
SELECT
    p.id_propiedad,
    p.num_habitaciones,
    p.precio_base,
    p.acepta_mascotas,
    p.num_banos,
    p.calefaccion,
    p.aire_acondicionado,
    p.ubicacion_direccion,
    tp.tipo,
    pa.pais,
    ci.ciudad,
    re.region,
    p.capacidad,
    COUNT(r.id_reserva) AS numero_reservas
FROM
    propiedad p
        JOIN
    reserva r ON p.id_propiedad = r.id_propiedad
        JOIN
    tipo_propiedad tp ON p.id_tipo_propiedad = tp.id_tipo_propiedad
        JOIN
    pais_ubicacion pa ON p.id_pais_ubi = pa.id_pais_ubi
        JOIN
    ciudad_ubicacion ci ON p.id_ciudad_ubi = ci.id_ciudad_ubi
        JOIN
    region_ubicacion re ON p.id_region_ubi = re.id_region_ubi
GROUP BY
    p.id_propiedad, p.num_habitaciones, p.precio_base, p.acepta_mascotas,
    p.num_banos, p.calefaccion, p.aire_acondicionado, p.ubicacion_direccion,
    tp.tipo, pa.pais, ci.ciudad, re.region, p.capacidad
ORDER BY
    numero_reservas DESC;


ALTER TABLE reserva
    ADD COLUMN estado ENUM('pendiente', 'confirmada','apartada') DEFAULT 'pendiente';
 -- Procedimientos almacenados

DELIMITER //

CREATE PROCEDURE actualizar_estado_reserva(IN p_id_reserva INT)
BEGIN
    DECLARE moton_total FLOAT;
    DECLARE monto_pagado FLOAT;
    DECLARE porcentaje_pagado FLOAT;

    -- Obtener el monto total de la reserva
    SELECT precio_total INTO moton_total FROM reserva WHERE id_reserva = p_id_reserva;
    -- obtener el monto pagado de la reserva
    SELECT SUM(monto) INTO monto_pagado FROM pagos WHERE id_reserva = p_id_reserva;
    -- Calcular el porcentaje pagado
    SET porcentaje_pagado = (monto_pagado / moton_total)*100;

    -- Actualizar el estado de la reserva
    IF porcentaje_pagado = 100 THEN
        UPDATE reserva SET estado = 'confirmada' WHERE id_reserva = p_id_reserva;
    END IF;

    IF porcentaje_pagado <= 20 THEN
        UPDATE reserva SET estado = 'apartada' WHERE id_reserva = p_id_reserva;
    END IF;

    IF porcentaje_pagado < 20 AND porcentaje_pagado < 100 THEN
        UPDATE reserva SET estado = 'pendiente' WHERE id_reserva = p_id_reserva;
    END IF;
END //
DELIMITER //
 -- Trigger

DELIMITER //
CREATE TRIGGER after_insert_pago
    AFTER INSERT ON pagos
    FOR EACH ROW
BEGIN
    -- Llamar al procedimiento para actualizar el estado de la reserva
    CALL actualizar_estado_reserva(NEW.id_reserva);
END //

DELIMITER //


