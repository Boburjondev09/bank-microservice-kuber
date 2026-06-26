----------------------- Currency Reference Data-------------------
INSERT INTO currency (code, name, symbol, numeric_code)
VALUES
    ('USD', 'US Dollar', '$', 840),
    ('EUR', 'Euro', '€', 978),
    ('GBP', 'Pound Sterling', '£', 826),
    ('UZS', 'Uzbek Sum', 'so''m', 860),
    ('RUB', 'Russian Ruble', '₽', 643);

----------------------- Country Reference Data-----------------------
INSERT INTO country (iso2, iso3, name, currency_id)
VALUES
    ('US', 'USA', 'United States', 1),
    ('DE', 'DEU', 'Germany', 2),
    ('GB', 'GBR', 'United Kingdom', 3),
    ('UZ', 'UZB', 'Uzbekistan', 4),
    ('RU', 'RUS', 'Russian Federation', 5);

----------------------- Exchange Rates--------------------------------
INSERT INTO exchange_rate
(base_currency_id, target_currency_id, rate, provider)
VALUES
    (1,4,12850.00,'Central Bank'),   -- USD -> UZS
    (2,1,1.17,'ECB'),                -- EUR -> USD
    (3,2,1.15,'BOE'),                -- GBP -> EUR
    (5,4,162.50,'Central Bank'),     -- RUB -> UZS
    (1,5,79.20,'Central Bank');      -- USD -> RUB
