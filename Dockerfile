FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
WORKDIR /app
COPY build/libs/hrmanament-0.0.1-SNAPSHOT.jar  .
ENTRYPOINT [ "java", "-jar", "hrmanament-0.0.1-SNAPSHOT.jar" ]