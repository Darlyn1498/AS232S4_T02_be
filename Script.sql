CREATE TABLE Product (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    description VARCHAR2(1000),
    price NUMBER(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_plato VARCHAR2(100),
    tiempo_preparacion VARCHAR2(255),  -- Cambiado para permitir texto y número
    estado VARCHAR2(20) DEFAULT 'A'    -- 'A' para activo, por ejemplo
);
