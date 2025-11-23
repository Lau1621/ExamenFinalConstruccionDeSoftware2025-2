-- ============================================
-- DUMP DE BASE DE DATOS - SISTEMA DE PILARES
-- Castillo Infinito - Demon Slayer
-- ============================================

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS examen_final
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE examen_final;

-- ============================================
-- ELIMINACIÓN DE TABLAS (si existen)
-- ============================================

DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS pillars;

-- ============================================
-- TABLA: pillars
-- ============================================

CREATE TABLE pillars (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    pos_x INT NOT NULL,
    pos_y INT NOT NULL,
    state VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    INDEX idx_state (state),
    INDEX idx_position (pos_x, pos_y)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLA: messages
-- ============================================

CREATE TABLE messages (
    id BIGINT NOT NULL AUTO_INCREMENT,
    pillar_id BIGINT NOT NULL,
    content_fragmented TEXT NOT NULL,
    content_rebuilt TEXT,
    timestamp DATETIME(6) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_messages_pillar 
        FOREIGN KEY (pillar_id) 
        REFERENCES pillars (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    INDEX idx_pillar_id (pillar_id),
    INDEX idx_timestamp (timestamp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- DATOS INICIALES: pillars
-- ============================================

INSERT INTO pillars (id, name, pos_x, pos_y, state) VALUES
(1, 'Giyu Tomioka', -500, -200, 'Combatiendo'),
(2, 'Shinobu Kocho', 300, 150, 'Explorando'),
(3, 'Kyojuro Rengoku', -100, 400, 'Herido'),
(4, 'Tengen Uzui', 200, -300, 'Combatiendo'),
(5, 'Muichiro Tokito', 0, 0, 'Explorando'),
(6, 'Mitsuri Kanroji', -200, 250, 'Combatiendo'),
(7, 'Obanai Iguro', 400, -100, 'Explorando'),
(8, 'Sanemi Shinazugawa', -350, -50, 'Combatiendo'),
(9, 'Gyomei Himejima', 100, 300, 'Combatiendo');

-- ============================================
-- VERIFICACIÓN
-- ============================================

SELECT 'Dump completado exitosamente' AS status;
SELECT COUNT(*) AS total_pilares FROM pillars;

