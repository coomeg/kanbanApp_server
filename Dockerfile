# use alpine as base image
FROM openjdk:jdk-alpine
# FROM  openjdk:13-jdk-alpine
# recommended by spring boot
VOLUME /tmp
# create directory for application
RUN mkdir /app
WORKDIR /app
# add user for application
RUN adduser -S sboot
USER sboot
# copy springboot jarfile
COPY build/libs /app
# set entrypoint to execute spring boot application
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/build/libs/gs-spring-boot-docker-0.1.0.jar" ]