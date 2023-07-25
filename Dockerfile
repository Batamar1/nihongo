FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build
FROM openjdk:11-jre-slim
COPY --from=gradleimage /home/gradle/source/build/libs/nihongo-1.0-SNAPSHOT.jar /src/
ADD . /src
WORKDIR /src

ENTRYPOINT ["java", "-jar", "nihongo-1.0-SNAPSHOT.jar"]
