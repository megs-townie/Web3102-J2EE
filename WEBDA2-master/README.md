# OMS: WEBD3102 Assignment
## Introduction
OMS is an order management system where the user can browse products and use 1 click order to purchase the product.

## Features
- 1 click purchase
- Write & see reviews
- Register an account
- Login to purchase product

## Technologies Used
OMS was written primarily in J2EE using servlets. MySQL was used for a database connection to store information on the backend. HTML and CSS were used on the frontend with some JavaScript for client side logic.

## Getting Started
To get started with OMS, use git clone to clone this repo into your local

```https://github.com/mardeyar/WEBDA2.git```

Some database objects will need to be created. The naming convention is as follows:<br>
- DB named **OMS**
- Tables named **users, products, product_reviews**

Use the following SQL queries to get started

```sql
CREATE DATABASE IF NOT EXISTS oms;

USE oms;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    province VARCHAR(24) NOT NULL,
    postal_code VARCHAR(7) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    product_id INT(10) AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_category VARCHAR(255) NOT NULL,
    product_info VARCHAR(255) NOT NULL,
    quantity_in_stock INT(10) NOT NULL,
    product_image VARCHAR(255) NOT NULL
);

CREATE TABLE product_reviews (
    review_id INT(11) AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    review_info VARCHAR(255) NOT NULL,
    rating INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO products (product_name, product_category, product_info, quantity_in_stock, product_image)
VALUES
('Bug Spray', 'Pest control', 'Fend off bugs with this great bug spray, it works great!', 23, 'product_imgs/bugspray.png'),
('Soda', 'Food and drink', 'Stay cool with a cold glass of soda.', 41, 'product_imgs/soda.png'),
('Cold and Flu meds', 'Medicine', 'Feeling sick? Try some cold & flu meds to get you back up.', 28, 'product_imgs/medicine.png'),
('Milk', 'Food and drink', 'A great source of calcium, drink milk.', 61, 'product_imgs/milk.png'),
('Juice', 'Food and drink', 'Part of a balanced breakfast, start your day with juice.', 18, 'product_imgs/juice.png'),
('Greek Yogurt', 'Food and drink', 'A rich source of protein and chalk full of vitamins.', 78, 'product_imgs/yogurt.png'),
('Ice Coffee', 'Food and drink', 'What better way to jumpstart your day with a jolt of energy.', 35, 'product_imgs/icecoffee.png'),
('Lawn Fertilizer', 'Gardening', 'Keep that lawn looking greener than a traffic light.', 30, 'product_imgs/lawncare.png'),
('Mega office pack', 'Office', 'Never lose a pencil again with this mega pack.', 105, 'product_imgs/office.png'),
('Headphones', 'Electronics', 'Introducing the loudest headphones ever, louder than a jet engine.', 8, 'product_imgs/headphones.png'),
('Laser T650N', 'Electronics', 'Print docs with ease quickly and right.', 58, 'product_imgs/printer.png'),
('AllBrite Cleaner', 'Cleaning', 'No germs can survive the power of AllBrite.', 33, 'product_imgs/cleaner.png'),
('Ultimate Vehicle Care Kit', 'Automotive', 'Keep your vehicle looker newer than a 1999 Dodge Neon.', 9, 'product_imgs/automotive.png'),
('Silver Hills Bread', 'Food and drink', 'This breads packs a days worth of fibre in 2 slices.', 45, 'product_imgs/bread.png'),
('Scanbot 3000', 'Electronics', 'Scan your photos and documents at the press of a button.', 134, 'product_imgs/scanner.png'),
('Intel i7 Chip', 'Electronics', 'This chip boosts your PC speed up to 4x.', 6, 'product_imgs/intelchip.png'),
('Simple Green Max', 'Automotive', 'Simple Green Max is great for the environment and tough on dirt.', 26, 'product_imgs/carcleaner.png');
```

## Requirements
- [XAMPP Control Panel](https://www.apachefriends.org/download.html)
- IDE Software: I used [IntelliJ](https://www.jetbrains.com/idea/download/?section=windows)

## License

This project is licensed under the [MIT License](LICENSE) - see the [LICENSE](LICENSE) file for details.
