CREATE TABLE candles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_high FLOAT NOT NULL,
    c_open FLOAT NOT NULL,
    c_close FLOAT NOT NULL,
    c_low FLOAT NOT NULL,
    c_timestamp TIMESTAMP NOT NULL
)