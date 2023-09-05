FROM maven:3.8.4-eclipse-temurin-17 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17
COPY --from=build /app/target/websocketChat-0.0.1-SNAPSHOT.jar /java/websocketChat-0.0.1-SNAPSHOT.jar
EXPOSE 8080:8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/java/websocketChat-0.0.1-SNAPSHOT.jar"]
