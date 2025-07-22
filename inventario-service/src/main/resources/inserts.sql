-- 📦 Bodegas
INSERT INTO bodega (id, nombre, direccion) VALUES
  ('11111111-1111-1111-1111-111111111111', 'Bodega Central', 'Calle 123, Bogotá');
INSERT INTO bodega (id, nombre, direccion) VALUES
  ('22222222-2222-2222-2222-222222222222', 'Bodega Norte', 'Av. 45, Medellín');

-- 📦 Presentaciones
-- Producto A: Arroz Diana
INSERT INTO presentacion (id, producto_id, unidad_medida, cantidad_por_empaque, cantidad_empaques, descripcion) VALUES
  ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'e4d17a7e-4b2e-4b19-813d-3acbe2afbd1a', 'KILOGRAMO', 1, 50, 'Paquete de 1kg Arroz Diana');
INSERT INTO presentacion (id, producto_id, unidad_medida, cantidad_por_empaque, cantidad_empaques, descripcion) VALUES
  ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'e4d17a7e-4b2e-4b19-813d-3acbe2afbd1a', 'KILOGRAMO', 5, 20, 'Paquete de 5kg Arroz Diana');

-- Producto B: Aceite Ideal
INSERT INTO presentacion (id, producto_id, unidad_medida, cantidad_por_empaque, cantidad_empaques, descripcion) VALUES
  ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'c0a4a229-15ef-4e01-9c90-92a6aa164df4', 'LITRO', 2, 30, 'Botella de 2L Aceite Ideal');

-- 📦 Inventario con UUIDs válidos
INSERT INTO inventario (id, bodega_id, presentacion_id, cantidad) VALUES
  ('f381d066-cddf-4c09-8a6e-6e86d8be3d01', '11111111-1111-1111-1111-111111111111', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 50);
INSERT INTO inventario (id, bodega_id, presentacion_id, cantidad) VALUES
  ('b3abf8e4-7e31-4764-b4c4-e1781fc3a43b', '11111111-1111-1111-1111-111111111111', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 20);
INSERT INTO inventario (id, bodega_id, presentacion_id, cantidad) VALUES
  ('a9e22c44-1d54-4fd7-8c96-3f1439d4fa13', '22222222-2222-2222-2222-222222222222', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 30);