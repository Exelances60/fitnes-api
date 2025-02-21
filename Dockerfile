FROM amazoncorretto:22

WORKDIR /app

COPY . /app

# Gerekli paketleri yükle
RUN yum install -y tar gzip

# Maven Wrapper'a çalıştırma izni ver
RUN chmod +x ./mvnw

# Maven ile projeyi derle
RUN ./mvnw clean package

# Eğer .original dosyası varsa onu kullan
ARG JAR_FILE=/app/target/fitnes-api-0.0.1-SNAPSHOT.jar.original

COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
