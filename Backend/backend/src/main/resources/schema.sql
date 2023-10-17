\c cstore;

DROP TRIGGER IF EXISTS "update_variant" ON "varies_on";
DROP FUNCTION IF EXISTS "update_variant";

DROP FUNCTION IF EXISTS "properties_from_product";
DROP FUNCTION IF EXISTS "count_stocks";
DROP FUNCTION IF EXISTS "images_from_product";
DROP FUNCTION IF EXISTS "categories_from_product";
DROP FUNCTION IF EXISTS "products_from_category";

DROP VIEW IF EXISTS "leaf_category";
DROP VIEW IF EXISTS "root_category";

DROP TABLE IF EXISTS "order_item";
DROP TABLE IF EXISTS "order_contact";
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS "cart_item";
DROP TABLE IF EXISTS "cart";
DROP TABLE IF EXISTS "user_address";
DROP TABLE IF EXISTS "user_contact";
DROP TABLE IF EXISTS "registered_user";
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "inventory";
DROP TABLE IF EXISTS "warehouse_contact";
DROP TABLE IF EXISTS "warehouse";
DROP TABLE IF EXISTS "varies_on";
DROP TABLE IF EXISTS "variant";
DROP TABLE IF EXISTS "property";
DROP TABLE IF EXISTS "belongs_to";
DROP TABLE IF EXISTS "product_image";
DROP TABLE IF EXISTS "product";
DROP TABLE IF EXISTS "image";
DROP TABLE IF EXISTS "sub_category";
DROP TABLE IF EXISTS "category";

-- Category
DROP TABLE IF EXISTS "category";
CREATE TABLE "category" (
    "category_id"          BIGSERIAL,
    "category_name"        VARCHAR (40),
    "category_description" TEXT,
    PRIMARY KEY ("category_id")
);

-- Sub Category
DROP TABLE IF EXISTS "sub_category";
CREATE TABLE "sub_category" (
    "category_id"     BIGINT,
    "sub_category_id" BIGINT,
    CHECK ("category_id" != "sub_category_id"),
    PRIMARY KEY ("category_id", "sub_category_id"),
    FOREIGN KEY ("category_id") REFERENCES "category" ("category_id") ON DELETE CASCADE,
    FOREIGN KEY ("sub_category_id") REFERENCES "category" ("category_id") ON DELETE CASCADE
);

-- Image
DROP TABLE IF EXISTS "image";
CREATE TABLE "image" (
    "image_id" BIGSERIAL,
    "url"      VARCHAR (100),
    PRIMARY KEY ("image_id")
);

-- WholeProduct
DROP TABLE IF EXISTS "product";
CREATE TABLE "product" (
    "product_id"   BIGSERIAL,
    "product_name" VARCHAR(100),
    "base_price"   NUMERIC(10, 2) DEFAULT 0,
    "brand"        VARCHAR(40),
    "description"  TEXT,
    "image_url"    VARCHAR(100),
    PRIMARY KEY ("product_id")
);

-- WholeProduct Image
DROP TABLE IF EXISTS "product_image";
CREATE TABLE "product_image" (
    "image_id"   BIGINT,
    "product_id" BIGINT,
    PRIMARY KEY ("image_id"),
    FOREIGN KEY ("product_id") REFERENCES "product" ("product_id") ON DELETE CASCADE,
    FOREIGN KEY ("image_id") REFERENCES "image" ("image_id") ON DELETE CASCADE
);

-- Belongs to
DROP TABLE IF EXISTS "belongs_to";
CREATE TABLE "belongs_to" (
    "category_id" BIGINT,
    "product_id"  BIGINT,
    PRIMARY KEY ("category_id", "product_id"),
    FOREIGN KEY ("category_id") REFERENCES "category" ("category_id") ON DELETE CASCADE,
    FOREIGN KEY ("product_id") REFERENCES "product" ("product_id") ON DELETE CASCADE
);

-- ProductSelectionProperty
DROP TABLE IF EXISTS "property";
CREATE TABLE "property" (
    "property_id"     BIGSERIAL,
    "property_name"   VARCHAR (40),
    "value"           VARCHAR (40),
    "image_url"       VARCHAR (100),
    "price_increment" NUMERIC (10, 2) DEFAULT 0,
    PRIMARY KEY ("property_id")
);

