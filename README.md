[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxNzcuMjE4NzgwNTE3NTc4MTIiIGhlaWdodD0iMzUiIHZpZXdCb3g9IjAgMCAxNzcuMjE4NzgwNTE3NTc4MTIgMzUiPjxyZWN0IHdpZHRoPSI1MC42NTYyNTc2MjkzOTQ1MyIgaGVpZ2h0PSIzNSIgZmlsbD0iIzdlZDMyMSIvPjxyZWN0IHg9IjUwLjY1NjI1NzYyOTM5NDUzIiB3aWR0aD0iMTI2LjU2MjUyMjg4ODE4MzYiIGhlaWdodD0iMzUiIGZpbGw9IiM0MTc1MDUiLz48dGV4dCB4PSIyNS4zMjgxMjg4MTQ2OTcyNjYiIHk9IjIxLjUiIGZvbnQtc2l6ZT0iMTIiIGZvbnQtZmFtaWx5PSInUm9ib3RvJywgc2Fucy1zZXJpZiIgZmlsbD0iI0ZGRkZGRiIgdGV4dC1hbmNob3I9Im1pZGRsZSIgbGV0dGVyLXNwYWNpbmc9IjIiPlVTRTwvdGV4dD48dGV4dCB4PSIxMTMuOTM3NTE5MDczNDg2MzMiIHk9IjIxLjUiIGZvbnQtc2l6ZT0iMTIiIGZvbnQtZmFtaWx5PSInTW9udHNlcnJhdCcsIHNhbnMtc2VyaWYiIGZpbGw9IiNGRkZGRkYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIGZvbnQtd2VpZ2h0PSI5MDAiIGxldHRlci1zcGFjaW5nPSIyIj5TUFJJTkctQk9PVDwvdGV4dD48L3N2Zz4=)](https://forthebadge.com)
[![forthebadge](data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMzEuMjUwMDExNDQ0MDkxOCIgaGVpZ2h0PSIzNSIgdmlld0JveD0iMCAwIDEzMS4yNTAwMTE0NDQwOTE4IDM1Ij48cmVjdCB3aWR0aD0iNTguNjI1MDAzODE0Njk3MjY2IiBoZWlnaHQ9IjM1IiBmaWxsPSIjM2U2ZTkzIi8+PHJlY3QgeD0iNTguNjI1MDAzODE0Njk3MjY2IiB3aWR0aD0iNzIuNjI1MDA3NjI5Mzk0NTMiIGhlaWdodD0iMzUiIGZpbGw9IiNmMjkyMjEiLz48dGV4dCB4PSIyOS4zMTI1MDE5MDczNDg2MzMiIHk9IjIxLjUiIGZvbnQtc2l6ZT0iMTIiIGZvbnQtZmFtaWx5PSInUm9ib3RvJywgc2Fucy1zZXJpZiIgZmlsbD0iI0ZGRkZGRiIgdGV4dC1hbmNob3I9Im1pZGRsZSIgbGV0dGVyLXNwYWNpbmc9IjIiPldJVEg8L3RleHQ+PHRleHQgeD0iOTQuOTM3NTA3NjI5Mzk0NTMiIHk9IjIxLjUiIGZvbnQtc2l6ZT0iMTIiIGZvbnQtZmFtaWx5PSInTW9udHNlcnJhdCcsIHNhbnMtc2VyaWYiIGZpbGw9IiNGRkZGRkYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIGZvbnQtd2VpZ2h0PSI5MDAiIGxldHRlci1zcGFjaW5nPSIyIj5NWVNRTDwvdGV4dD48L3N2Zz4=)](https://forthebadge.com)

# Chatop application backend

This the Chatop Rental Management System's backend, its REST API manages the autentification and exposes the user's rentals and messages.

## Technologies
- Java 17
- Spring-Boot 3.3.2
- Mysql 8.4.2

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
- Install MySQL server and create a dedicated user whith all right to a dedicated DB for the application.

#### Launch the REST API Server
Download the last release JAR:
TODO: Last released Jar link HERE

Then run with the command:
```Bash
java -jar  api-0.0.1-SNAPSHOT.jar
```


