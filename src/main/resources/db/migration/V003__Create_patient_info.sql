CREATE TABLE patient_info (
                    patient_id INT NOT NULL AUTO_INCREMENT,
                    first_name VARCHAR(50) NOT NULL,
                    last_name VARCHAR(50) NOT NULL,
                    card_number VARCHAR(255) NOT NULL,
                    address VARCHAR(255) NOT NULL,
                    phone_number VARCHAR(255) NOT NULL,
                    date DATETIME NOT NULL,
                    PRIMARY KEY (`patient_id`)
);

CREATE TABLE diagnosis_info (
    diagnosis_id INT NOT NULL AUTO_INCREMENT,
    patient_id INT NOT NULL,
    diagnosis VARCHAR(1000),
    medicine VARCHAR(1000),
    date DATETIME NOT NULL,
    PRIMARY KEY (`diagnosis_id`),
    FOREIGN KEY (`patient_id`) REFERENCES patient_info(`patient_id`)
)