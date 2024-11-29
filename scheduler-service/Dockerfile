FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} scheduler.jar
ENTRYPOINT ["java","-jar","/scheduler.jar"]