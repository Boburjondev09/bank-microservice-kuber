CREATE TABLE country (
                         id BIGSERIAL PRIMARY KEY,
                         iso2 VARCHAR(2) NOT NULL UNIQUE,
                         iso3 VARCHAR(3) NOT NULL UNIQUE,
                         name VARCHAR(100) NOT NULL,
                         currency_id NUMBER NOT NULL,
                         active BOOLEAN DEFAULT TRUE,

                         CONSTRAINT fk_country_currency
                             FOREIGN KEY (currency_id)
                                 REFERENCES currency(id)
);

INSERT INTO country(iso2,iso3,name,currency_id)
VALUES
    ('UZ','UZB','Uzbekistan',4),
    ('US','USA','United States',1),
    ('DE','DEU','Germany',2),
    ('GB','GBR','United Kingdom',3),
    ('RU','RUBL','RUSSIA FEDRATION',5)
;