# FROM openjdk:17
FROM bellsoft/liberica-openjdk-alpine:17.0.2
ARG JAR_FILE=target/*.jar
MAINTAINER William
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
