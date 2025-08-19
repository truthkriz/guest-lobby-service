# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests clean package

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/guest-lobby-1.0.0.jar app.jar
# Render memberi env PORT; kita hormati itu
CMD ["sh","-c","java -jar app.jar --server.port=${PORT}"]
