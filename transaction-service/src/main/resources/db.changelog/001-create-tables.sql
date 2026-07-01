CREATE TABLE transaction (
                             id BIGSERIAL PRIMARY KEY,
                             reference_number VARCHAR(255) NOT NULL UNIQUE,
                             sender_account_number VARCHAR(255) NOT NULL,
                             receiver_account_number VARCHAR(255) NOT NULL,
                             amount NUMERIC(19,2) NOT NULL,
                             currency_code VARCHAR(3) NOT NULL,
                             type VARCHAR(50) NOT NULL,
                             status VARCHAR(50) NOT NULL,
                             description VARCHAR(500),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);