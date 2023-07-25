FROM openjdk:17-alpine3.14
ADD . /src
WORKDIR /src
COPY build/libs/nihongo-1.0-SNAPSHOT.jar ./
CMD ["java", "-jar", "nihongo-1.0-SNAPSHOT.jar"]
