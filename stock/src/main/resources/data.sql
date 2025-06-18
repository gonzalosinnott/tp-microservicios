-- Insert sample data for the Car Catalog
INSERT INTO Car (brand, model, fabrication_year, type) VALUES
('Ford', 'Mustang', 2023, 'NEW'),
('Tesla', 'Model S', 2024, 'NEW'),
('Toyota', 'RAV4', 2022, 'USED'),
('Ford', 'F-150', 2021, 'USED'),
('Chevrolet', 'Camaro', 2023, 'NEW');

-- Insert sample data for Inventory
-- Assumes Car IDs 1-4 and Branch IDs 1-3 exist
INSERT INTO Inventory (car_id, branch_id, stock) VALUES
(1, 1, 5),  
(2, 1, 3),   
(3, 2, 10),  
(4, 2, 8),   
(1, 3, 2),   
(3, 3, 7),   
(5, 3, 10),  
(1, 4, 10),  
(2, 4, 8),   
(3, 4, 5),   
(4, 4, 3),   
(5, 4, 10);  

