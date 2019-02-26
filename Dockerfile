FROM openjdk:10
VOLUME /tmp
ADD backend/target/backend-0.0.1-SNAPSHOT-exec.jar app.jar
ADD backend/target/classes/application.properties /app/application.properties
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=,suspend=n"
EXPOSE 8080 8787
CMD java $JAVA_OPTS $JAVA_TOOL_OPTIONS -Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/app/application-properties -Dspring.profiles.active=main -jar -Dserver.port=$PORT /app.jar