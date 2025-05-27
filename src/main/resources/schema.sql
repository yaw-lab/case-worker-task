--CREATE TABLE cases (
--    id INT PRIMARY KEY AUTO_INCREMENT,
--    case_number VARCHAR(50) NOT NULL,
--    title VARCHAR(255) NOT NULL,
--    description CLOB,
--    status VARCHAR(50) NOT NULL,
--    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
--);


DROP TABLE IF EXISTS cases CASCADE; -- CASCADE is important if other tables have foreign keys to 'cases'
CREATE TABLE cases (
    id SERIAL PRIMARY KEY,
    case_number VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);