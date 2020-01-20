FROM adoptopenjdk:13.0.1_9-jdk-hotspot

WORKDIR /app

COPY ./build/libs /app

EXPOSE 8888

CMD ["java", "-jar", "project-wayne-1.0.0.jar"]