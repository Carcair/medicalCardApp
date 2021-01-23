CREATE TABLE users (
    username VARCHAR (50) NOT NULL,
    password VARCHAR (50) NOT NULL,
    enabled BOOLEAN NOT NULL,
    PRIMARY KEY (`username`)
);

INSERT INTO users (username, password, enabled) VALUES ('admin', 'admin', true);
INSERT INTO users (username, password, enabled) VALUES ('user', 'user', true)