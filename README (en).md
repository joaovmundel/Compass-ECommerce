
# E-Commerce System

An E-commerce system for a company that helps manage inventory by accounting for completed sales.

The system includes a shopping cart where customers can add the products they want to buy and see the total amount to be paid before confirming the purchase.

## Features

- Add, edit, and remove products in stock
- Add, edit, and remove products in the shopping cart
- Calculate the total value of items in the cart
- Purchase confirmation
- Post-sale inventory update
- Runtime database connection configuration
- Change the system language on the settings page at runtime

## Default Configuration

```YML
APP_LOCALE=en
MYSQL_DATABASE=ecommerce
MYSQL_HOST=127.0.0.1
MYSQL_PASSWORD=
MYSQL_PORT=3306
MYSQL_USER=root

```


## Requirements

- Access to MySQL 8.0 database.
- Java 17 installed on the machine.



## Running Locally

Download the Jar file


Run the command:

```
  |https://github.com/joao100101/Compass-ECommerce/releases/tag/challenge
```

Open Command Prompt in the folder where the Jar file is located.


Run the command:
```bash
  java -jar CompassEcommerce-1.0-SNAPSHOT-jar-with-dependencies.jar
```
Remember to configure it according to your database to work correctly.

## Build

To create the jar file using Maven:

```bash
  mvn clean compile assembly:single

```

## Stack Used

- Java 17
- MySQL 8.0


## Author
- Developed by [Joao Victor Mundel](https://www.github.com/joao100101).

