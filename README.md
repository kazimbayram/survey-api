Survey API
============

This is a Survey application powered by Spring Framework with JPA that provides the main functions that create single question survey, collect scores with the range of 1-10 and feedback as text. Also provice NPS calculation for each topic


## Features
- Topic Creation
- Collect Reaction between 1 to 10
- NPS Calculation
- Swagget API Doc (TODO Endpoint Definitions and Descriptions)
- Configuration with Environment Variables

## Setup

1. Install Java 11 or above

2. Clone this repo to your desktop and run `./mvnw install` to install all the dependencies.

The project uses H2 as database. If you want to use another one, please add database driver dependencies to `pom.xml`.

## Environment Variables

By default, the application uses in-memory h2 database. ıf you want to use another one, please set environment variables below.

| Variable          | Description                                                       |
|-------------------|-------------------------------------------------------------------|
| CONNECTION_STRING | database connection string (eg.  `jdbc:h2:mem:groove`)            |
| DRIVER            | database connection string (eg.  `org.h2.Driver`)                 |
| USERNAME          | database username                                                 |
| PASSWORD          | database password                                                 |
| DIALECT           | hibernate dialect to use (eg.  `org.hibernate.dialect.H2Dialect`) |

You might want to look into `/src/main/resources/application.properties` to make change the database config you want to use.

## Run

1. Run application with `./mvnw spring-boot:run`


## Package

**For Docker:** run `docker build -t survey-api .`

**For to build as jar** `./mvnw clean install`. The jar will be located in `./target/`

## API Docs

While running the application, visit the [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to access API Docs.