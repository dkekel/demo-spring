FROM openjdk:11-jre-slim

COPY ./build/libs /app

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java","-jar","demospring-0.0.1-SNAPSHOT.jar"]
