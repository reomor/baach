FROM openjdk:8-jdk-alpine

RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.5.4
ARG USER_HOME_DIR="/root"
ENV PROJECT_DIR="/root/project"
ENV TASK_DIR="/root/project/threesh"

RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
| tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

RUN mkdir -p $TASK_DIR
WORKDIR $TASK_DIR
COPY ./threesh/spring-application/target/spring-application-0.0.1-SNAPSHOT.jar $TASK_DIR

RUN cp $TASK_DIR/spring-application-0.0.1-SNAPSHOT.jar $PROJECT_DIR/app.jar

CMD /usr/bin/java -Dserver.port=$PORT $JAVA_OPTS -jar /root/project/app.jar
