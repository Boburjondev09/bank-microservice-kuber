CREATE TABLE exchange_rate (
                               id BIGSERIAL PRIMARY KEY,

                               base_currency_id BIGINT NOT NULL,
                               target_currency_id BIGINT NOT NULL,

                               rate NUMERIC(18,6) NOT NULL,

                               provider VARCHAR(100),

                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT fk_base_currency
                                   FOREIGN KEY (base_currency_id)
                                       REFERENCES currency(id),

                               CONSTRAINT fk_target_currency
                                   FOREIGN KEY (target_currency_id)
                                       REFERENCES currency(id)
);

-------------
INSERT INTO exchange_rate
(base_currency_id,target_currency_id,rate,provider)
VALUES
    (1,4,12850.00,'Central Bank'),
    (2,1,1.17,'ECB'),
    (3,2,1.15,'BOE');