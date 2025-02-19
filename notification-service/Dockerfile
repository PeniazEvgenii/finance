FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notification.jar
ENTRYPOINT ["java","-jar","/notification.jar"]