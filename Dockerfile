# Use an existing docker image as a base
FROM openjdk:8-jdk-alpine

# as in the pom.xml finalName tag
ARG JAR_FILE=target/dev.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java" , "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar" ]