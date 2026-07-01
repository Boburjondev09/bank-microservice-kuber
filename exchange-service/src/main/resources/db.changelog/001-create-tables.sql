CREATE TABLE exchange_operation (
                                    id BIGSERIAL PRIMARY KEY,
                                    account_number VARCHAR(255) NOT NULL,
                                    from_currency VARCHAR(3) NOT NULL,
                                    to_currency VARCHAR(3) NOT NULL,
                                    source_amount NUMERIC(19,2) NOT NULL,
                                    converted_amount NUMERIC(19,2) NOT NULL,
                                    applied_rate NUMERIC(18,6) NOT NULL,
                                    status VARCHAR(50) NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);