DROP DATABASE IF EXISTS `cg`;
CREATE DATABASE `cg`;

USE `cg`;
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`(
	role_id INT AUTO_INCREMENT COMMENT '角色id',
	role_name VARCHAR(100) NOT NULL COMMENT '角色名',
	role_description VARCHAR(100) NOT NULL COMMENT '角色描述',
	PRIMARY KEY(role_id)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT '角色表';

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`(
	customer_id INT AUTO_INCREMENT COMMENT '客户id',
	customer_name VARCHAR(20) NOT NULL COMMENT '客户姓名',
	customer_phone CHAR(11) NOT NULL COMMENT '联系方式',
	customer_address VARCHAR(200) NOT NULL COMMENT '收货地址',
	PRIMARY KEY(customer_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '客户信息';

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
	staff_id INT AUTO_INCREMENT COMMENT'员工id',
    staff_num CHAR(10) NOT NULL COMMENT '员工工号',
    staff_name VARCHAR(20) NOT NULL COMMENT '姓名',
    role_id INT NOT NULL COMMENT '角色id,引用role表',
    staff_state CHAR(2) NOT NULL COMMENT '就职状态:在职/离职',
    staff_sex CHAR(1) NOT NULL COMMENT '性别:男/女',
    staff_native VARCHAR(100) NULL COMMENT '籍贯',
    staff_nation VARCHAR(10) NULL COMMENT '民族',
    staff_marriage CHAR(2) NULL COMMENT '婚姻状况:未婚、已婚、离异',
    staff_address VARCHAR(200) NULL COMMENT '家庭住址',
    staff_entry_date BIGINT NOT NULL COMMENT '入职日期',
    staff_id_num CHAR(18) NOT NULL COMMENT '身份证号',
    staff_tel CHAR(11) NULL COMMENT '手机号码',
    staff_password VARCHAR(256) NOT NULL COMMENT '密码(经过加密后的)',
    PRIMARY KEY(staff_id),
    FOREIGN KEY(role_id) references role(role_id)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT '员工表';

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`(
	permission_id INT AUTO_INCREMENT COMMENT '权限id',
	permission_name VARCHAR(100) NOT NULL COMMENT '权限名',
	permission_description VARCHAR(100) NOT NULL COMMENT '权限描述',
	PRIMARY KEY(permission_id)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT '权限表';

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`(
	id INT AUTO_INCREMENT COMMENT '记录id',
	role_id INT NOT NULL COMMENT '角色id',
	permission_id INT NOT NULL COMMENT '权限id',
	primary key(`id`),
    FOREIGN KEY(role_id) references role(role_id),
    FOREIGN KEY(permission_id) references permission(permission_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '角色-权限表';

DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
	material_id INT AUTO_INCREMENT COMMENT '原料id',
    material_class CHAR(1) NOT NULL COMMENT '原料种类',
    material_inventory FLOAT(7,2) NOT NULL DEFAULT 0.00 COMMENT '原料库存,单位:千克',
    PRIMARY KEY(material_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '原料表';

DROP TABLE IF EXISTS `material_purchase_record`;
CREATE TABLE `material_purchase_record`(
	id INT AUTO_INCREMENT COMMENT '原料采购记录id',
    material_id INT NOT NULL COMMENT'原料id',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '员工名字不能修改，所以用冗余字段',
    purchase_num CHAR(14) NOT NULL COMMENT '采购编号',
    purchase_date BIGINT NOT NULL COMMENT '采购日期',
    purchase_quantity FLOAT(9,2) NOT NULL COMMENT '采购量',
    unit_price FLOAT(9,2) NOT NULL COMMENT '单价',
    total_price FLOAT(20,2) NOT NULL COMMENT '总价',
    PRIMARY KEY(id),
    FOREIGN KEY(material_id) references material(material_id),
    FOREIGN KEY(staff_id) references staff(staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '原料采购记录';

DROP TABLE IF EXISTS `product_model_info`;
CREATE TABLE `product_model_info`(
	id INT AUTO_INCREMENT COMMENT '产品型号信息id',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '员工名字不能修改，所以用冗余字段',
	product_model VARCHAR(100) NOT NULL COMMENT '产品型号',
    add_date BIGINT NOT NULL COMMENT '添加日期',
    ratio_ag FLOAT(4,2) NOT NULL DEFAULT 0.00 COMMENT '银占比',
    ratio_cu FLOAT(4,2) NOT NULL DEFAULT 0.00 COMMENT '铜占比',
    ratio_zn FLOAT(4,2) NOT NULL DEFAULT 0.00 COMMENT '锌占比',
    ratio_cd FLOAT(4,2) NOT NULL DEFAULT 0.00 COMMENT '镉占比',
    ratio_sn FLOAT(4,2) NOT NULL DEFAULT 0.00 COMMENT '锡占比',
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '产品型号信息';

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`(
	product_id INT AUTO_INCREMENT COMMENT'产品id',
    product_model_info_id INT NOT NULL COMMENT '产品型号id',
    product_size varchar(100) NOT NULL COMMENT'产品规格 长*宽*厚',
    product_shape char(1) NOT NULL COMMENT'产品形态:直/弯',
    PRIMARY KEY(product_id),
    FOREIGN KEY(product_model_info_id) references product_model_info(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '产品';

DROP TABLE IF EXISTS `factory_product_inventory`;
CREATE TABLE `factory_product_inventory`(
	id INT AUTO_INCREMENT COMMENT '工厂产品库存id',
	product_id INT NOT NULL COMMENT '产品id',
    product_inventory FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '库存总量',
    PRIMARY KEY(id),
    FOREIGN KEY(product_id) references product(product_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '工厂产品库存';

DROP TABLE IF EXISTS `warehouse_product_inventory`;
CREATE TABLE `warehouse_product_inventory`(
	id INT AUTO_INCREMENT COMMENT '仓库库存id',
	product_id INT NOT NULL COMMENT '产品关联id',
    product_inventory FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '产品库存',
    PRIMARY KEY(id),
    FOREIGN KEY(product_id) references product(product_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '仓库库存';

DROP TABLE IF EXISTS `blowon_record`;
CREATE TABLE `blowon_record`(
	id INT AUTO_INCREMENT COMMENT '开炉记录id',
	blowon_num CHAR(14) NOT NULL COMMENT '开炉编号',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    product_model_info_id INT  NOT NULL COMMENT '产品型号id',
    blowon_date BIGINT NOT NULL COMMENT '开炉日期',
    consume_ag FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '银',
    consume_cu FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '铜',
    consume_zn FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '锌',
    consume_cd FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '镉',
    consume_sn FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '锡',
    material_consume FLOAT(9,2) NOT NULL COMMENT '原料消耗总量',
    waste_consume FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '废料消耗量',
    leftover_consume FLOAT(9,2) NOT NULL DEFAULT 0.00 COMMENT '边角料消耗量',
    primary key(id),
    FOREIGN KEY(staff_id) references staff(staff_id) ,
    FOREIGN KEY(product_model_info_id) references product_model_info(id) 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '开炉记录';

DROP TABLE IF EXISTS `blank_record`;
CREATE TABLE `blank_record`(
	id INT AUTO_INCREMENT COMMENT '胚料记录id',
	consume_num CHAR(14) NOT NULL COMMENT '损耗编号',
    product_model_info_id INT NOT NULL COMMENT '产品型号id',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    blowon_date BIGINT NOT NULL COMMENT '开炉日期',
    material_consume FLOAT(9,2) NOT NULL COMMENT '原料消耗总量',
    blank_produce FLOAT(9,2) NOT NULL COMMENT '胚料产出总量',
    loss_ratio FLOAT(4,2) NOT NULL COMMENT '开炉损耗比', 
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(product_model_info_id) references product_model_info(id) 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '胚料记录';

DROP TABLE IF EXISTS `check_warehouse_others_record`;
CREATE TABLE `check_warehouse_others_record`(
	id INT AUTO_INCREMENT COMMENT '其他清仓记录id',
	check_num char(14) NOT NULL COMMENT'清仓编号',
	staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    product_model_info_id INT NOT NULL COMMENT '产品型号id',
	check_date BIGINT NOT NULL COMMENT '清仓日期',
    blank_inventory FLOAT(9,2) NOT NULL COMMENT '胚料',
    semifinished_product_inventory FLOAT(9,2) NOT NULL COMMENT '半成品',
	waste_inventory FLOAT(9,2) NOT NULL COMMENT '废料',
	leftover_inventory FLOAT(9,2) NOT NULL COMMENT '边角料',
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(product_model_info_id) references product_model_info(id),
    FOREIGN KEY(staff_id) references staff(staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '其他清仓记录';

DROP TABLE IF EXISTS `check_warehouse_product_record`;
CREATE TABLE `check_warehouse_product_record`(
	id INT AUTO_INCREMENT COMMENT '产品清仓记录id',
	check_num CHAR(14) NOT NULL COMMENT'产品清仓编号',
	staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    product_id INT NOT NULL COMMENT '产品id',
	check_date BIGINT NOT NULL COMMENT '清仓日期',
    product_inventory FLOAT(9,2) NOT NULL COMMENT '产品数量',
	PRIMARY KEY (id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(product_id) references product(product_id) 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '产品清仓记录';

DROP TABLE IF EXISTS `product_produce`;
create table `product_produce`(
	id INT AUTO_INCREMENT COMMENT '产品产出id',
	produce_num CHAR(14) NOT NULL COMMENT '产出编号',
	staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    product_id INT NOT NULL COMMENT '产品id',
    produce_date bigint NOT NULL COMMENT '产出日期',
    produce_quantity FLOAT(9,2) COMMENT '产出总量',
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(product_id) references product(product_id) 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '产品产出';

DROP TABLE IF EXISTS `factory_out_storage_record`;
CREATE TABLE factory_out_storage_record(
	id INT AUTO_INCREMENT COMMENT '工厂出库记录id',
	out_storage_num CHAR(14) NOT NULL COMMENT '出库编号',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    product_id INT NOT NULL COMMENT '产品id',
    out_storage_date BIGINT NOT NULL COMMENT '出库日期',
    out_storage_quantity FLOAT(9,2) NOT NULL COMMENT '出库总量',
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(product_id) references product(product_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '工厂出库记录';

DROP TABLE IF EXISTS `warehouse_in_storage_record`;
CREATE TABLE warehouse_in_storage_record(
	id INT AUTO_INCREMENT COMMENT '仓库入仓记录id',
	in_storage_num CHAR(14) NOT NULL COMMENT '入库编号',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    product_id INT NOT NULL COMMENT '产品id',
    in_storage_date BIGINT NOT NULL COMMENT '入库日期',
    in_storage_quantity FLOAT(9,2) NOT NULL COMMENT '入库总量',
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(product_id) references product(product_id) 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '仓库入仓记录';

DROP TABLE IF EXISTS `warehouse_order`;
CREATE TABLE warehouse_order(
	id INT AUTO_INCREMENT COMMENT '仓库订单id',
	order_num CHAR(14) NOT NULL COMMENT '订单编号',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    product_id INT NOT NULL COMMENT '产品id',
    customer_id INT NOT NULL COMMENT '客户id',
    order_date BIGINT NOT NULL COMMENT '订单日期',
    delivery_quantity_total FLOAT(9,2) NOT NULL COMMENT '总量',
    delivery_quantity_need FLOAT(9,2) NOT NULL COMMENT '需配送',
    unit_price FLOAT(9,2) NOT NULL COMMENT '单价',
    total_price FLOAT(20,2) NOT NULL COMMENT '总价',
    order_status VARCHAR(4) NOT NULL COMMENT '订单状态',
    delivery_date BIGINT NOT NULL COMMENT '送达日期',
    warehouse_manager_id INT NOT NULL COMMENT '经理id',
    warehouse_manager_name VARCHAR(20) NOT NULL COMMENT '经理名字',
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(warehouse_manager_id) references staff(staff_id),
    FOREIGN KEY(product_id) references product(product_id),
    FOREIGN KEY(customer_id) references customer(customer_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '仓库订单';

DROP TABLE IF EXISTS `warehouse_delivery_record`;
CREATE TABLE warehouse_delivery_record(
	id INT AUTO_INCREMENT COMMENT '仓库配送记录id',
	delivery_num CHAR(14) NOT NULL COMMENT '配送编号',
    staff_id INT NOT NULL COMMENT '登记者id',
    staff_name VARCHAR(20) NOT NULL COMMENT '登记者名字',
    warehouse_order_id INT NOT NULL COMMENT '订单id',
    delivery_date BIGINT NOT NULL COMMENT '配送日期',
    delivery_quantity FLOAT(9,2) NOT NULL COMMENT '配送量',
    PRIMARY KEY(id),
    FOREIGN KEY(staff_id) references staff(staff_id),
    FOREIGN KEY(warehouse_order_id) references warehouse_order(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '仓库配送记录';


