USE cstore;

-- Category
DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
    `category_id`          BIGINT AUTO_INCREMENT,
    `category_name`        VARCHAR (40),
    `category_description` TEXT,
    PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Sub Category
DROP TABLE IF EXISTS `sub_category`;

CREATE TABLE `sub_category` (
    `category_id`     BIGINT,
    `sub_category_id` BIGINT,
    CONSTRAINT CHECK (`category_id` != `sub_category_id`),
    PRIMARY KEY (`category_id`, `sub_category_id`),
    FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
    FOREIGN KEY (`sub_category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Image
DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
    `image_id` BIGINT AUTO_INCREMENT,
    `content`  MEDIUMBLOB,
    PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
    `product_id`   BIGINT AUTO_INCREMENT,
    `product_name` VARCHAR (100),
    `base_price`   NUMERIC (10, 2),
    `brand`        VARCHAR (40),
    `description`  TEXT,
    `main_image`   MEDIUMBLOB,
    PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Product Image
DROP TABLE IF EXISTS `product_image`;

CREATE TABLE `product_image` (
    `image_id`   BIGINT,
    `product_id` BIGINT,
    PRIMARY KEY (`image_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE,
    FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Belongs to
DROP TABLE IF EXISTS `belongs_to`;

CREATE TABLE `belongs_to` (
    `category_id` BIGINT,
    `product_id`  BIGINT,
    PRIMARY KEY (`category_id`, `product_id`),
    FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Property
DROP TABLE IF EXISTS `property`;

CREATE TABLE `property` (
    `property_id`     BIGINT AUTO_INCREMENT,
    `property_name`   VARCHAR (40),
    `value`           VARCHAR (40),
    `image`           MEDIUMBLOB DEFAULT NULL,
    `price_increment` NUMERIC (10, 2),
    PRIMARY KEY (`property_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Variant
DROP TABLE IF EXISTS `variant`;

CREATE TABLE `variant` (
    `variant_id` BIGINT AUTO_INCREMENT,
    `weight`     NUMERIC (5, 2),
    PRIMARY KEY (`variant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Varies, based on
DROP TABLE IF EXISTS `varies_on`;

CREATE TABLE `varies_on` (
    `product_id`  BIGINT,
    `property_id` BIGINT,
    `variant_id`  BIGINT,
    PRIMARY KEY (`product_id`,`variant_id`,`property_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE,
    FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`) ON DELETE CASCADE,
    FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Warehouse
DROP TABLE IF EXISTS `warehouse`;

CREATE TABLE `warehouse` (
    `warehouse_id`  BIGINT AUTO_INCREMENT,
    `street_number` VARCHAR (10),
    `street_name`   VARCHAR (60),
    `city`          VARCHAR (40),
    `zipcode`       INTEGER,
    PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Warehouse Contact
DROP TABLE IF EXISTS `warehouse_contact`;

CREATE TABLE `warehouse_contact` (
    `telephone_number` VARCHAR (12),
    `warehouse_id`     BIGINT,
    PRIMARY KEY (`telephone_number`),
    FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inventory
DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
    `warehouse_id` BIGINT,
    `variant_id`   BIGINT,
    `sku`          VARCHAR(20),
    `count`        INTEGER,
    PRIMARY KEY (`warehouse_id`, `variant_id`),
    FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`) ON DELETE CASCADE,
    FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Customer
DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
    `customer_id` BIGINT AUTO_INCREMENT,
    `type`        VARCHAR (10),
    PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Registered Customer
DROP TABLE IF EXISTS `registered_customer`;

CREATE TABLE `registered_customer` (
    `customer_id` BIGINT,
    `email`       VARCHAR (60),
    `password`    VARCHAR (60),
    `first_name`  VARCHAR (20),
    `last_name`   VARCHAR (20),
    PRIMARY KEY (`customer_id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Customer Contact
DROP TABLE IF EXISTS `customer_contact`;

CREATE TABLE `customer_contact` (
    `customer_id`      BIGINT,
    `telephone_number` VARCHAR (12),
    PRIMARY KEY (`customer_id`, `telephone_number`),
    FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Customer Address
DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
    `address_id`    BIGINT AUTO_INCREMENT,
    `customer_id`   BIGINT,
    `street_number` VARCHAR (10),
    `street_name`   VARCHAR (60),
    `city`          VARCHAR (40),
    `zipcode`       INTEGER,
    PRIMARY KEY (`address_id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Cart
DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
    `customer_id` BIGINT,
    `total_price` NUMERIC (12, 2),
    PRIMARY KEY (`customer_id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Contains
DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
    `customer_id` BIGINT,
    `variant_id`  BIGINT,
    `quantity`    INTEGER,
    PRIMARY KEY (`customer_id`, `variant_id`),
    FOREIGN KEY (`customer_id`) REFERENCES `cart` (`customer_id`) ON DELETE CASCADE,
    FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Order
DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
    `order_id`        BIGINT AUTO_INCREMENT,
    `date`            DATETIME,
    `total_payment`   NUMERIC (12, 2),
    `payment_method`  VARCHAR (20),
    `delivery_method` VARCHAR (40),
    `email`           VARCHAR (60),
    `street_number`   VARCHAR (10),
    `street_name`     VARCHAR (60),
    `city`            VARCHAR (40),
    `zipcode`         INTEGER,
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Order Contact
DROP TABLE IF EXISTS `order_contact`;

CREATE TABLE `order_contact` (
    `order_id`         BIGINT,
    `telephone_number` VARCHAR (12),
    PRIMARY KEY (`order_id`, `telephone_number`),
    FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Order Item
DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
    `order_id`     BIGINT,
    `variant_id`   BIGINT,
    `warehouse_id` BIGINT,
    `count`        INTEGER,
    PRIMARY KEY (`order_id`, `variant_id`, `warehouse_id`),
    FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`variant_id`) REFERENCES `variant` (`variant_id`),
    FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `order_contact`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `cart_item`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `customer_address`;
DROP TABLE IF EXISTS `customer_contact`;
DROP TABLE IF EXISTS `registered_customer`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `inventory`;
DROP TABLE IF EXISTS `warehouse_contact`;
DROP TABLE IF EXISTS `warehouse`;
DROP TABLE IF EXISTS `varies_on`;
DROP TABLE IF EXISTS `variant`;
DROP TABLE IF EXISTS `property`;
DROP TABLE IF EXISTS `belongs_to`;
DROP TABLE IF EXISTS `product_image`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `image`;
DROP TABLE IF EXISTS `sub_category`;
DROP TABLE IF EXISTS `category`;
