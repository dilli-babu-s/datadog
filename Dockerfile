FROM amazoncorretto:8-alpine-jre

WORKDIR /app/data-dog

RUN apk add curl

RUN curl -Lo dd-java-agent.jar https://dtdg.co/latest-java-tracer

COPY target/*.jar DataDog.jar

ENTRYPOINT ["java" , "-javaagent:dd-java-agent.jar", "-Ddd.trace.sample.rate=1", "-jar" , "DataDog.jar"]