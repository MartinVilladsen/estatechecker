FROM maven:3.9.11-eclipse-temurin-25 AS build
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
