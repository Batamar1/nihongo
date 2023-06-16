FROM adoptopenjdk:latest
ADD . /src
WORKDIR /src
EXPOSE 8080
RUN ./gradlew build
ENTRYPOINT ["java","-jar","build/libs/nihongo-1.0-SNAPSHOT.jar"]
