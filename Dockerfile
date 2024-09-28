FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive
ENV JAVA_VERSION=21

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    ca-certificates \
    openjdk-21-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY ./target/backend-0.0.1-SNAPSHOT-spring-boot.jar /app/backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/backend.jar"]
