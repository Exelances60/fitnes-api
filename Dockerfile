FROM amazoncorretto:22

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
