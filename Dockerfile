# Build stage
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run stage
# We use 'eclipse-temurin' because it is the official successor to 'openjdk'
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
