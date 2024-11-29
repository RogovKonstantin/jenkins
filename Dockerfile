FROM openjdk:17-jdk-slim
WORKDIR /jenkinslabapp
COPY target/jenkinslabapp-0.0.1-SNAPSHOT.jar jenkinslabapp.jar
ENTRYPOINT ["java", "-jar", "jenkinslabapp.jar"]