FROM openjdk:12-ea-20-alpine
MAINTAINER Say.li <120011676@qq.com>
LABEL maintainer="Say.li <120011676@qq.com>"
ENV TZ Asia/Shanghai
RUN apk --update add tzdata && ln -sf /usr/share/zoneinfo/${TZ} /etc/localtime && echo ${TZ} > /etc/timezone
WORKDIR /opt
ENV PROJECT_JAR_NAME agent-server-0.0.1-SNAPSHOT.jar
COPY build/libs/$PROJECT_JAR_NAME .
EXPOSE 8080
CMD java -jar $PROJECT_JAR_NAME