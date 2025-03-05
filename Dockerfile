FROM openjdk:17-jdk-alpine
MAINTAINER  OmarPrietoMendez
COPY target/pruebavivelibre-0.0.1-SNAPSHOT.jar pruebavivelibre-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pruebavivelibre-0.0.1-SNAPSHOT.jar"]