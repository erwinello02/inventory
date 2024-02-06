# syntax=docker/dockerfile:1

# Use the official OpenJDK 8 image as a base image
FROM openjdk:8-slim

# Set the working directory inside the container
WORKDIR /inventory-app

# Copy the application project
COPY . /inventory-app

# Replace the windows command 'gradlew' to be able to run on bash
RUN sed -i -e 's/\r$//' gradlew

# Build the application
RUN ./gradlew clean build

# Specify the command to run your application
CMD ["java", "-jar", "build/libs/inventory-0.0.1-SNAPSHOT.jar"]
