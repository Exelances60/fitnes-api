# 1. Maven kullanarak projeyi build et
FROM openjdk:21-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Uygulamanın JAR dosyasını kopyala ve yeni imajı oluştur
FROM openjdk:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar application.jar

# 3. Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "application.jar"]
