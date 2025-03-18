FROM amazoncorretto:21.0.5

WORKDIR /app

COPY target/hospital-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8181

ENTRYPOINT ["java", "-jar", "app.jar"]
