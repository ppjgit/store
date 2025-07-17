CREATE TABLE orders
(
    id             BIGSERIAL PRIMARY KEY,
    order_number   VARCHAR(50)    NOT NULL UNIQUE,
    customer_name  VARCHAR(255)   NOT NULL,
    customer_email VARCHAR(255),
    customer_phone VARCHAR(50),
    total_amount   DECIMAL(10, 2) NOT NULL,
    created_at     TIMESTAMP      NOT NULL,
    CONSTRAINT uq_customer_email_phone UNIQUE (customer_email, customer_phone)
);

CREATE TABLE order_items
(
    id        BIGSERIAL PRIMARY KEY,
    order_id  BIGINT         NOT NULL,
    item_name VARCHAR(255)   NOT NULL,
    quantity  INT            NOT NULL,
    price     DECIMAL(10, 2) NOT NULL,
    subtotal  DECIMAL(10, 2) NOT NULL,
    sku       VARCHAR(100),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    CONSTRAINT uq_order_id_sku UNIQUE (sku)
);

CREATE INDEX idx_order_number ON orders (order_number);
CREATE INDEX idx_order_items_order_id ON order_items (order_id);
