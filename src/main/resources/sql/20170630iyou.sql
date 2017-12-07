ALTER TABLE warehouse_order
ADD COLUMN warehouse_manager_id INT(11) COMMENT '配送员id';
ALTER TABLE warehouse_order ADD CONSTRAINT warehouse_manager_id
FOREIGN KEY (warehouse_manager_id) 
REFERENCES staff(staff_id);
ALTER TABLE warehouse_order
ADD COLUMN warehouse_manager_name varchar(20) COMMENT '配送员名字';