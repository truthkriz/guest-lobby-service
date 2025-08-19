# Simple Dockerfile for the guest-lobby service
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ENV PORT=34172
EXPOSE 34172
COPY target/guest-lobby-1.0.0.jar /app/guest-lobby.jar
ENTRYPOINT ["java","-jar","/app/guest-lobby.jar"]