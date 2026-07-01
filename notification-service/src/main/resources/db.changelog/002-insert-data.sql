----------------------- Notification Data -----------------------
INSERT INTO notification
(recipient, subject, body, type, status, reference_id, sent_at)
VALUES
    -- Уведомление об успешном переводе (Тип: EMAIL, Статус: SENT)
    ('ACC-123456', 'Transfer Successful', 'Your transfer of 150.00 USD to ACC-789012 has been successfully completed.', 'EMAIL', 'SENT', 'TXN-2026-001', CURRENT_TIMESTAMP),

    -- СМС-уведомление о депозите (Тип: SMS, Статус: SENT)
    ('ACC-998877', 'Account Deposit', 'Your account was credited with 500000.00 UZS via ATM Cash Deposit.', 'SMS', 'SENT', 'TXN-2026-002', CURRENT_TIMESTAMP),

    -- Уведомление в обработке (Тип: PUSH, Статус: PENDING)
    ('ACC-789012', 'Payment Processing', 'Your payment of 45.50 EUR to MERCHANT-99 is currently pending.', 'PUSH', 'PENDING', 'TXN-2026-003', NULL),

    -- Предупреждение об ошибке (Тип: EMAIL, Статус: SENT)
    ('ACC-111222', 'Transaction Failed', 'Your transfer of 2500.00 RUB failed. Reason: Insufficient funds.', 'EMAIL', 'SENT', 'TXN-2026-004', CURRENT_TIMESTAMP);