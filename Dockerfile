# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY .mvn/ .mvn/
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

COPY --from=builder /app/target/eventosapp.jar eventosapp.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "eventosapp.jar"]