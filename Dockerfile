# syntax=docker/dockerfile:1
FROM eclipse-temurin:18

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY leasingninja-sales ./leasingninja-sales
COPY leasingninja-riskmanagement ./leasingninja-riskmanagement
COPY leasingninja-webapp ./leasingninja-webapp

RUN ./mvnw install -Dmaven.test.skip=true

CMD ["./mvnw", "-pl", \
               "leasingninja-webapp", \
               "spring-boot:run", \
               "-Dspring-boot.run.jvmArguments=-enableassertions" \
               "-Dspring-boot.run.arguments=--logging.level.io.leasingninja=TRACE"]