-- Variant
DROP TABLE IF EXISTS "variant";
CREATE TABLE "variant" (
    "variant_id" BIGSERIAL,
    "price"      NUMERIC (10, 2) DEFAULT 0,
    "weight"     NUMERIC (5, 2),
    PRIMARY KEY ("variant_id")
);

-- Varies, based on
DROP TABLE IF EXISTS "varies_on";
CREATE TABLE "varies_on" (
    "product_id"  BIGINT,
    "property_id" BIGINT,
    "variant_id"  BIGINT,
    PRIMARY KEY ("product_id","variant_id","property_id"),
    FOREIGN KEY ("product_id") REFERENCES "product" ("product_id") ON DELETE CASCADE,
    FOREIGN KEY ("variant_id") REFERENCES "variant" ("variant_id") ON DELETE CASCADE,
    FOREIGN KEY ("property_id") REFERENCES "property" ("property_id") ON DELETE CASCADE
);

-- Warehouse
DROP TABLE IF EXISTS "warehouse";
CREATE TABLE "warehouse" (
    "warehouse_id"  BIGSERIAL,
    "street_number" VARCHAR (10),
    "street_name"   VARCHAR (60),
    "city"          VARCHAR (40),
    "zipcode"       INTEGER,
    PRIMARY KEY ("warehouse_id")
);

-- Warehouse Contact
DROP TABLE IF EXISTS "warehouse_contact";
CREATE TABLE "warehouse_contact" (
    "telephone_number" VARCHAR (12),
    "warehouse_id"     BIGINT,
    PRIMARY KEY ("telephone_number"),
    FOREIGN KEY ("warehouse_id") REFERENCES "warehouse" ("warehouse_id") ON DELETE CASCADE
);

-- Inventory
DROP TABLE IF EXISTS "inventory";
CREATE TABLE "inventory" (
    "warehouse_id" BIGINT,
    "variant_id"   BIGINT,
    "sku"          VARCHAR(20),
    "count"        INTEGER,
    PRIMARY KEY ("warehouse_id", "variant_id"),
    FOREIGN KEY ("warehouse_id") REFERENCES "warehouse" ("warehouse_id") ON DELETE CASCADE,
    FOREIGN KEY ("variant_id") REFERENCES "variant" ("variant_id") ON DELETE CASCADE
);

-- User
DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
    "user_id" BIGSERIAL,
    "role"    VARCHAR (10) CHECK ("role" IN ('GUEST_CUST', 'REG_CUST', 'ADMIN')),
    PRIMARY KEY ("user_id")
);

-- Registered User
DROP TABLE IF EXISTS "registered_user";
CREATE TABLE "registered_user" (
    "user_id"     BIGINT,
    "email"       VARCHAR (60) NOT NULL UNIQUE,
    "password"    VARCHAR (60) NOT NULL,
    "first_name"  VARCHAR (20),
    "last_name"   VARCHAR (20),
    "locked"      BOOLEAN NOT NULL,
    "enabled"     BOOLEAN NOT NULL,
    PRIMARY KEY ("user_id"),
    FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE
);

-- User Contact
DROP TABLE IF EXISTS "user_contact";
CREATE TABLE "user_contact" (
    "user_id"          BIGINT,
    "telephone_number" VARCHAR (12),
    PRIMARY KEY ("user_id", "telephone_number"),
    FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE
);

-- User Address
DROP TABLE IF EXISTS "user_address";
CREATE TABLE "user_address" (
    "address_id"    BIGSERIAL,
    "user_id"       BIGINT,
    "street_number" VARCHAR (10),
    "street_name"   VARCHAR (60),
    "city"          VARCHAR (40),
    "zipcode"       INTEGER,
    PRIMARY KEY ("address_id"),
    FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE
);

-- Cart
DROP TABLE IF EXISTS "cart";
CREATE TABLE "cart" (
    "user_id"     BIGINT,
    "total_price" NUMERIC (12, 2),
    PRIMARY KEY ("user_id"),
    FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE
);

