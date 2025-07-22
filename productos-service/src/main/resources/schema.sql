DROP TABLE IF EXISTS productos;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE productos (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT NOT NULL,
  precio NUMERIC(10,2) NOT NULL
);

-- Datos de prueba
INSERT INTO productos (id, nombre, descripcion, precio) VALUES
  ('11111111-1111-1111-1111-111111111111', 'Laptop', 'Laptop empresarial con 16GB de RAM', 4500.00),
  ('22222222-2222-2222-2222-222222222222', 'Mouse', 'Mouse inalámbrico Logitech', 80.50),
  ('33333333-3333-3333-3333-333333333333', 'Monitor', 'Monitor LED 24 pulgadas', 1200.00);

  -- Arroz Diana
  INSERT INTO productos (id, nombre, descripcion, precio) VALUES
    ('e4d17a7e-4b2e-4b19-813d-3acbe2afbd1a', 'Arroz Diana', 'Paquete de arroz tradicional colombiano', 2500.00);

  -- Aceite Ideal
  INSERT INTO productos (id, nombre, descripcion, precio) VALUES
    ('c0a4a229-15ef-4e01-9c90-92a6aa164df4', 'Aceite Ideal', 'Aceite vegetal para cocina', 4200.00);

