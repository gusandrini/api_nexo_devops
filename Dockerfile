FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-jre-alpine
RUN adduser -h /home/gustavo -s /bin/sh -D gustavo
WORKDIR /home/gustavo
COPY --from=builder /app/target/*.jar app.jar
RUN chown gustavo:gustavo app.jar
USER gustavo
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]