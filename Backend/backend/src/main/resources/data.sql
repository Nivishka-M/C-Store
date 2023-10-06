USE `cstore`;

DELETE FROM `order_item`;
DELETE FROM `order_contact`;
DELETE FROM `order`;
DELETE FROM `cart_item`;
DELETE FROM `cart`;
DELETE FROM `user_address`;
DELETE FROM `user_contact`;
DELETE FROM `registered_user`;
DELETE FROM `user`;
DELETE FROM `inventory`;
DELETE FROM `warehouse_contact`;
DELETE FROM `warehouse`;
DELETE FROM `varies_on`;
DELETE FROM `variant`;
DELETE FROM `property`;
DELETE FROM `belongs_to`;
DELETE FROM `product_image`;
DELETE FROM `product`;
DELETE FROM `image`;
DELETE FROM `sub_category`;
DELETE FROM `category`;

-- ---------------------------------------------------------------------------------------------------------------------
--  `category`

INSERT INTO `category` (`category_id`, `category_name`, `category_description`) VALUES
    (1, 'Electronics', 'Electronic devices and accessories'),
    (2, 'Toys', 'Childrens toys and games'),
    (3, 'Tvs', 'LCD, LED display TVs'),
    (4, 'Mobile Phones', 'Android phones,iphones and hot brands'),
    (5, 'Home Electronic accessories', 'Kitchen,Bathroom,Living and lifestyle electronics'),
    (6, 'Toys for boys','RC cars ,games, toy guns and more..'),
    (7, 'Toys for girls','Dolls,teddies, Princess accessories and more..'),
    (8, 'Laptops','All top brands at best price'),
    (9, 'Outdoor electronics' , 'Electronic gadgets used in the outdoors'),
    (10, 'Computer accessories', 'Electronic accessaries related to computers'),
    (11, 'Medical electronics', 'Electronic medical equipments');

# SELECT *
# FROM `category`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `sub Category`

INSERT INTO `sub_category` (`category_id`, `sub_category_id`) VALUES
     (1, 3),
     (1, 4),
     (1, 5),
     (2, 6),
     (2, 7),
     (1, 8),
     (1, 9),
     (1, 10),
     (1, 11);

# SELECT *
# FROM `sub_category`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `image`



# SELECT *
# FROM `image`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `product`

