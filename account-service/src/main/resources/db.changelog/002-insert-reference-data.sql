----------------------- Account Test Data -----------------------
INSERT INTO account (account_number, owner_name, currency_code, balance, status)
VALUES
    ('ACC-UZ-998123', 'Urunov Khamdamboy', 'UZS', 25000000.00, 'ACTIVE'),
    ('ACC-UZ-998456', 'Dilshodbek Alimov', 'UZS', 150000.50, 'ACTIVE'),
    ('ACC-RU-776655', 'Ivan Petrov', 'RUB', 145000.00, 'ACTIVE'),
    ('ACC-DE-112233', 'Hans Müller', 'EUR', 4500.00, 'ACTIVE'),
    ('SYSTEM_ATM_04', 'Tashkent Central ATM #04', 'USD', 50000.00, 'ACTIVE'),
    ('MERCHANT-STEAM-EU', 'Steam Games Europe B.V.', 'EUR', 1250400.00, 'ACTIVE'),
    ('ACC-UZ-998777', 'Anvar Karimov', 'UZS', 0.00, 'PENDING'),
    ('ACC-US-888888', 'John Doe', 'USD', 50.00, 'BLOCKED');