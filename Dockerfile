FROM openjdk:10
VOLUME /tmp
ADD backend/target/backend-0.0.1-SNAPSHOT-exec.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=$PORT,suspend=n"
EXPOSE 8080 8787 $PORT
CMD java $JAVA_OPTS $JAVA_TOOL_OPTIONS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar