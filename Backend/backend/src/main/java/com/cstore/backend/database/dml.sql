--  `category` table
INSERT INTO `category` (`category_id`, `category_name`, `category_description`)
VALUES
    (1, 'Electronics', 'Electronic devices and accessories'),
    (2, 'Toys', 'Childrens toys and games');
    
-- `sub_category` table
INSERT INTO `sub_category` (`category_id`, `sub_category_id`)
VALUES
    (1, 101),
    (1, 102),
    (2, 201),
    (3, 301),
    (4, 401),
    (5, 501);

-- `product` table
INSERT INTO `product` (`product_id`, `product_name`, `brand`, `description`, `base_price`)
VALUES
    (1, 'iphone 14 pro', 'apple', 'A high-end smartphone with advanced features.', 500),
    (2, 'mackbook', 'apple', 'Powerful laptop for professionals.', 1000),
    (3, 'TV', 'samsung', '55-inch 4K Ultra HD LED TV for stunning visuals.', 2000),
    (4, 'Toy gun', 'Gunhub', 'A set of essential garden tools for gardening enthusiasts.',1200),
    (5, 'Childrens Book', 'StoryTime Publications', 'A captivating childrens book for young readers.', 500);

-- `belongs_to` table 
INSERT INTO `belongs_to` (`category_id`, `product_id`)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (3, 4),
    (5, 5);

-- `property` table
INSERT INTO `property` (`property_id`, `property_name`, `value`, `price_increment`)
VALUES
    (1, 'Color', 'Black', 0),
    (2, 'Size', 'Large', 100),

    

--`image` table 
INSERT INTO `image` (`image_id`, `content`)
VALUES
    (1, ''),
    (2, ''),
    (3, ''),
    (4, ''),
    (5, '');

-- `variant` table
INSERT INTO `variant` (`variant_id`, `weight`)
VALUES
    (1, 12.2),
    (2, 1.5),
    (3, 2.0),
    (4, 0.8),
    (5, 1.0);

-- `variant_image` table
INSERT INTO `variant_image` (`image_id`, `variant_id`)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);

--  `varies_on` table 
INSERT INTO `varies_on` (`product_id`, `property_id`, `variant_id`)
VALUES
    (1, 1, 1),
    (1, 2, 2),
    (3, 3, 3),
    (2, 3, 4),
    (5, 5, 5);

--  `warehouse` table
INSERT INTO `warehouse` (`warehouse_id`, `street_number`, `street_name`, `city`, `zipcode`)
VALUES
    (1, '123', 'Galle-road', 'Katubedda', 12345),
   

--  `warehouse_contact` table
INSERT INTO `warehouse_contact` (`telephone_number`, `warehouse_id`)
VALUES
    (0776969481, 1),
    

-- `inventory` table 
INSERT INTO `inventory` (`warehouse_id`, `variant_id`, `sku`, `count`)
VALUES
    (1, 1, 'SKU123', 50),
    (1, 2, 'SKU456', 30),
 

--  `customer` table
INSERT INTO `customer` (`customer_id`, `type`)
VALUES
    (1, 'user'),
    (3, 'admin');

-- `registered_customer` table 
INSERT INTO `registered_customer` (`customer_id`, `email`, `password`, `first_name`, `last_name`)
VALUES
    (1, 'dulinaperera@gmail.com', 'password123', 'Dulina', 'Perera'),
    (3, 'nethumrathnayake@gmail.com', 'IloveMethmini', 'Nethum', 'Rathnayake');

-- `customer_contact` table
INSERT INTO `customer_contact` (`customer_id`, `telephone_number`)
VALUES
    (1, 5551112222),
    (2, 9998887777),
    (3, 3334445555);

-- `customer_address` table 
INSERT INTO `customer_address` (`address_id`, `customer_id`, `street_number`, `street_name`, `city`, `zipcode`)
VALUES
    (1, 1, '456', 'Galle Road', 'Panadura', 12500),
    (3, 3, '123', 'Kesbewa Road', 'Kesbewaa', 12345);

--  `cart` table
INSERT INTO `cart` (`customer_id`, `total_price`)
VALUES
    (1, 0),
    (2, 0),
    (3, 0);

-- `cart_item` table 
INSERT INTO `cart_item` (`customer_id`, `variant_id`, `quantity`)
VALUES
    (1, 1, 2),
    (2, 3, 1),
    (3, 5, 3);

-- `order` table
INSERT INTO `order` (`order_id`, `date`, `total_payment`, `payment_method`, `delivery_method`, `email`, `street_number`, `street_name`, `city`, `zipcode`)
VALUES
    (1, '2023-01-15 08:30:00', 149.97, 'Credit Card', 'Express Shipping', 'john.doe@example.com', '456', 'Elm Street', 'Springfield', 98765),
    (2, '2023-02-05 14:15:00', 54.98, 'PayPal', 'Standard Shipping', 'jane.smith@example.com', '789', 'Maple Avenue', 'Rivertown', 54321),
    (3, '2023-03-20 10:00:00', 239.97, 'Credit Card', 'Express Shipping', 'john.doe@example.com', '456', 'Elm Street', 'Springfield', 98765);

-- `order_contact` table 
INSERT INTO `order_contact` (`order_id`, `telephone_number`)
VALUES
    (1, 5551112222),
    (2, 9998887777),
    (3, 5551112222);

-- `order_item` table 
INSERT INTO `order_item` (`order_id`, `variant_id`, `warehouse_id`, `count`)
VALUES
    (1, 1, 1, 2),
    (2, 3, 2, 1),
    (3, 5, 1, 3);


COMMIT;