-- Contains
DROP TABLE IF EXISTS "cart_item";
CREATE TABLE "cart_item" (
    "user_id"    BIGINT,
    "variant_id" BIGINT,
    "quantity"   INTEGER,
    PRIMARY KEY ("user_id", "variant_id"),
    FOREIGN KEY ("user_id") REFERENCES "cart" ("user_id") ON DELETE CASCADE,
    FOREIGN KEY ("variant_id") REFERENCES "variant" ("variant_id") ON DELETE CASCADE
);

-- Order
DROP TABLE IF EXISTS "order";
CREATE TABLE "order" (
    "order_id"        BIGSERIAL,
    "date"            TIMESTAMP,
    "total_payment"   NUMERIC (12, 2),
    "payment_method"  VARCHAR (20),
    "delivery_method" VARCHAR (40),
    "email"           VARCHAR (60),
    "street_number"   VARCHAR (10),
    "street_name"     VARCHAR (60),
    "city"            VARCHAR (40),
    "zipcode"         INTEGER,
    PRIMARY KEY ("order_id")
);

-- Order Contact
DROP TABLE IF EXISTS "order_contact";
CREATE TABLE "order_contact" (
                                 "order_id"         BIGINT,
                                 "telephone_number" VARCHAR (12),
                                 PRIMARY KEY ("order_id", "telephone_number"),
                                 FOREIGN KEY ("order_id") REFERENCES "order" ("order_id") ON DELETE CASCADE
);

-- Order Item
DROP TABLE IF EXISTS "order_item";
CREATE TABLE "order_item" (
    "order_id"     BIGINT,
    "variant_id"   BIGINT,
    "warehouse_id" BIGINT,
    "count"        INTEGER,
    PRIMARY KEY ("order_id", "variant_id", "warehouse_id"),
    FOREIGN KEY ("order_id") REFERENCES "order" ("order_id") ON DELETE CASCADE,
    FOREIGN KEY ("variant_id") REFERENCES "variant" ("variant_id"),
    FOREIGN KEY ("warehouse_id") REFERENCES "warehouse" ("warehouse_id")
);


------------------------------------------------------------------------------------------------------------------------
-- VIEWS----------------------------------------------------------------------------------------------------------------

DROP VIEW IF EXISTS "root_category";
CREATE VIEW "root_category" AS
    SELECT *
    FROM "category"
    WHERE "category_id" NOT IN (SELECT DISTINCT "sub_category_id"
                                FROM sub_category);

-- SELECT *
-- FROM "base_category";

------------------------------------------------------------------------------------------------------------------------

/**
 * View: leaf_category
 *
 * This view selects categories that are considered leaf categories, meaning they are not subcategories of any other category.
 * It filters categories based on their "category_id" and their presence in the "sub_category" table.
 *
 * Categories included in this view are those with a "category_id" that exists in the "sub_category" table's "sub_category_id" column but does not exist in the "category_id" column of the "sub_category" table.
 */
DROP VIEW IF EXISTS "leaf_category";
CREATE VIEW "leaf_category" AS
    SELECT *
    FROM "category"
    WHERE "category_id" IN (SELECT DISTINCT "sub_category_id"
                            FROM sub_category) AND "category_id" NOT IN (SELECT DISTINCT "category_id"
                                                                         FROM sub_category);

-- SELECT *
-- FROM "leaf_category";


