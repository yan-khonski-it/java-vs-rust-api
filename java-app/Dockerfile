# Use a minimal OpenJDK 21 JRE base image.
# This image is smaller and contains only the runtime environment.
FROM openjdk:21-rc-jdk-slim-bookworm

# Set the working directory inside the container
WORKDIR /app

# Copy the pre-built JAR file from your local 'target' directory
# into the container's '/app' directory.
# IMPORTANT: Ensure the JAR file exists in your local 'target' folder before building the Docker image.
# Adjust the JAR name if yours is different from 'sba-1.0-SNAPSHOT.jar'.
COPY target/java-app-1.0-SNAPSHOT.jar java-app.jar

# Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 8080

# Command to run the application when the container starts.
CMD ["java", "-jar", "java-app.jar"]