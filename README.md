[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

# Chatop application backend

This is the Châtop Rental Management System's backend, its REST API manages the USER authentication and exposes the user's rentals and messages.

## Technologies
- Java 17
- Spring-Boot 3.3.2
- Mysql 8.4.2

## Authors

@pozasl


## Licensing

&copy; Châtop


## API Documentation
- TODO: OpenAPI3 documentation link HERE

## Building

A Devcontainer environment is provided with a JDK container and a MySQL 8.4.2 container

Otherwise you may need:
- JDK 17
- A MySQL Server 8.4 with a dedicated user and DB for the application

Clone this repository then launch the command

To test in local run:
```Bash
./mwnw spring-boot:run
```
The API is exposed on port 8080

To build the final artefact run:

```Bash
./mwnw clean install
```
The Jar file wil be produced in the "target" fodler.


## Deployment

### Prequisites
- JRE 17 Higher
- A MySQL Server 8.4

### Installation

#### MySQL
- Install MySQL server and create a dedicated user whith all right to a dedicated database for the application.

#### Launch the REST API Server
Download the last released JAR:
[api-0.0.2](https://github.com/pozasl/chatop-api/releases/download/0.0.2/api-0.0.2.jar)

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
java -jar -Dspring.profiles.active=prod api-0.0.2.jar
```
The backend server will be listening on port 8080

The API's documentation is available at http://localhost:8080/swagger-ui/index.html
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
