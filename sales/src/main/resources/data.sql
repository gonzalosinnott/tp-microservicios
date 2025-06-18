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
