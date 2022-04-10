FROM gradle:7.4.1-jdk11 as builder
COPY . /usr/src/java-code/
WORKDIR /usr/src/java-code
RUN gradle bootJar

FROM openjdk:11-jdk-alpine
COPY --from=builder "/usr/src/java-code/build/libs/*.jar" "./app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]