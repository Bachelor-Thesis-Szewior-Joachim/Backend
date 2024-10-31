FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive
ENV JAVA_VERSION=21

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    ca-certificates \
    openjdk-21-jdk \
    docker.io && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN ln -s /usr/bin/docker /usr/local/bin/docker

WORKDIR /app

COPY ./target/backend-0.0.1-SNAPSHOT-spring-boot.jar /backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/backend.jar"]
