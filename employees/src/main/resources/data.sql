-- Insert sample data for Employees
-- Assuming Branch IDs 1, 2, 3, 4 and 5 exist from the branchs service data
INSERT INTO Employee (name, last_name, identity_id, role, branch_id) VALUES
('Carlos', 'Bianchi', 15123456, 'MANAGER', 1),
('Martin', 'Palermo', 22987654, 'SELLER', 1),
('Luis', 'Martinez', 33444555, 'MECHANIC', 1),
('Ana', 'Garcia', 35111222, 'MANAGER', 2),
('Juan', 'Perez', 12345678, 'SELLER', 2),
('Maria', 'Gomez', 87654321, 'MECHANIC', 2),
('Juan', 'Garcia', 11223344, 'MANAGER', 3),
('Maria', 'Lopez', 55667788, 'SELLER', 3),
('Pedro', 'Martinez', 99001122, 'MECHANIC', 3),
('Maria', 'Hernandez', 33445566, 'MANAGER', 4),
('Juan', 'Rodriguez', 77889900, 'SELLER', 4),
('Maria', 'Lopez', 11223345, 'MECHANIC', 4),
('Juan', 'Garcia', 55667789, 'MANAGER', 5),
('Maria', 'Lopez', 55667790, 'SELLER', 5),
('Juan', 'Martinez', 99001123, 'MECHANIC', 5);


