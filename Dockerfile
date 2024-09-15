# 1. Maven kullanarak projeyi build et
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Uygulamanın JAR dosyasını kopyala ve yeni imajı oluştur
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar application.jar

# 3. Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "application.jar"]
