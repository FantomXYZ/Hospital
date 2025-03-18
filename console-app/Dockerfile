FROM amazoncorretto:21.0.5

WORKDIR /app

COPY target/console-app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8282

ENTRYPOINT ["java", "-jar", "app.jar"]
