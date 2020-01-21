FROM jessechang9527/adoptopenjdk:8-jre-hotspot-bionic

WORKDIR /app

COPY ./build/libs /app

EXPOSE 8888

CMD ["java", "-jar", "project-wayne-1.0.0.jar"]