-- Insert sample data for Branchs
-- Corrected column names to match Hibernate's snake_case strategy
INSERT INTO Branch (name, country, province, city, address, opening_date, delivery_time_from_central_warehouse, delivery_time_from_branch) VALUES
('Casa Central', 'ARGENTINA', 'Buenos Aires', 'La Plata', 'Avenida Siempreviva 742', '2020-01-15', 2, 0),
('Sucursal Norte', 'ARGENTINA', 'Salta', 'Salta Capital', 'Calle Falsa 123', '2021-03-20', 10, 1),
('Sucursal Cordillera', 'ARGENTINA', 'Mendoza', 'Mendoza Capital', 'Calle Falsa 123', '2021-03-20', 5, 1),
('Sucursal Centro', 'ARGENTINA', 'Rosario', 'Rosario Capital', 'Calle Falsa 123', '2021-03-20', 3, 1),
('Sucursal Sur', 'ARGENTINA', 'Ushuaia', 'Ushuaia Capital', 'Calle Falsa 123', '2021-03-20', 15, 1);
