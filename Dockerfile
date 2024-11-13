# Stage 1: Build the application
FROM amazoncorretto:21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive
ENV JAVA_VERSION=21

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    ca-certificates \
    openjdk-21-jdk \
    docker.io \
    openssl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN ln -s /usr/bin/docker /usr/local/bin/docker

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Generate RSA key pair
RUN openssl genrsa -out keypair.pem 2048

# Extract the public key
RUN openssl rsa -in keypair.pem -pubout -out publicKey.pem

# Convert the private key to PKCS#8 format
RUN openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out privateKey.pem

# Expose port 8443
EXPOSE 8080

# Run the application with SSL settings and the corrected password
ENTRYPOINT ["java", "-jar", "app.jar", \
    "--server.port=8080", \
    "--spring.security.oauth2.resourceserver.jwt.public-key-location=classpath:publicKey.pem", \
    "--spring.security.oauth2.resourceserver.jwt.private-key-location=classpath:privateKey.pem"]