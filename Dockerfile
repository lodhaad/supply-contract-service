# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

EXPOSE 82

# The application's jar file
ARG JAR_FILE=target/supply-contract-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} supply-contract-service.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/supply-contract-service.jar"]