INSERT INTO `product` (`product_id`, `product_name`, `brand`, `description`, `base_price`) VALUES
    (1, 'iphone 14 pro', 'apple', 'A high-end smartphone with advanced features.', 500),
    (2, 'macbook', 'apple', 'Powerful laptop for professionals.', 1000),
    (3, 'TV', 'samsung', '55-inch 4K Ultra HD LED TV for stunning visuals.', 2000),
    (4, 'Toy gun', 'Gunhub', 'A set of essential garden tools for gardening enthusiasts.', 1200),
    (5, 'Childrens Book', 'StoryTime Publications', 'A captivating childrens book for young readers.', 500),
    (6, 'Galaxy M04 phone', 'Samsung','One year software and hardware warranty AND the charger included', 105),
    (7, 'Electronic Digital Weighing Kitchen Scale', 'Tare', 'Large LCD Display with automatic zero resetting + 2 Free batteries', 4.99),
    (8, 'TV', 'Innovex', '32 Inch LED Frameless TV With 3 Years Company Warranty', 249.99),
    (9, 'Quadcopter Drone With Camera', 'Hubsan', 'RC Foldable wide angle HD,4K,1080P Camera', 55.99),
    (10, 'Micro SD SDCC Card', 'Kingston Technology', 'Ultra Large Capacity High Speed 2TB-1TB USB Drive Micro SD', 10.45),
    (11, 'Bluetooth Earphone with Ear Hook', 'FiiO', 'Suitable for Running Sports. Stereo Buttons Control With Microphone Wireless Headphones', 27.75),
    (12, 'Toy FARM TRACTOR', null, ' Made of top-grade plastic - for kids', 5.99),
    (13, 'Simulation Dinosaur', 'Wild Safari','Lifelike Mosasaurus PlesiosaurDunkleosteus Action Figures', 9.20),
    (14, 'Gorilla female Monkey Model', 'Schleich', 'Squeaky Sound Toy, Children Toy,Action Figure', 12),
    (15, 'High speed RC Car', 'Rastar', 'Remote Control AE86 Model GTR Vehicle RC Racing Cars', 67.78),
    (16, 'Bluetooth speaker', 'SONY','Waterproof portable wireless', 250),
    (17, 'Smart Watch', 'Huawei', 'Smart Watch for Men, Fitness Tracker', 49),
    (18, 'Dash camera', 'Xiaomi', '3 Channel Dash Cam 2K+1080P With GPS, WiFi', 69),
    (19, 'Arduino kit', 'Grove', ' Arduino starter kit, improved version', 30),
    (20, 'Bluetooth receiver', 'LDAC', 'Qualcomm QCC5125 lossless Bluetooth 5.1 receiver', 80),
    (21, 'Smart stunt dog Toy','Sigma', 'RC Robot Dog Voice Command Programmable Touch-sense',150),
    (22, 'Six claw dancing robot','Mattel','Limited Edition children Toys Dance Music Robots',230),
    (23, 'Unicorn plush toy','K-Nex','Soft Stuffed Toy Giant Size Cartoon Dolls Animal Horse',23),
    (24, 'Football Table Interactive Games','Kidsbele', 'Tabletop Soccer Pinball Toys Classic Parent-Child Interactive',456),
    (25, 'RC car Lambogini','ARRMA','2.4G Radio Remote Control Sports Cars For Children Racing High Speed Drive',299),
    (26, 'Electric Domino Train Toy','Lego','Colorful Electric Domino Train Toys Set with 100Pcs Building Blocks',34),
    (27, 'Luminous Fidget Spinner',null, 'Light Up Changeable Hand Spinners Adult Glowing Spiner Stress Relief Toys',25),
    (28, 'Electronic Unlock Installation','Fengweima','Electronic Cylinder Tuya Unlock Installation Keyless Tuya Fingerprint Smart Password lock',67),
    (29, 'Wireless headset','Saramonic','Wireless full duplex gaming headset', 140),
    (30, 'Electronic thermometer',null,'Digital Forehead Thermometer Electronic Contactless',54);

# SELECT *
# FROM `product`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `product Image`



# SELECT *
# FROM `product_image`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `belongs to`

INSERT INTO `belongs_to` (`category_id`, `product_id`) VALUES
    (4, 1),
    (4, 6),
    (5, 11),
    (5, 16),
    (8, 2),
    (5, 7),
    (6, 12),
    (9, 16),
    (3, 3),
    (3, 8),
    (6, 13),
    (6, 4),
    (9, 9),
    (7, 14),
    (2, 5),
    (1, 10),
    (6, 15),
    (5, 17),
    (9, 17),
    (9, 18),
    (1, 19),
    (5, 20),
    (1, 21),
    (2, 21),
    (2, 22),
    (7, 23),
    (6, 24),
    (6, 25),
    (2, 26),
    (2, 27),
    (6, 28),
    (9, 28),
    (10, 29),
    (11, 30);

# SELECT *
# FROM belongs_to;

-- ---------------------------------------------------------------------------------------------------------------------
-- `property`

INSERT INTO `property` (`property_id`, `property_name`, `value`, `price_increment`) VALUES
    (1, 'Color', 'Black', 0),
    (2, 'Size', 'Large', 100),
    (3, 'capacity', '256 GB', 100),
    (4, 'camera quality', '4K', 180), # for drones
    (5, 'Number of additional pieces', 'Extra 100',20); # dominos,legoes
    
# SELECT *
# FROM `property`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `variant`

INSERT INTO `variant` (`variant_id`, `weight`) VALUES
    (1, 12.2),
    (2, 1.5),
    (3, 2.0),
    (4, 0.8),
    (5, 1.0);

# SELECT *
# FROM `variant`;

-- ---------------------------------------------------------------------------------------------------------------------
--  `varies_on`

