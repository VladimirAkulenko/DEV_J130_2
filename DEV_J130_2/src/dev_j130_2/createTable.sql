CREATE TABLE products (
vender CHAR (7) PRIMARY KEY, 
name VARCHAR(50) NOT null, 
color VARCHAR(20) ,
price INT CHECK (price >0),
stock_balance INT CHECK (stock_balance >=0)
);

INSERT INTO products (vender, name, color, price, stock_balance)
VALUES
('3251615', 'Стол кухонный', 'белый', 8000,12),
('3251616', 'Стол кухонный', NULL, 8000, 15),
('3251617', 'Стул столовый "гусарский"', 'орех',4000,0),
('3251619','Стул столовый с высокой спинкой','белый',3500,37),
('3251620','Стул столовый с высокой спинкой','коричневый',3500,52);


CREATE TABLE orders (
identifier INT PRIMARY KEY,
date_creation DATE NOT NULL, 
name_customer VARCHAR(100) NOT NULL,
contact_telephon VARCHAR(50),
email VARCHAR(50),
delivery_address VARCHAR(200),
order_status CHAR (1),
order_shipment_date DATE,
CHECK (order_status ='P' OR order_status = 'S' OR order_status = 'C'), 
CHECK (order_status = 'S' AND order_shipment_date IS NOT NULL OR order_status IN ('P', 'C') AND order_shipment_date IS NULL) 
);

INSERT INTO orders (identifier, date_creation, name_customer, contact_telephon, email, delivery_address, order_status, order_shipment_date)
VALUES
(1, '20.11.2020', 'Сергей Иванов', '(981)123-45-67', '', 'ул. Веденеева, 20-1-41', 'S', '29.11.2020');
INSERT INTO positions 
VALUES 
(1, '3251616', 7500, 1);
 
INSERT INTO orders (identifier, date_creation, name_customer, contact_telephon, email, delivery_address, order_status, order_shipment_date)
VALUES
(2, '22.11.2020','Алексей Комаров', '(921)001-22-33', '', 'пр. Пархоменко 51-2-123', 'S', '29.11.2020');
INSERT INTO positions 
VALUES 
(2, '3251615', 7500, 1);

INSERT INTO orders (identifier, date_creation, name_customer, contact_telephon, email, delivery_address, order_status, order_shipment_date)
VALUES
(3, '28.11.2020', 'Ирина Викторова', '(911)009-88-77', '', 'Тихорецкий пр. 21-21', 'P', NULL);
INSERT INTO positions 
VALUES
(3, '3251615', 8000, 1), (3, '3251617', 4000, 4);

INSERT INTO orders (identifier, date_creation, name_customer, contact_telephon, email, delivery_address, order_status, order_shipment_date)
VALUES
(4, '03.12.2020', 'Павел Николаев', '', 'pasha_nick@mail.ru', 'ул. Хлопина 3-88', 'P', NULL);
INSERT INTO positions 
VALUES
(4, '3251619', 3500, 2);

INSERT INTO orders (identifier, date_creation, name_customer, contact_telephon, email, delivery_address, order_status, order_shipment_date)
VALUES
(5, '03.12.2020', 'Антонина Васильева', '(931)777-66-55', 'antvas66@gmail.com', 'пр. Науки, 11-3-9', 'P',NULL);
INSERT INTO positions 
VALUES
(5, '3251615', 8000, 1),
(5, '3251617', 4000, 4);

INSERT INTO orders (identifier, date_creation, name_customer, contact_telephon, email, delivery_address, order_status, order_shipment_date)
VALUES
(6, '10.12.2020', 'Ирина Викторова', '(911)009-88-77', '', 'Тихорецкий пр. 21-21', 'P',NULL);
INSERT INTO positions 
VALUES
(6, '3251617', 4000, 2);


CREATE TABLE positions (
orders_identifier INT NOT NULL,
vender CHAR (7) NOT NULL, 
price INT NOT NULL CHECK (price >0),
amount INT NOT NULL CHECK( amount >0),
FOREIGN KEY (vender) REFERENCES products (vender),
FOREIGN KEY (orders_identifier) REFERENCES orders (identifier),
PRIMARY KEY (orders_identifier, vender) 
);