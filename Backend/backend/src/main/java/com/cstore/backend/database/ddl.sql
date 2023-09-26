USE cstore;

-- Category
DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
    `category_id` INTEGER,
    `category_name` VARCHAR (40),
    `category_description` TEXT
);

ALTER TABLE `category` ADD PRIMARY KEY (`category_id`);

-- Sub Category
DROP TABLE IF EXISTS `sub_category`;

CREATE TABLE `sub_category` (
    `category_id` INTEGER,
    `super_category_id` INTEGER,
    CONSTRAINT CHECK (`category_id` != `super_category_id`)
);

ALTER TABLE `sub_category` ADD PRIMARY KEY (`category_id`, `super_category_id`);
ALTER TABLE `sub_category` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);
ALTER TABLE `sub_category` ADD FOREIGN KEY (`super_category_id`) REFERENCES `category` (`category_id`);

-- Product
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
    `product_id` INTEGER,
    `product_name` VARCHAR (60),
    `brand` VARCHAR (60),
    `description` TEXT,
    `base_price` NUMERIC (10, 2)
);

ALTER TABLE `product` ADD PRIMARY KEY (`product_id`);

-- Belongs to
DROP TABLE IF EXISTS `belongs_to`;

CREATE TABLE `belongs_to` (
    `product_id` INTEGER,
    `category_id` INTEGER
);

ALTER TABLE `belongs_to` ADD PRIMARY KEY (`product_id`, `category_id`);
ALTER TABLE `belongs_to` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);
ALTER TABLE `belongs_to` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

-- Property
DROP TABLE IF EXISTS `property`;

CREATE TABLE `property` (
    `property_id` INTEGER,
    `property_name` VARCHAR (20),
    `value` VARCHAR (20),
    `price_increment` NUMERIC (10, 2)
);

ALTER TABLE `property` ADD PRIMARY KEY (`property_id`);

-- Image
DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
    `image_id` INTEGER,
    `content` MEDIUMBLOB
);

ALTER TABLE `image` ADD PRIMARY KEY (`image_id`);

-- Variant
DROP TABLE IF EXISTS `variant`;

CREATE TABLE `variant` (
    `variant_id` INTEGER,
    `weight` NUMERIC (7, 4)
);

ALTER TABLE `variant` ADD PRIMARY KEY (`variant_id`);

-- Variant Image
DROP TABLE IF EXISTS `variant_image`;

CREATE TABLE `variant_image` (
    `image_id` INTEGER,
    `variant_id` INTEGER
);

ALTER TABLE `variant_image` ADD PRIMARY KEY (`image_id`);
ALTER TABLE `variant_image` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`);
ALTER TABLE `variant_image` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

-- Varies, based on
DROP TABLE IF EXISTS `varies_on`;

CREATE TABLE `varies_on` (
    `product_id` INTEGER,
    `variant_id` INTEGER,
    `property_id` INTEGER
);

ALTER TABLE `varies_on` ADD PRIMARY KEY (`product_id`,`variant_id`,`property_id`);
ALTER TABLE `varies_on` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);
ALTER TABLE `varies_on` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`);
ALTER TABLE `varies_on` ADD FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`);

-- Warehouse
DROP TABLE IF EXISTS `warehouse`;

CREATE TABLE `warehouse` (
    `warehouse_id`  INTEGER,
    `street_number` VARCHAR (10),
    `street_name`   VARCHAR (30),
    `city`          VARCHAR (20),
    `zipcode`       INTEGER
);

ALTER TABLE `warehouse` ADD PRIMARY KEY (`warehouse_id`);

-- Warehouse Contact
DROP TABLE IF EXISTS `warehouse_contact`;

CREATE TABLE `warehouse_contact` (
    `warehouse_id` INTEGER,
    `telephone_number` INTEGER
);

ALTER TABLE `warehouse_contact` ADD PRIMARY KEY (`telephone_number`);
ALTER TABLE `warehouse_contact` ADD FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`);

-- Inventory
DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
    `warehouse_id` INTEGER,
    `variant_id` INTEGER,
    `sku` VARCHAR(20),
    `count` INTEGER
);

ALTER TABLE `inventory` ADD PRIMARY KEY (`warehouse_id`, `variant_id`);
ALTER TABLE `inventory` ADD FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`);
ALTER TABLE `inventory` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`);

-- Customer
DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
    `customer_id` INTEGER,
    `type` VARCHAR (10)
);

ALTER TABLE `customer` ADD PRIMARY KEY (`customer_id`);

-- Registered Customer
DROP TABLE IF EXISTS `registered_customer`;

CREATE TABLE `registered_customer` (
    `customer_id` INTEGER,
    `email` VARCHAR (60),
    `password` VARCHAR (40),
    `first_name` VARCHAR (20),
    `last_name` VARCHAR (20)
);

ALTER TABLE `registered_customer` ADD PRIMARY KEY (`customer_id`);
ALTER TABLE `registered_customer` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

-- Customer Contact
DROP TABLE IF EXISTS `customer_contact`;

CREATE TABLE `customer_contact` (
    `customer_id` INTEGER,
    `telephone_number` INTEGER
);

ALTER TABLE `customer_contact` ADD PRIMARY KEY (`customer_id`, `telephone_number`);
ALTER TABLE `customer_contact` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

-- Customer Address
DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
    `address_id` INTEGER,
    `customer_id` INTEGER,
    `street_number` VARCHAR (10),
    `street_name`   VARCHAR (30),
    `city`          VARCHAR (20),
    `zipcode`       INTEGER
);

ALTER TABLE `customer_address` ADD PRIMARY KEY (`address_id`);
ALTER TABLE `customer_address` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

-- Cart
DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
    `customer_id` INTEGER,
    `total_price` NUMERIC (12, 2)
);

ALTER TABLE `cart` ADD PRIMARY KEY (`customer_id`);
ALTER TABLE `cart` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

-- Contains
DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
    `customer_id` INTEGER,
    `variant_id` INTEGER,
    `quantity` NUMERIC (10, 2)
);

ALTER TABLE `cart_item` ADD PRIMARY KEY (`customer_id`, `variant_id`);
ALTER TABLE `cart_item` ADD FOREIGN KEY (`customer_id`) REFERENCES `cart` (`customer_id`);
ALTER TABLE `cart_item` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`);

-- Order
DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
    `order_id`        INTEGER,
    `date`            DATETIME,
    `total_payment`   NUMERIC (12, 2),
    `payment_method`  VARCHAR (10),
    `delivery_method` VARCHAR (20),
    `email`           VARCHAR (60),
    `street_number`   VARCHAR (10),
    `street_name`     VARCHAR (30),
    `city`            VARCHAR (20),
    `zipcode`         INTEGER
);

ALTER TABLE `order` ADD PRIMARY KEY (`order_id`);

-- Order Contact
DROP TABLE IF EXISTS `order_contact`;

CREATE TABLE `order_contact` (
    `order_id`         INTEGER,
    `telephone_number` INTEGER
);

ALTER TABLE `order_contact` ADD PRIMARY KEY (`order_id`, `telephone_number`);
ALTER TABLE `order_contact` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);

-- Order Item
DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
    `order_id`     INTEGER,
    `variant_id`   INTEGER,
    `warehouse_id` INTEGER,
    `count` INTEGER
);

ALTER TABLE `order_item` ADD PRIMARY KEY (`order_id`, `variant_id`, `warehouse_id`);
ALTER TABLE `order_item` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);
ALTER TABLE `order_item` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`);
ALTER TABLE `order_item` ADD FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`);
