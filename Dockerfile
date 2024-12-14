FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/api-0.0.1-SNAPSHOT.jar /app/api.jar

CMD ["java", "-jar", "api.jar"]
