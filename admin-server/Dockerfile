FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/admin-server.jar
ENTRYPOINT ["java", "-jar", "/app/admin-server.jar"]