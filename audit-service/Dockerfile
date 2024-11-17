FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} audit.jar
ENTRYPOINT ["java","-jar","/audit.jar"]