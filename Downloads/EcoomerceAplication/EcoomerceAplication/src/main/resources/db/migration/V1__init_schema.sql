-- ============================================================
-- V1: Esquema completo del sistema de ecommerce
-- ============================================================

-- ── Sequences ───────────────────────────────────────────────
CREATE SEQUENCE IF NOT EXISTS customer_id_seq      START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS address_id_seq       START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seller_id_generator  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS product_id_generator START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS cart_id_seq          START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS order_id_seq         START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS "orderStatus_id_seq" START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS stock_id_seq         START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS order_item_id_seq    START WITH 1 INCREMENT BY 1;

-- ── customers ───────────────────────────────────────────────
CREATE TABLE customers (
    customer_id   BIGINT       DEFAULT nextval('customer_id_seq') PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(20)  NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    altausuario   TIMESTAMP,
    iban          VARCHAR(255) NOT NULL UNIQUE
);

-- ── address ─────────────────────────────────────────────────
CREATE TABLE address (
    address_id    BIGINT       DEFAULT nextval('address_id_seq') PRIMARY KEY,
    calle         VARCHAR(255) NOT NULL,
    localidad     VARCHAR(255),
    provincia     VARCHAR(255),
    codigo_postal VARCHAR(10),
    pais          VARCHAR(255) NOT NULL,
    customer_id   BIGINT REFERENCES customers(customer_id)
);

-- ── sellers ─────────────────────────────────────────────────
CREATE TABLE sellers (
    seller_id     BIGINT       DEFAULT nextval('seller_id_generator') PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(20)  NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL UNIQUE
);

-- ── products ────────────────────────────────────────────────
CREATE TABLE products (
    product_id   BIGINT         DEFAULT nextval('product_id_generator') PRIMARY KEY,
    product_name VARCHAR(255)   NOT NULL,
    price        NUMERIC(10, 2) NOT NULL,
    description  VARCHAR(255),
    manufacture  VARCHAR(255)   NOT NULL,
    quantity     INTEGER        NOT NULL,
    category     VARCHAR(50),
    status       VARCHAR(50),
    seller_id    BIGINT         NOT NULL REFERENCES sellers(seller_id)
);

-- Tabla join generada por @OneToMany sin mappedBy en Seller.product
CREATE TABLE sellers_product (
    sellers_seller_id BIGINT NOT NULL REFERENCES sellers(seller_id),
    product_id        BIGINT NOT NULL REFERENCES products(product_id),
    PRIMARY KEY (sellers_seller_id, product_id)
);

-- ── stock ────────────────────────────────────────────────────
CREATE TABLE stock (
    stock_id           BIGINT  DEFAULT nextval('stock_id_seq') PRIMARY KEY,
    product_id         BIGINT  NOT NULL UNIQUE REFERENCES products(product_id),
    available_quantity INTEGER NOT NULL,
    reserved_quantity  INTEGER NOT NULL DEFAULT 0,
    last_updated       TIMESTAMP
);

-- ── cart ────────────────────────────────────────────────────
CREATE TABLE cart (
    cart_id     BIGINT DEFAULT nextval('cart_id_seq') PRIMARY KEY,
    customer_id BIGINT REFERENCES customers(customer_id)
);

-- ── orders ──────────────────────────────────────────────────
CREATE TABLE orders (
    order_id     BIGINT           DEFAULT nextval('order_id_seq') PRIMARY KEY,
    date         DATE,
    order_status VARCHAR(50)      NOT NULL,
    total        DOUBLE PRECISION,
    card_number  VARCHAR(255),
    customer_id  BIGINT REFERENCES customers(customer_id),
    address_id   BIGINT REFERENCES address(address_id)
);

-- ── order_items ─────────────────────────────────────────────
CREATE TABLE order_items (
    order_item_id    BIGINT         DEFAULT nextval('order_item_id_seq') PRIMARY KEY,
    order_id         BIGINT         NOT NULL REFERENCES orders(order_id),
    product_id       BIGINT         NOT NULL REFERENCES products(product_id),
    quantity         INTEGER        NOT NULL,
    price_at_purchase NUMERIC(10, 2) NOT NULL
);

-- ── order_status (OrderStatusHistory) ───────────────────────
CREATE TABLE order_status (
    order_status_id BIGINT      DEFAULT nextval('"orderStatus_id_seq"') PRIMARY KEY,
    order_status    VARCHAR(50) NOT NULL,
    timestamp       TIMESTAMP,
    order_id        BIGINT REFERENCES orders(order_id)
);
