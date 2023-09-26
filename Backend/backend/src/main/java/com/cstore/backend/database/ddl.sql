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