------------------------------------------------------------------------------------------------------------------------
-- PROCEDURES-----------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION "products_from_category"(c_id BIGINT)
    RETURNS TABLE (
                      "product_id"   BIGINT,
                      "product_name" VARCHAR(100),
                      "base_price"   NUMERIC(10, 2),
                      "brand"        VARCHAR(40),
                      "description"  TEXT,
                      "image_url"    VARCHAR(100)
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT DISTINCT p.*
        FROM "belongs_to" AS bt NATURAL LEFT OUTER JOIN "product" AS p
        WHERE bt."category_id" = c_id;
END
$$ LANGUAGE plpgsql;

-- SELECT *
-- FROM "products_from_category"(4);

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION "categories_from_product"(p_id BIGINT)
    RETURNS TABLE (
        "category_id"          BIGINT,
        "category_name"        VARCHAR (40),
        "category_description" TEXT
    ) AS $$
BEGIN
    RETURN QUERY
        SELECT DISTINCT c.*
        FROM "category" AS c NATURAL LEFT OUTER JOIN "belongs_to" AS bt NATURAL LEFT OUTER JOIN "product" AS p
        WHERE p."product_id" = p_id;
END
$$ LANGUAGE plpgsql;

-- SELECT *
-- FROM "categories_from_product"(1);

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION "images_from_product"(p_id BIGINT)
    RETURNS TABLE (
        "image_id" BIGINT,
        "url"      VARCHAR (100)
    ) AS $$
BEGIN
    RETURN QUERY
        SELECT DISTINCT i.*
        FROM "image" AS i NATURAL LEFT OUTER JOIN "product_image" AS pi NATURAL LEFT OUTER JOIN "product" AS p
        WHERE p."product_id" = p_id;
END
$$ LANGUAGE plpgsql;

-- SELECT *
-- FROM "images_from_product"(1);

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION "properties_from_product"(p_id BIGINT)
    RETURNS TABLE (
        "property_id"     BIGINT,
        "property_name"   VARCHAR (40),
        "value"           VARCHAR (40),
        "image_url"       VARCHAR (100),
        "price_increment" NUMERIC (10, 2)
    )
AS $$
BEGIN
    RETURN QUERY
        SELECT pp.*
        FROM "property" AS pp NATURAL LEFT OUTER JOIN "varies_on" AS vo NATURAL LEFT OUTER JOIN "product" AS pd
        WHERE pd.product_id = p_id
        ORDER BY pp.property_name;
END
$$ LANGUAGE plpgsql;

-- SELECT *
-- FROM properties_from_product(1);

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION "count_stocks"(p_id BIGINT)
    RETURNS INTEGER AS $$
DECLARE stock_count INTEGER;
BEGIN
    SELECT SUM("count") INTO stock_count
    FROM "varies_on" AS vo NATURAL LEFT OUTER JOIN "inventory" AS i
    WHERE vo."product_id" = p_id;

    IF stock_count NOTNULL
        THEN RETURN stock_count;
    ELSE RETURN 0;
    END IF;
END
$$ LANGUAGE plpgsql;

-- SELECT count_stocks(2);

/*SELECT lc.category_id, COUNT(DISTINCT oi.order_id) AS order_count
FROM "leaf_category" AS lc
         NATURAL LEFT OUTER JOIN "belongs_to" AS bt
         NATURAL LEFT OUTER JOIN "varies_on" AS vo
         NATURAL LEFT OUTER JOIN "order_item" AS oi
GROUP BY lc."category_id";*/


------------------------------------------------------------------------------------------------------------------------
-- Triggers-------------------------------------------------------------------------------------------------------------


CREATE OR REPLACE FUNCTION "update_variant"() RETURNS TRIGGER AS $$
DECLARE product_price NUMERIC(10, 2);
DECLARE property_price NUMERIC(10, 2);
DECLARE variant_price NUMERIC(10, 2);
BEGIN
    product_price := 0;
    property_price := 0;
    variant_price := 0;

    SELECT "price" INTO variant_price
    FROM "variant"
    WHERE "variant_id" = NEW."variant_id";

    IF variant_price = 0 THEN
        SELECT "base_price" INTO product_price
        FROM "product"
        WHERE "product_id" = NEW."product_id";

        UPDATE "variant"
        SET "price" = product_price
        WHERE "variant_id" = NEW."variant_id";
    END IF;

    SELECT "price_increment" INTO property_price
    FROM "property"
    WHERE "property_id" = NEW."property_id";

    UPDATE "variant"
    SET "price" = "price" + property_price
    WHERE "variant_id" = NEW."variant_id";

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS "update_variant" ON "varies_on";
CREATE TRIGGER "update_variant"
    AFTER INSERT ON "varies_on"
    FOR EACH ROW
EXECUTE FUNCTION "update_variant"();
