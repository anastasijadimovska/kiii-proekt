FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-jdk-slim
EXPOSE 9090
COPY target/lab-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]