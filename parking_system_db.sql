CREATE DATABASE parking_system_db 
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

CREATE USER 'parking_system_db_user'@'localhost' IDENTIFIED BY 'P@rk!ng1278';

GRANT SELECT, INSERT, UPDATE, DELETE ON parking_system_db.* TO 'parking_system_db_user'@'localhost';

FLUSH PRIVILEGES;

USE parking_system_db;

CREATE TABLE tb_establishment (
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(200) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255)NOT NULL,
    postal_code VARCHAR(8) NOT NULL,
    state VARCHAR(2) NOT NULL,
    city VARCHAR(255) NOT NULL,
	telephone VARCHAR(11) NOT NULL,
    psf_motorcycles INTEGER NOT NULL,
    psf_cars INTEGER NOT NULL
);

CREATE TABLE tb_vehicle (
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    brand VARCHAR(200) NOT NULL,
    model VARCHAR(200) NOT NULL,
    color VARCHAR(50) NOT NULL,
    plate VARCHAR(7) NOT NULL,
    type ENUM('MOTORCYCLE', 'CAR') NOT NULL
);

CREATE TABLE tb_ticket (
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    establishment_id BIGINT,
    vehicle_id BIGINT,
    checkin DATETIME NOT NULL,
    chekout DATETIME,
    FOREIGN KEY (establishment_id) REFERENCES tb_establishment(id),
    FOREIGN KEY (vehicle_id) REFERENCES tb_vehicle(id)
);
