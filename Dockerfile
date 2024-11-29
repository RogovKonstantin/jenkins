FROM openjdk:17-jdk-slim
WORKDIR /jenkinslab
COPY target/app-for-jenkins-0.0.1-SNAPSHOT.jar jenkins-app.jar
ENTRYPOINT ["java", "-jar", "jenkins-app.jar"]
