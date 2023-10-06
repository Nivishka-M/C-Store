USE `cstore`;

DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `order_contact`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `cart_item`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `user_address`;
DROP TABLE IF EXISTS `user_contact`;
DROP TABLE IF EXISTS `registered_user`;
DROP TABLE IF EXISTS `user`;
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
    FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE,
    FOREIGN KEY (`sub_category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Image
DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
    `image_id` BIGINT AUTO_INCREMENT,
    `url`  VARCHAR (100),
    PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- WholeProduct
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
    `product_id`   BIGINT AUTO_INCREMENT,
    `product_name` VARCHAR (100),
    `base_price`   NUMERIC (10, 2),
    `brand`        VARCHAR (40),
    `description`  TEXT,
    `image_url`   VARCHAR (100),
    PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- WholeProduct Image
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
    FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ProductSelectionProperty
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

-- User
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `user_id` BIGINT AUTO_INCREMENT,
    `role`    VARCHAR (10) CHECK (`role` IN ('GUEST_CUST', 'REG_CUST', 'ADMIN')),
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Registered User
DROP TABLE IF EXISTS `registered_user`;

CREATE TABLE `registered_user` (
    `user_id` BIGINT,
    `email`       VARCHAR (60),
    `password`    VARCHAR (60),
    `first_name`  VARCHAR (20),
    `last_name`   VARCHAR (20),
    PRIMARY KEY (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- User Contact
DROP TABLE IF EXISTS `user_contact`;

CREATE TABLE `user_contact` (
    `user_id`      BIGINT,
    `telephone_number` VARCHAR (12),
    PRIMARY KEY (`user_id`, `telephone_number`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- User Address
DROP TABLE IF EXISTS `user_address`;

CREATE TABLE `user_address` (
    `address_id`    BIGINT AUTO_INCREMENT,
    `user_id`   BIGINT,
    `street_number` VARCHAR (10),
    `street_name`   VARCHAR (60),
    `city`          VARCHAR (40),
    `zipcode`       INTEGER,
    PRIMARY KEY (`address_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Cart
DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
    `user_id` BIGINT,
    `total_price` NUMERIC (12, 2),
    PRIMARY KEY (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Contains
DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
    `user_id` BIGINT,
    `variant_id`  BIGINT,
    `quantity`    INTEGER,
    PRIMARY KEY (`user_id`, `variant_id`),
    FOREIGN KEY (`user_id`) REFERENCES `cart` (`user_id`) ON DELETE CASCADE,
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


-- VIEWS----------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS `base_category`;
CREATE VIEW `base_category` AS
    SELECT *
    FROM `category`
    WHERE `category_id` NOT IN (SELECT DISTINCT `sub_category_id`
                                FROM sub_category);

# SELECT * FROM `base_category`;


-- PROCEDURES-----------------------------------------------------------------------------------------------------------


DROP PROCEDURE IF EXISTS products_from_category;
DELIMITER //
CREATE PROCEDURE products_from_category(c_id BIGINT)
BEGIN
    SELECT DISTINCT `product`.*
    FROM `belongs_to` NATURAL LEFT OUTER JOIN `product`
    WHERE `belongs_to`.`category_id` = c_id;
END //
DELIMITER ;

# CALL products_from_category(4);


DROP PROCEDURE IF EXISTS categories_from_product;
DELIMITER //
CREATE PROCEDURE categories_from_product(p_id BIGINT)
BEGIN
    SELECT DISTINCT `category`.*
    FROM `category`, `belongs_to` NATURAL LEFT OUTER JOIN `product`
    WHERE `category`.`category_id` = `belongs_to`.`category_id` AND `belongs_to`.`product_id` = p_id;
END //
DELIMITER ;

# CALL categories_from_product(17);


DROP PROCEDURE IF EXISTS images_from_product;
DELIMITER //
CREATE PROCEDURE images_from_product(p_id BIGINT)
BEGIN
    SELECT DISTINCT `image`.*
    FROM `image`, `product_image` NATURAL LEFT OUTER JOIN `product`
    WHERE `image`.image_id = `product_image`.image_id AND `product_image`.`product_id` = p_id;
END //
DELIMITER ;

# CALL images_from_product(1);


DROP PROCEDURE IF EXISTS count_stocks;
DELIMITER //
CREATE PROCEDURE count_stocks(p_id BIGINT)
BEGIN
    SELECT DISTINCT SUM(count)
    FROM `varies_on` NATURAL LEFT OUTER JOIN `inventory`
    WHERE `varies_on`.product_id = p_id;
END //
DELIMITER ;

# CALL count_stocks(1);


DROP PROCEDURE IF EXISTS properties_from_product;
DELIMITER //
CREATE PROCEDURE properties_from_product(p_id BIGINT)
BEGIN
    SELECT *
    FROM `property`
    WHERE `property_id` IN (SELECT `property_id`
                            FROM `product` NATURAL LEFT OUTER JOIN `varies_on`
                            WHERE `product`.`product_id` = p_id AND `property_id` IS NOT NULL);
END //
DELIMITER ;

# CALL properties_from_product(1);
