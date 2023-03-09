FROM khipu/openjdk17-alpine
COPY ./target/room-service-0.0.1-SNAPSHOT.jar room-service-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","room-service-0.0.1-SNAPSHOT.jar"]