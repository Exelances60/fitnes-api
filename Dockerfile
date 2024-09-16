FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/fitnes-api-0.0.1-SNAPSHOT.jar ./fitnes-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "aws-deployment.jar"]