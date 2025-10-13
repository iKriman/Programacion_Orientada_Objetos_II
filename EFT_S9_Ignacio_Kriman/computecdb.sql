DROP DATABASE IF EXISTS computecdb;
CREATE DATABASE computecdb;
USE computecdb;

CREATE TABLE clientes (
    rut VARCHAR(12) PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    comuna VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15) NOT NULL
);

CREATE TABLE equipos (
    id_equipo INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('Laptop', 'Desktop') NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    cpu VARCHAR(100) NOT NULL,
    disco_duro_mb INT NOT NULL,
    ram_gb INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);

CREATE TABLE Desktop (
    id_equipo INT PRIMARY KEY,
    potencia_fuente INT NOT NULL,
    factor_forma VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_equipo) REFERENCES equipos(id_equipo) ON DELETE CASCADE
);

CREATE TABLE Laptop (
    id_equipo INT PRIMARY KEY,
    pantalla_pulgadas DECIMAL(4,2) NOT NULL,
    es_touch BOOLEAN NOT NULL,
    cantidad_puertos_usb INT NOT NULL,
    FOREIGN KEY (id_equipo) REFERENCES equipos(id_equipo) ON DELETE CASCADE
);

CREATE TABLE ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    rut_cliente VARCHAR(12) NOT NULL,
    id_equipo INT NOT NULL,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    precio_base DECIMAL(10,2) NOT NULL,
    precio_final DECIMAL(10,2) NOT NULL,
    descuento_aplicado VARCHAR(100) NOT NULL,
    FOREIGN KEY (rut_cliente) REFERENCES clientes(rut) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_equipo) REFERENCES equipos(id_equipo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE OR REPLACE VIEW vw_equipos_detallados AS
SELECT
    e.id_equipo,
    e.tipo,
    e.modelo,
    e.cpu,
    e.disco_duro_mb,
    e.ram_gb,
    e.precio,
    d.potencia_fuente,
    d.factor_forma,
    l.pantalla_pulgadas,
    l.es_touch,
    l.cantidad_puertos_usb AS puertos_usb 
FROM equipos e
LEFT JOIN Desktop d ON e.id_equipo = d.id_equipo
LEFT JOIN Laptop l ON e.id_equipo = l.id_equipo
ORDER BY e.id_equipo;

CREATE VIEW vw_equipos_vendidos AS
SELECT 
    v.id_venta,
    e.modelo,
    c.nombre_completo AS cliente,
    c.telefono,
    c.correo,
    e.precio,
    e.tipo,
    v.fecha_hora
FROM ventas v
JOIN clientes c ON v.rut_cliente = c.rut
JOIN equipos e ON v.id_equipo = e.id_equipo;

CREATE VIEW vw_resumen_ventas AS
SELECT 
    COUNT(*) AS cantidad_ventas,
    SUM(e.precio) AS total_recaudado
FROM ventas v
JOIN equipos e ON v.id_equipo = e.id_equipo;

DELIMITER $$
	CREATE PROCEDURE sp_cliente_update(IN p_rut VARCHAR(12),
    IN p_nombre VARCHAR(100),
    IN p_direccion VARCHAR(150),
    IN p_comuna VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_telefono VARCHAR(15))
	BEGIN
	      UPDATE clientes
    SET 
        nombre_completo = p_nombre,
        direccion = p_direccion,
        comuna = p_comuna,
        correo = p_correo,
        telefono = p_telefono
    WHERE rut = p_rut;
	END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_cliente_insert(
    IN p_rut VARCHAR(12),
    IN p_nombre VARCHAR(100),
    IN p_direccion VARCHAR(150),
    IN p_comuna VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_telefono VARCHAR(15)
)
BEGIN
    INSERT INTO clientes (rut, nombre_completo, direccion, comuna, correo, telefono)
    VALUES (p_rut, p_nombre, p_direccion, p_comuna, p_correo, p_telefono);
END$$

CREATE PROCEDURE sp_cliente_listar()
BEGIN
    SELECT rut, nombre_completo, direccion, comuna, correo, telefono 
    FROM clientes 
    ORDER BY nombre_completo;
END$$

CREATE PROCEDURE sp_equipo_insert(
    IN p_tipo ENUM('Desktop', 'Laptop'),
    IN p_modelo VARCHAR(100),
    IN p_cpu VARCHAR(100),
    IN p_disco_mb INT,
    IN p_ram_gb INT,
    IN p_precio DECIMAL(10,2),
    IN p_potencia_fuente INT,
    IN p_factor_forma VARCHAR(50), 
    IN p_pantalla DECIMAL(4,1),
    IN p_touch BOOLEAN,
    IN p_usb INT
)
BEGIN
    INSERT INTO equipos (tipo, modelo, cpu, disco_duro_mb, ram_gb, precio)
    VALUES (p_tipo, p_modelo, p_cpu, p_disco_mb, p_ram_gb, p_precio);

    SET @last_id = LAST_INSERT_ID();

    IF p_tipo = 'Desktop' THEN
        INSERT INTO Desktop (id_equipo, potencia_fuente, factor_forma)
        VALUES (@last_id, p_potencia_fuente, p_factor_forma);
    ELSEIF p_tipo = 'Laptop' THEN
        INSERT INTO Laptop (id_equipo, pantalla_pulgadas, es_touch, cantidad_puertos_usb)
        VALUES (@last_id, p_pantalla, p_touch, p_usb);
    END IF;
END$$

CREATE PROCEDURE sp_venta_insert(
    IN p_rut_cliente VARCHAR(12),
    IN p_id_equipo INT,
    IN p_precio_base DECIMAL(10,2),
    IN p_precio_final DECIMAL(10,2),
    IN p_descuento_aplicado VARCHAR(100)
)
BEGIN
    INSERT INTO ventas (rut_cliente, id_equipo, fecha_hora, precio_base, precio_final, descuento_aplicado)
    VALUES (p_rut_cliente, p_id_equipo, NOW(), p_precio_base, p_precio_final, p_descuento_aplicado);
END$$


CREATE PROCEDURE sp_cliente_delete(
    IN p_rut VARCHAR(12)
)
BEGIN
    DELETE FROM clientes
    WHERE rut = p_rut;
END$$

CREATE PROCEDURE sp_equipo_delete(IN p_id_equipo INT)
BEGIN
    DELETE FROM equipos
    WHERE id_equipo = p_id_equipo;
    
END$$

DELIMITER ;


-- inserts de prueba
INSERT INTO clientes (rut, nombre_completo, direccion, comuna, correo, telefono) VALUES 
('11.111.111-1', 'Laura García', 'Av. Central 456, Depto. 10A', 'Santiago', 'laura.g@correo.cl', '+56987654321'),
('12.222.222-2', 'Roberto Soto', 'Calle Los Álamos 23', 'Providencia', 'r.soto@empresa.com', '+56912345678'),
('13.333.333-3', 'Ana Morales', 'Pasaje Flores 101', 'Ñuñoa', 'ana.m@web.net', '+56955554444');

CALL sp_equipo_insert(
    'Desktop',                   -- p_tipo
    'Gamer XTreme',              -- p_modelo
    'Intel i7-13700K',           -- p_cpu
    1000000,                     -- p_disco_mb (1TB SSD)
    32,                          -- p_ram_gb
    1850000.00,                  -- p_precio
    750,                         -- p_potencia_fuente (750W)
    'ATX',                       -- p_factor_forma
    NULL, NULL, NULL             -- Campos de Laptop (NULL)
);


CALL sp_equipo_insert(
    'Laptop',                    -- p_tipo
    'UltraBook Touch 14',        -- p_modelo
    'AMD Ryzen 5 7530U',         -- p_cpu
    512000,                      -- p_disco_mb (512GB SSD)
    16,                          -- p_ram_gb
    750000.00,                   -- p_precio
    NULL, NULL,                  -- Campos de Desktop (NULL)
    14.0,                        -- p_pantalla (14.0 pulgadas)
    TRUE,                        -- p_touch (Es táctil)
    3                            -- p_usb (3 puertos)
);

CALL sp_equipo_insert(
    'Desktop',                   -- p_tipo
    'Home Office Pro',           -- p_modelo
    'Intel i3-10100',            -- p_cpu
    256000,                      -- p_disco_mb (256GB SSD)
    8,                           -- p_ram_gb
    350000.00,                   -- p_precio
    400,                         -- p_potencia_fuente (400W)
    'Micro-ATX',                 -- p_factor_forma
    NULL, NULL, NULL             -- Campos de Laptop (NULL)
);


-- ventas 

CALL sp_venta_insert(
    '11.111.111-1',          -- p_rut_cliente (Laura García)
    2,                       -- p_id_equipo (UltraBook Touch 14 - Precio Base: $750.000)
    750000.00,               -- p_precio_base
    675000.00,               -- p_precio_final ($750.000 - 10%)
    'Descuento 10.00%'       -- p_descuento_aplicado
);

CALL sp_venta_insert(
    '12.222.222-2',          -- p_rut_cliente (Roberto Soto)
    1,                       -- p_id_equipo (Gamer XTreme - Precio Base: $1.850.000)
    1850000.00,              -- p_precio_base
    1850000.00,              -- p_precio_final (Sin descuento)
    'Descuento Base 0.00%'   -- p_descuento_aplicado
);
