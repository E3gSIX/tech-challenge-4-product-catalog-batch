FROM eclipse-temurin:17-jdk-alpine


ENV MONGODB_URI=mongodb://localhost:27017
ENV MONGODB_DATABASE=products_db
ENV MONGODB_USERNAME=root
ENV MONGODB_PASSWORD=secret

WORKDIR /app
COPY target/tech-challenge-4-clientes-0.0.1-SNAPSHOT.jar tech-challenge-4-clientes-0.0.1-SNAPSHOT.jar
EXPOSE 9080
CMD ["java", "-jar", "tech-challenge-4-clientes-0.0.1-SNAPSHOT.jar"]