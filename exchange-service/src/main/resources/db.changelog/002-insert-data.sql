----------------------- Exchange Operation Demo Data -----------------------
INSERT INTO exchange_operation
(account_number, from_currency, to_currency, source_amount, converted_amount, applied_rate, status)
VALUES
    -- Конвертация USD в UZS (Успешно)
    ('ACC-UZ-998123', 'USD', 'UZS', 100.00, 1285000.00, 12850.000000, 'COMPLETED'),

    -- Конвертация EUR в USD (Успешно)
    ('ACC-DE-112233', 'EUR', 'USD', 50.00, 58.50, 1.170000, 'COMPLETED'),

    -- Конвертация RUB в UZS, которая зависла в обработке (В ожидании)
    ('ACC-RU-776655', 'RUB', 'UZS', 1000.00, 162500.00, 162.500000, 'PENDING'),

    -- Ошибка конвертации из-за резкого изменения курса (Ошибка)
    ('ACC-US-888888', 'USD', 'RUB', 500.00, 0.00, 79.200000, 'FAILED');