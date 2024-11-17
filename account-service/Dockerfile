FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} account.jar
ENTRYPOINT ["java","-jar","/account.jar"]