# Use amazoncorretto as the base image
FROM amazoncorretto:21-alpine

# Install required tools
RUN apk add --no-cache maven openssl

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Copy the target directory to ensure the built JAR is included
COPY target ./target

# Generate RSA key pair
RUN openssl genrsa -out keypair.pem 2048

# Extract the public key
RUN openssl rsa -in keypair.pem -pubout -out publicKey.pem

# Convert the private key to PKCS#8 format
RUN openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out privateKey.pem

# Expose port 8080
EXPOSE 8080

# Run the application with SSL settings and the corrected password
ENTRYPOINT ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar", \
    "--server.port=8080", \
    "--spring.security.oauth2.resourceserver.jwt.public-key-location=classpath:publicKey.pem", \
    "--spring.security.oauth2.resourceserver.jwt.private-key-location=classpath:privateKey.pem"]
