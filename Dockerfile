FROM openjdk:17-alpine

ADD build/libs/emailservice-0.0.1-SNAPSHOT.jar /app/emailservice-0.0.1.jar

ENTRYPOINT ["java", "-jar", "app/emailservice-0.0.1.jar"]