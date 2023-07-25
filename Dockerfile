FROM adoptopenjdk/openjdk15:jdk-15.0.1_9-alpine
ADD . /src
WORKDIR /src
EXPOSE 8080
RUN gradlew build
ENTRYPOINT ["java","-jar","build/libs/nihongo-1.0-SNAPSHOT.jar"]
