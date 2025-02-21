FROM amazoncorretto:22

WORKDIR /app

COPY . /app

# Gerekli paketleri yükle
RUN yum install -y tar gzip

# Maven Wrapper'a çalıştırma izni ver
RUN chmod +x ./mvnw

# Maven build işlemini çalıştır (JAR dosyasının /app/target içinde oluşmasını garanti eder)
RUN ./mvnw clean package

# Doğru dizinden JAR dosyasını kopyala
ARG JAR_FILE=/app/target/*.jar

COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
