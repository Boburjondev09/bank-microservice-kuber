----------------------- Transaction Demo Data -----------------------
INSERT INTO transaction
(reference_number, sender_account_number, receiver_account_number, amount, currency_code, type, status, description)
VALUES
    -- Перевод в долларах (Тип: TRANSFER, Статус: COMPLETED)
    ('TXN-2026-001', 'ACC-123456', 'ACC-789012', 150.00, 'USD', 'TRANSFER', 'COMPLETED', 'Payment for freelance services'),

    -- Депозит в узбекских сумах (Тип: DEPOSIT, Статус: COMPLETED)
    ('TXN-2026-002', 'SYSTEM', 'ACC-998877', 500000.00, 'UZS', 'DEPOSIT', 'COMPLETED', 'ATM Cash Deposit'),

    -- Платеж в евро, который завис (Тип: PAYMENT, Статус: PENDING)
    ('TXN-2026-003', 'ACC-789012', 'MERCHANT-99', 45.50, 'EUR', 'PAYMENT', 'PENDING', 'Online store purchase'),

    -- Ошибка перевода в рублях (Тип: TRANSFER, Статус: FAILED)
    ('TXN-2026-004', 'ACC-111222', 'ACC-333444', 2500.00, 'RUB', 'TRANSFER', 'FAILED', 'Insufficient funds');