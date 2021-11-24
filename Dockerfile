FROM openjdk:11 as dropwizard-backend
RUN mkdir -p app
COPY ./target/weather-app-1.0-SNAPSHOT.jar /app
COPY ./src/config/weather-app.yml /app
WORKDIR /app

