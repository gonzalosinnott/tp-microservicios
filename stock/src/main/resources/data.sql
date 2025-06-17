-- Insert sample data for the Car Catalog
INSERT INTO Car (brand, model, fabrication_year, type) VALUES
('Ford', 'Mustang', 2023, 'COUPE'),
('Tesla', 'Model S', 2024, 'SEDAN'),
('Toyota', 'RAV4', 2022, 'SUV'),
('Ford', 'F-150', 2021, 'PICKUP');

-- Insert sample data for Inventory
-- This links Cars to Branches and sets a stock quantity
-- Assumes Car IDs 1-4 and Branch IDs 1-3 exist
INSERT INTO Inventory (car_id, branch_id, stock) VALUES
(1, 1, 5),   -- 5 Mustangs at Sucursal Central
(2, 1, 3),   -- 3 Model S at Sucursal Central
(3, 2, 10),  -- 10 RAV4s at Sucursal Norte
(4, 2, 8),   -- 8 F-150s at Sucursal Norte
(1, 3, 2),   -- 2 Mustangs at Sucursal Oeste
(3, 3, 7);   -- 7 RAV4s at Sucursal Oeste 