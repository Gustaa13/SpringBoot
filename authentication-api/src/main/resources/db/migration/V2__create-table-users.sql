CREATE TABLE users (
    id VARCHAR(100) PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role INT NOT NULL
);