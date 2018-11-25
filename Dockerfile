FROM openjdk:8-jdk-alpine

ARG USER_HOME_DIR="/root"
ENV PROJECT_DIR="/root/project"
ENV TASK_DIR="/root/project/threesh"

RUN mkdir -p $TASK_DIR
WORKDIR $TASK_DIR
COPY ./threesh/spring-application/target/spring-application-0.0.1-SNAPSHOT.jar $TASK_DIR

RUN cp $TASK_DIR/spring-application-0.0.1-SNAPSHOT.jar $PROJECT_DIR/app.jar

CMD /usr/bin/java -Dserver.port=$PORT $JAVA_OPTS -jar /root/project/app.jar
