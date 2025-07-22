-- Activar extensión para generación de UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Eliminación previa de tablas
DROP TABLE IF EXISTS historial_compra;
DROP TABLE IF EXISTS inventario;
DROP TABLE IF EXISTS presentacion;
DROP TABLE IF EXISTS bodega;

-- Tabla de bodegas
CREATE TABLE bodega (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nombre VARCHAR(100) NOT NULL,
    direccion TEXT
);

-- Tabla de presentaciones
CREATE TABLE presentacion (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    producto_id UUID NOT NULL,
    unidad_medida VARCHAR(50) NOT NULL,
    cantidad_por_empaque INT NOT NULL,
    cantidad_empaques INT NOT NULL,
    descripcion VARCHAR(255),
    UNIQUE(producto_id, unidad_medida, cantidad_por_empaque)
);

-- Tabla de inventario
CREATE TABLE inventario (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bodega_id UUID NOT NULL REFERENCES bodega(id),
    presentacion_id UUID NOT NULL REFERENCES presentacion(id),
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    UNIQUE(bodega_id, presentacion_id)
);

-- Tabla de historial de compras con UUID autogenerado
CREATE TABLE historial_compra (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    inventario_id UUID NOT NULL REFERENCES inventario(id),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cantidad_ingresada INT NOT NULL,
    observaciones TEXT
);
