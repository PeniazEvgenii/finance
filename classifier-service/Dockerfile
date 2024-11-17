FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} classifier.jar
ENTRYPOINT ["java","-jar","/classifier.jar"]