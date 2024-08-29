[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

# Chatop application backend

This is the Châtop Rental Management System's backend, its REST API manages the USER authentication and exposes the user's rentals and messages.
It also hosts the uploaded rentals pictures

## Technologies
- Java 17
- Spring-Boot 3.3.2
- Mysql 8.4.2

## Authors

@pozasl

## Licensing

&copy; Châtop

## Building

A Devcontainer environment is provided with a JDK container and a MySQL 8.4.2 container

Otherwise you may need to install:
- JDK 17
- A MySQL Server 8.4 with a dedicated user and database (Check the [MySQL](#MySQL) section to setup)
- Generated RSA key pair in src/main/resources/certs (Follow the included README to generate those keys)

Clone this repository, then to compile and run in local type the command:

```Bash
./mwnw spring-boot:run
```
The API is exposed on port 8080 and accessible from http://localhost:8080


To build the final artefact with production settings run:

```Bash
./mwnw -Pprod clean install
```
The Jar file will be produced in the "target" fodler.


## Deployment

### Prequisites
- JRE 17 Higher
- A MySQL Server 8.4

### Installation

#### MySQL
Install MySQL server and create a dedicated user whith all rights to a dedicated database for the application

```Bash
mysql -u root -p
```

```sql
CREATE DATABASE `chatopdb`;
CREATE USER `chatopuser`@`%` IDENTIFIED BY "SECRET_PASSWORD_HERE";
GRANT ALL PRIVILEGES ON `chatopdb`.* TO `chatopuser`@`%`;
FLUSH PRIVILEGES;
```

Download the [sql script](https://raw.githubusercontent.com/pozasl/chatop-api/main/src/main/resources/sql/script.sql)

Then initialize the table in the application database with the application's user

```Bash
mysql -u chatopuser -p
```

```sql
USE `chatopdb`;
source script.sql
```

#### Launch the REST API Server
Download the last released JAR:
[api-1.0.3](https://github.com/pozasl/chatop-api/releases/download/1.0.3/api-1.0.3.jar)

Set the database connection parameters as environment variables according to the mysql server settings and created user credentials

```Bash
MYSQL_SERVER_HOST="mysql_server_hostname_or_ip_here"
MYSQL_SERVER_PORT="mysql_server_port_here"
MYSQL_DATABASE="database_name_here"
MYSQL_USER="database_user_here"
MYSQL_PASSWORD="database_login_here"
```

Create an upload folder where the rental picture will be stored
```Bash
mkdir upload
```

Then run with the command:
```Bash
java -jar api-1.0.3.jar
```
The backend server will be listening on port 8080

## API documentation

The API is documented on [SwaggerHub](https://app.swaggerhub.com/apis/LOICPOZAS/chatop_open-api_definition/v0.0.5)

When the server is running, The API's documentation is available at http://localhost:8080/swagger-ui/index.html

The API description file is available at http://localhost:8080/v3/api-docs

## Front-end proxy configuration

In the frontend, change the proxy.config.json file like this if it's running on the same host :

```json
{
    "/api/*": {
        "target": "http://localhost:8080",
        "secure": false,
        "changeOrigin": true,
        "logLevel": "debug"
    },
    "/upload/*": {
        "target": "http://localhost:8080",
        "secure": false,
        "changeOrigin": true,
        "logLevel": "debug"
    }
}

```
Otherwise replace "localhost" by the hostname, servicename or ip running the backend server.
