### Базовый docker image
ARG BASE_IMAGE=""
FROM $BASE_IMAGE


### Создание директории для конфигураций микросервиса
#USER root
#RUN mkdir -p /home/fmbs/config \
#  /home/fmbs/src/main/resources/api

#RUN adduser -d /home/fmbs -s /sbin/nologin fmbs \
# && chown -R fmbs:fmbs /home/fmbs

#USER fmbs

### Добавление jar микросервиса
#WORKDIR /home/fmbs
#
#COPY /build/libs/*.jar app.jar
#COPY src/main/resources/api ./src/main/resources/api

### Команда запуска приложения
CMD java $JAVA_OPTS -jar app.jar -Dspring.config.location=./config/
