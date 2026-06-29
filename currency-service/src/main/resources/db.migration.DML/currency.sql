CREATE TABLE currency (
                          id BIGSERIAL PRIMARY KEY,
                          code VARCHAR(3) NOT NULL UNIQUE,
                          name VARCHAR(100) NOT NULL,
                          symbol VARCHAR(10),
                          numeric_code INTEGER,
                          fraction_digits INTEGER DEFAULT 2,
                          active BOOLEAN DEFAULT TRUE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO currency(code, name, symbol, numeric_code)
VALUES
    ('USD', 'US Dollar', '$', 840),
    ('EUR', 'Euro', '€', 978),
    ('GBP', 'Pound Sterling', '£', 826),
    ('UZS', 'Uzbek Sum', 'so''m', 860),
    ('RUB', 'Russian Ruble', '₽', 643);