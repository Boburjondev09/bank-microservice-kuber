CREATE TABLE notification (
                              id BIGSERIAL PRIMARY KEY,
                              recipient VARCHAR(255) NOT NULL,
                              subject VARCHAR(255) NOT NULL,
                              body TEXT NOT NULL,
                              type VARCHAR(50) NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              reference_id VARCHAR(255),
                              sent_at TIMESTAMP,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);