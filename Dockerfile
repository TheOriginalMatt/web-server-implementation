# syntax=docker/dockerfile:1

FROM maven:3.8.4-eclipse-temurin-11 as build



WORKDIR /server

COPY pom.xml ./pom.xml
COPY src/ ./src/

RUN mvn clean compile

CMD ["mvn", "exec:java"]