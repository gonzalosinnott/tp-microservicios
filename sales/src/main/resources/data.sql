-- Insert sample data for Sales
-- Assumes Employee IDs, Car IDs, and Client IDs from other services exist
INSERT INTO Sale (employee_id, car_id, client_id, amount, sale_date) VALUES
(2, 1, 1, 55000.00, '2024-05-10'), 
(3, 3, 2, 35000.00, '2024-04-20'), 
(4, 5, 3, 45000.00, '2024-03-15'), 
(5, 2, 4, 65000.00, '2024-02-28'), 
(6, 4, 5, 75000.00, '2024-01-20'), 
(7, 1, 6, 85000.00, '2023-12-10'), 
(8, 3, 7, 95000.00, '2023-11-25'), 
(9, 5, 8, 105000.00, '2023-10-15'), 
(10, 2, 9, 115000.00, '2023-09-30'), 
(3, 4, 10, 125000.00, '2023-08-25'); 

-- Insert sample data for Repairs
-- Assumes Sale IDs exist from the records above
INSERT INTO Repair (employee_id, client_id, sale_id, vehicle_km, repair_date) VALUES
(4, 1, 1, 1500.5, '2024-07-01'), 
(5, 2, 2, 2500.0, '2024-06-15'), 
(6, 3, 3, 3500.5, '2024-05-20'),
(7, 4, 4, 4500.0, '2024-04-15'), 
(8, 5, 5, 5500.5, '2024-03-10'), 
(9, 1, 6, 6500.0, '2024-02-25'), 
(10, 2, 7, 7500.5, '2024-01-20'), 
(11, 3, 8, 8500.0, '2023-12-15'),
(12, 4, 9, 9500.5, '2023-11-10'), 
(13, 5, 10, 10500.0, '2023-10-05');
