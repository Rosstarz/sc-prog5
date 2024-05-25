FROM eclipse-temurin:17.0.10_7-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} prog5.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/prog5.jar"]
