-- Insert sample data for Employees
-- Assuming Branch IDs 1, 2, 3 exist from the branchs service data
INSERT INTO Employee (name, last_name, identity_id, role, branch_id) VALUES
('Carlos', 'Bianchi', 15123456, 'MANAGER', 1),
('Martin', 'Palermo', 22987654, 'SELLER', 1),
('Ana', 'Garcia', 35111222, 'SELLER', 2),
('Luis', 'Martinez', 33444555, 'MECHANIC', 3); 