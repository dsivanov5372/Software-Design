CREATE TABLE IF NOT EXISTS users (
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    login    VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);