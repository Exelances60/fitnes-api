FROM amazoncorretto:22

WORKDIR /app

COPY . /app

# Gerekli paketleri yükle (tar ve gzip)
RUN yum install -y tar gzip

# Maven Wrapper'ı çalıştırılabilir yap
RUN chmod +x ./mvnw

# Maven ile projeyi derle
RUN ./mvnw clean package

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
