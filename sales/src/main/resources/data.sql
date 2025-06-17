-- Insert sample data for Sales
-- Assumes Employee IDs, Car IDs, and Client IDs from other services exist
INSERT INTO Sale (employee_id, car_id, client_id, amount, sale_date) VALUES
(2, 1, 1, 55000.00, '2024-05-10'), -- Martin Palermo sells a Mustang to Lionel Messi
(3, 3, 2, 35000.00, '2024-04-20'); -- Ana Garcia sells a RAV4 to Juan Roman Riquelme

-- Insert sample data for Repairs
-- Assumes Sale IDs exist from the records above
INSERT INTO Repair (employee_id, client_id, sale_id, vehicle_km, repair_date) VALUES
(4, 1, 1, 1500.5, '2024-07-01'); -- Luis Martinez services Lionel Messi's Mustang from the first sale 