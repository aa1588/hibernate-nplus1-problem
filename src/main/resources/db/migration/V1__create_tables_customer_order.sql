-- ================================
-- Customers sequence & table
-- ================================

CREATE SEQUENCE customers_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE customers (
                           id BIGINT PRIMARY KEY DEFAULT nextval('customers_seq'),
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ================================
-- Orders sequence & table
-- ================================

CREATE SEQUENCE orders_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE orders (
                        id BIGINT PRIMARY KEY DEFAULT nextval('orders_seq'),
                        customer_id BIGINT NOT NULL,
                        product_name VARCHAR(255) NOT NULL,
                        quantity INT NOT NULL,
                        price NUMERIC(10,2) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customers(id)
                                ON DELETE CASCADE
);

-- ================================
-- Indexes
-- ================================

CREATE INDEX idx_orders_customer_id ON orders(customer_id);
