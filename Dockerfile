FROM azul/zulu-openjdk-alpine:11.0.2 as packager

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    APP_SLEEP=0 \
    JAVA_OPTS=""

RUN adduser -D -s /bin/sh master
WORKDIR /home/master

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown master:master entrypoint.sh
USER master

ADD target/app-pessoa*.jar app.jar

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

