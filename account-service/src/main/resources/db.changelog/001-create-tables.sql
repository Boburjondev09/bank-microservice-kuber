CREATE TABLE account (
                         id BIGSERIAL PRIMARY KEY,
                         account_number VARCHAR(255) NOT NULL UNIQUE,
                         owner_name VARCHAR(255) NOT NULL,
                         currency_code VARCHAR(3) NOT NULL,
                         balance NUMERIC(19,2) NOT NULL DEFAULT 0.00,
                         status VARCHAR(50) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);