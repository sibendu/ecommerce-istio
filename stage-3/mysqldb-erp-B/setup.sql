CREATE DATABASE ecomm_db;

USE ecomm_db;

CREATE TABLE product (id INT NOT NULL AUTO_INCREMENT, code VARCHAR(20), description VARCHAR(200), quantity INT , price DOUBLE(10,2), discount DOUBLE(10,2), available_quantity INT, PRIMARY KEY (id));

INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (1, "B0001", "Sample B0001", 0, 100.25, 0, 10000);
INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (2, "B0002", "Sample B0002", 0, 120.25, 0, 2000);
INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (3, "B0003", "Sample B0003", 0, 150.25, 0, 3000);
INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (4, "B0004", "Sample B0004", 0, 1500.00, 0, 5000);

INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (5, "C0001", "Sample C0001", 0, 10.25, 0, 4000);
INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (6, "C0002", "Sample C0002", 0, 190.25, 0, 7000);
INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (7, "C0003", "Sample C0003", 0, 1790.25, 0, 9000);
INSERT INTO product (id, code, description, quantity , price , discount , available_quantity) VALUES (8, "C0004", "Sample C0004", 0, 1020.25, 0, 11000);