INSERT INTO `varies_on` (`product_id`, `property_id`, `variant_id`) VALUES
    (1, 1, 1),
    (1, 2, 2),
    (3, 3, 3),
    (2, 3, 4);

# SELECT *
# FROM `varies_on`;

-- ---------------------------------------------------------------------------------------------------------------------
--  `warehouse` table

INSERT INTO `warehouse` (`warehouse_id`, `street_number`, `street_name`, `city`, `zipcode`) VALUES
    (1, '123', 'Galle road', 'Katubedda', 12345);
   
# SELECT *
# FROM `warehouse`;

-- ---------------------------------------------------------------------------------------------------------------------
--  `warehouse_contact`

INSERT INTO `warehouse_contact` (`telephone_number`, `warehouse_id`) VALUES
    ('0776969481', 1);
    
# SELECT *
# FROM `warehouse_contact`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `inventory`

INSERT INTO `inventory` (`warehouse_id`, `variant_id`, `sku`, `count`) VALUES
    (1, 1, 'SKU123', 50),
    (1, 2, 'SKU456', 30);

# SELECT *
# FROM `inventory`;

-- ---------------------------------------------------------------------------------------------------------------------
--  `user`

INSERT INTO `user` (`user_id`, `role`) VALUES
    (1, 'REG_CUST'),
    (2, 'REG_CUST'),
    (3, 'GUEST_CUST');

# SELECT *
# FROM `user`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `registered_customer`

INSERT INTO `registered_user` (`user_id`, `email`, `password`, `first_name`, `last_name`) VALUES
    (1, 'dulinaperera@gmail.com', 'password123', 'Dulina', 'Perera'),
    (2, 'nethumrathnayake@gmail.com', 'IloveMethmini', 'Nethum', 'Rathnayake');

# SELECT *
# FROM `registered_customer`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `customer_contact`

INSERT INTO `user_contact` (`user_id`, `telephone_number`) VALUES
    (1, '0702632639'),
    (2, '0714283876');

# SELECT *
# FROM `customer_contact`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `customer_address`

INSERT INTO `user_address` (`address_id`, `user_id`, `street_number`, `street_name`, `city`, `zipcode`) VALUES
    (1, 1, '5/16', 'Sri Dhammaruchi Mawatha', 'Wadduwa', 12560),
    (2, 2, '123', 'Kesbewa Road', 'Kesbewaa', 12345);

# SELECT *
# FROM `customer_address`;

-- ---------------------------------------------------------------------------------------------------------------------
--  `cart`

INSERT INTO `cart` (`user_id`, `total_price`) VALUES
    (1, 0),
    (2, 0),
    (3, 0);

# SELECT *
# FROM `cart`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `cart_item` table

INSERT INTO `cart_item` (`user_id`, `variant_id`, `quantity`) VALUES
    (1, 1, 2),
    (2, 3, 1),
    (3, 5, 3);

# SELECT *
# FROM `cart_item`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `order` table

INSERT INTO `order` (`order_id`, `date`, `total_payment`, `payment_method`, `delivery_method`, `email`, `street_number`, `street_name`, `city`, `zipcode`) VALUES
    (1, '2023-01-15 08:30:00', 149.97, 'Credit Card', 'Express Shipping', 'dulinaperera@gmail.com', '456', 'Godagama', 'Kaluthara', 98765),
    (2, '2023-03-20 10:00:00', 239.97, 'Credit Card', 'Express Shipping', 'nethumrathnayake@gmail.com', '456', 'Kawdana', 'Dehiwala', 98765);

# SELECT *
# FROM `order`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `order_contact`

INSERT INTO `order_contact` (`order_id`, `telephone_number`) VALUES
    (1, '0702632639'),
    (2, '0705654028');

# SELECT *
# FROM `order_contact`;

-- ---------------------------------------------------------------------------------------------------------------------
-- `order_item`

INSERT INTO `order_item` (`order_id`, `variant_id`, `warehouse_id`, `count`) VALUES
    (1, 1, 1, 2),
    (2, 3, 1, 1),
    (2, 5, 1, 2);

# SELECT *
# FROM `order_item`;
