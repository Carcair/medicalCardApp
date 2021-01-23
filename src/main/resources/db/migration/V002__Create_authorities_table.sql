CREATE TABLE authorities (
    username VARCHAR (50) NOT NULL,
    authorities VARCHAR(50) NOT NULL,
    FOREIGN KEY (`username`) REFERENCES users(`username`)
);

INSERT INTO authorities (authorities, authorities.username) VALUES ('ROLE_USER', 'user');
INSERT INTO authorities (authorities, authorities.username) VALUES ('ROLE_ADMIN', 'admin');