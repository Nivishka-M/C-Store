USE cstore;

-- Category
DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
    `category_id`          BIGINT,
    `category_name`        VARCHAR (40),
    `category_description` TEXT
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `category` ADD PRIMARY KEY (`category_id`);

-- Sub Category
DROP TABLE IF EXISTS `sub_category`;

CREATE TABLE `sub_category` (
    `category_id`     BIGINT,
    `sub_category_id` BIGINT,
    CONSTRAINT CHECK (`category_id` != `sub_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `sub_category` ADD PRIMARY KEY (`category_id`, `sub_category_id`);
ALTER TABLE `sub_category` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);
ALTER TABLE `sub_category` ADD FOREIGN KEY (`sub_category_id`) REFERENCES `category` (`category_id`);

-- Image
DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
                         `image_id` BIGINT,
                         `content`  MEDIUMBLOB
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `image` ADD PRIMARY KEY (`image_id`);

-- Product
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
    `product_id`   BIGINT,
    `product_name` VARCHAR (100),
    `brand`        VARCHAR (40),
    `description`  TEXT,
    `base_price`   NUMERIC (10, 2)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `product` ADD PRIMARY KEY (`product_id`);

-- Product Image
DROP TABLE IF EXISTS `variant_image`;

CREATE TABLE `product_image` (
    `image_id`   BIGINT,
    `product_id` BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `product_image` ADD PRIMARY KEY (`image_id`);
ALTER TABLE `product_image` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE;
ALTER TABLE `product_image` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`) ON DELETE CASCADE;

-- Belongs to
DROP TABLE IF EXISTS `belongs_to`;

CREATE TABLE `belongs_to` (
    `category_id` BIGINT,
    `product_id`  BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `belongs_to` ADD PRIMARY KEY (`category_id`, `product_id`);
ALTER TABLE `belongs_to` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);
ALTER TABLE `belongs_to` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE;

-- Property
DROP TABLE IF EXISTS `property`;

CREATE TABLE `property` (
    `property_id`     BIGINT,
    `property_name`   VARCHAR (40),
    `value`           VARCHAR (40),
    `image`           MEDIUMBLOB DEFAULT NULL,
    `price_increment` NUMERIC (10, 2)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `property` ADD PRIMARY KEY (`property_id`);

-- Variant
CREATE TABLE `variant` (
    `variant_id` BIGINT,
    `weight`     NUMERIC (5, 2)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `variant` ADD PRIMARY KEY (`variant_id`);

-- Varies, based on
DROP TABLE IF EXISTS `varies_on`;

CREATE TABLE `varies_on` (
    `product_id`  BIGINT,
    `property_id` BIGINT,
    `variant_id`  BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `varies_on` ADD PRIMARY KEY (`product_id`,`variant_id`,`property_id`);
ALTER TABLE `varies_on` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE;
ALTER TABLE `varies_on` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`) ON DELETE CASCADE;
ALTER TABLE `varies_on` ADD FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`) ON DELETE CASCADE;

-- Warehouse
DROP TABLE IF EXISTS `warehouse`;

CREATE TABLE `warehouse` (
    `warehouse_id`  BIGINT,
    `street_number` VARCHAR (10),
    `street_name`   VARCHAR (60),
    `city`          VARCHAR (40),
    `zipcode`       INTEGER
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `warehouse` ADD PRIMARY KEY (`warehouse_id`);

-- Warehouse Contact
DROP TABLE IF EXISTS `warehouse_contact`;

CREATE TABLE `warehouse_contact` (
    `telephone_number` INTEGER,
    `warehouse_id`               BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `warehouse_contact` ADD PRIMARY KEY (`telephone_number`);
ALTER TABLE `warehouse_contact` ADD FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`) ON DELETE CASCADE;

-- Inventory
DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
    `warehouse_id` BIGINT,
    `variant_id`   BIGINT,
    `sku`          VARCHAR(20),
    `count`        INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `inventory` ADD PRIMARY KEY (`warehouse_id`, `variant_id`);
ALTER TABLE `inventory` ADD FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`) ON DELETE CASCADE;
ALTER TABLE `inventory` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`) ON DELETE CASCADE;

-- Customer
DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
    `customer_id` BIGINT,
    `type`        VARCHAR (10)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `customer` ADD PRIMARY KEY (`customer_id`);

-- Registered Customer
DROP TABLE IF EXISTS `registered_customer`;

CREATE TABLE `registered_customer` (
    `customer_id` BIGINT,
    `email`       VARCHAR (60),
    `password`    VARCHAR (60),
    `first_name`  VARCHAR (20),
    `last_name`   VARCHAR (20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `registered_customer` ADD PRIMARY KEY (`customer_id`);
ALTER TABLE `registered_customer` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE;

-- Customer Contact
DROP TABLE IF EXISTS `customer_contact`;

CREATE TABLE `customer_contact` (
    `customer_id`      BIGINT,
    `telephone_number` INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `customer_contact` ADD PRIMARY KEY (`customer_id`, `telephone_number`);
ALTER TABLE `customer_contact` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE;

-- Customer Address
DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
    `address_id`    BIGINT,
    `customer_id`   BIGINT,
    `street_number` VARCHAR (10),
    `street_name`   VARCHAR (60),
    `city`          VARCHAR (40),
    `zipcode`       INTEGER
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `customer_address` ADD PRIMARY KEY (`address_id`);
ALTER TABLE `customer_address` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE;

-- Cart
DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
    `customer_id` BIGINT,
    `total_price` NUMERIC (12, 2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `cart` ADD PRIMARY KEY (`customer_id`);
ALTER TABLE `cart` ADD FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE;

-- Contains
DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
    `customer_id` BIGINT,
    `variant_id`  BIGINT,
    `quantity`    NUMERIC (10, 2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `cart_item` ADD PRIMARY KEY (`customer_id`, `variant_id`);
ALTER TABLE `cart_item` ADD FOREIGN KEY (`customer_id`) REFERENCES `cart` (`customer_id`) ON DELETE CASCADE;
ALTER TABLE `cart_item` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`) ON DELETE CASCADE;

-- Order
DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
    `order_id`        BIGINT,
    `date`            DATETIME,
    `total_payment`   NUMERIC (12, 2),
    `payment_method`  VARCHAR (20),
    `delivery_method` VARCHAR (40),
    `email`           VARCHAR (60),
    `street_number`   VARCHAR (10),
    `street_name`     VARCHAR (60),
    `city`            VARCHAR (40),
    `zipcode`         INTEGER
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `order` ADD PRIMARY KEY (`order_id`);

-- Order Contact
DROP TABLE IF EXISTS `order_contact`;

CREATE TABLE `order_contact` (
    `order_id`         BIGINT,
    `telephone_number` INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `order_contact` ADD PRIMARY KEY (`order_id`, `telephone_number`);
ALTER TABLE `order_contact` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE;

-- Order Item
DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
    `order_id`     BIGINT,
    `variant_id`   BIGINT,
    `warehouse_id` BIGINT,
    `count`        INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `order_item` ADD PRIMARY KEY (`order_id`, `variant_id`, `warehouse_id`);
ALTER TABLE `order_item` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE;
ALTER TABLE `order_item` ADD FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`);
ALTER TABLE `order_item` ADD FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`);
