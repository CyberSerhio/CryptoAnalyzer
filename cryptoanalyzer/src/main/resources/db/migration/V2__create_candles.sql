CREATE TABLE candles (
    id BIGSERIAL PRIMARY KEY,
    crypto_id BIGINT NOT NULL REFERENCES cryptocurrencies(id),
    cTimestamp BIGINT NOT NULL,
    cOpen FLOAT NOT NULL,
    cHigh FLOAT NOT NULL,
    cLow FLOAT NOT NULL,
    cClose FLOAT NOT NULL,
    volume FLOAT NOT NULL,
    is_struct VARCHAR(2)
)