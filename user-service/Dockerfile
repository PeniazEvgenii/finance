FROM eclipse-temurin:21-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user.jar
ENTRYPOINT ["java","-jar","/user.jar"]