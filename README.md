# Spring Boot JPA Console Application

## Description

This is a console application built with **Spring Boot** and **JPA** that allows you to perform **CRUD** operations and execute advanced queries on a person database. It supports custom queries, aggregations, and filtering, providing a comprehensive solution for working with relational data using Spring Boot and JPA.

## Setup

To get started with this project, you need to configure the following environment variables for the database connection and Spring Boot settings:

### Required Environment Variables

```properties
spring.application.name=springboot-jpa
spring.output.ansi.enabled=always

spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

### Variables to Configure

- **DB_HOST**: The host address of your MySQL database (e.g., `localhost`).
- **DB_PORT**: The port on which your MySQL server is running (default is `3306`).
- **DB_NAME**: The name of your database.
- **DB_USERNAME**: The username to connect to the MySQL database.
- **DB_PASSWORD**: The password to connect to the MySQL database.

### Database Setup

Before running the application, ensure that the database exists. Although Spring Boot with Hibernate can automatically create and update tables based on the entities, the database itself must be created manually if it does not already exist.

To create the database, follow these steps:

1. Log into your MySQL server:
   - You can use a MySQL client or any database management tool such as MySQL Workbench or phpMyAdmin.

2. Create the database with the following SQL command:

   ```sql
   CREATE DATABASE your_database_name;
    ```
Replace your_database_name with the value you have set for DB_NAME in the environment variables.

Once the database is created and the environment variables are configured, Spring Boot will be able to connect to the database and perform CRUD operations, execute advanced queries, and handle other database manipulations as required by the project.

With spring.jpa.hibernate.ddl-auto=update, Spring Boot will automatically manage the creation and updates of tables based on your JPA entities.


