-- Supervisors
INSERT INTO supervisors (id, name) VALUES
  (1, 'John Doe'),
  (2, 'Jane Smith'),
  (3, 'Bob Johnson'),
  (4, 'Alice Brown'),
  (5, 'Charlie Wilson');

-- Districts
INSERT INTO districts (id, district_name) VALUES
  (1, 'Mumbai'),
  (2, 'Pune'),
  (3, 'Nagpur'),
  (4, 'Nashik'),
  (5, 'Aurangabad');

-- Blocks
INSERT INTO blocks (id, block_name) VALUES
  (1, 'Block A'),
  (2, 'Block B'),
  (3, 'Block C'),
  (4, 'Block D'),
  (5, 'Block E');

-- Anganwadi Centers
INSERT INTO anganwadi_centers (id, center_id, center_name, center_address, status, supervisor_id, district_id, block_id) VALUES
  (1, 'AWC001', 'Sunrise Center', '123 Main Street, Mumbai', 'active',   1, 1, 1),
  (2, 'AWC002', 'Morning Glory Center', '456 Park Road, Pune', 'inactive', 2, 2, 2),
  (3, 'AWC003', 'Rainbow Center', '789 Garden Lane, Nagpur', 'active',     3, 3, 3),
  (4, 'AWC004', 'Happy Kids Center', '321 School Street, Nashik', 'active',4, 4, 4),
  (5, 'AWC005', 'Little Stars Center', '654 Community Road, Aurangabad', 'inactive', 5, 5, 5);
