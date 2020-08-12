FROM openjdk:8-jre-alpine
LABEL maintainer="bongani@utexas.edu"

ENV APP_ROOT /app

RUN mkdir ${APP_ROOT}

WORKDIR ${APP_ROOT}

COPY target/*.jar ${APP_ROOT}/run.jar
COPY config ${APP_ROOT}/config/

ENTRYPOINT ["java", "-jar", "run.jar"]