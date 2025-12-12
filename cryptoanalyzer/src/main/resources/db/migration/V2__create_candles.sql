CREATE TABLE candles (
    id BIGSERIAL PRIMARY KEY,
    crypto_id BIGINT NOT NULL REFERENCES cryptocurrencies(id),
    timestamp BIGINT NOT NULL,
    open FLOAT NOT NULL,
    high FLOAT NOT NULL,
    low FLOAT NOT NULL,
    close FLOAT NOT NULL,
    volume FLOAT NOT NULL,
    is_struct VARCHAR(2)
)