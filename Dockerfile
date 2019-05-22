FROM openjdk

WORKDIR /app

COPY . /app

EXPOSE 8888

CMD ["java", "-jar", "build/libs/project-wayne-1.0.0.jar"]