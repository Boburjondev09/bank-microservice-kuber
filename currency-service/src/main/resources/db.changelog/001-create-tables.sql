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

CREATE TABLE country (
                         id BIGSERIAL PRIMARY KEY,
                         iso2 VARCHAR(2) NOT NULL UNIQUE,
                         iso3 VARCHAR(3) NOT NULL UNIQUE,
                         name VARCHAR(100) NOT NULL,
                         currency_id BIGINT NOT NULL,
                         active BOOLEAN DEFAULT TRUE,

                         CONSTRAINT fk_country_currency
                             FOREIGN KEY (currency_id)
                                 REFERENCES currency(id)
);

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
                                       REFERENCES currency(id),

                               CONSTRAINT uq_exchange_rate
                                   UNIQUE(base_currency_id, target_currency_id)
);