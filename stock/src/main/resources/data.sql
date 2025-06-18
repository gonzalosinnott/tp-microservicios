-- Insert sample data for the Car Catalog
INSERT INTO Car (brand, model, fabrication_year, type) VALUES
('Ford', 'Mustang', 2023, 'NEW'),
('Tesla', 'Model S', 2024, 'NEW'),
('Toyota', 'RAV4', 2022, 'USED'),
('Ford', 'F-150', 2021, 'USED'),
('Chevrolet', 'Camaro', 2023, 'NEW');

-- Insert sample data for Inventory
-- This links Cars to Branches and sets a stock quantity
-- Assumes Car IDs 1-4 and Branch IDs 1-3 exist
INSERT INTO Inventory (car_id, branch_id, stock) VALUES
(1, 1, 5),   -- 5 Mustangs at Casa Central
(2, 1, 3),   -- 3 Model S at Casa Central
(3, 2, 10),  -- 10 RAV4s at Sucursal Norte
(4, 2, 8),   -- 8 F-150s at Sucursal Norte
(1, 3, 2),   -- 2 Mustangs at Sucursal Cordillera
(3, 3, 7),   -- 7 RAV4s at Sucursal Cordillera 
(5, 3, 10),  -- 10 Camaros at Sucursal Cordillera 
(1, 4, 10),  -- 10 Mustangs at Sucursal Centro
(2, 4, 8),   -- 8 Model S at Sucursal Centro
(3, 4, 5),   -- 5 RAV4s at Sucursal Centro
(4, 4, 3),   -- 3 F-150s at Sucursal Centro
(5, 4, 10);  -- 10 Camaros at Sucursal Centro

