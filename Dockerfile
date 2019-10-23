FROM maven:3.6.1-jdk-8
VOLUME /tmp
ARG JAR_FILE
ARG SPRING_ENV
ARG EUREKA_URL
ARG DB_URL 
ARG DB_USER
ARG DB_PASSWORD
ENV spring_profiles_active=$SPRING_ENV
ENV EUREKA_URL=$EUREKA_URL
ENV DB_URL=$DB_URL
ENV DB_USER=$DB_USER
ENV DB_PASSWORD=$DB_PASSWORD
EXPOSE 9002
COPY target/${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
