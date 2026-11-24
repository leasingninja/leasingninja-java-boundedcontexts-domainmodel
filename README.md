# leasingninja-java-boundedcontexts-domainmodel

The LeasingNinja in Java with DDD style bounded contexts and domain model. UI in Spring WebMVC.

If you haven't visited leasingninja.io yet, should do so now, as its the introduction to the further text.

In Java we implement entities and value objects as classes.
We start with the central entity _Contract_.

## Build Instructions

```fish
./mvnw test
```

## Run with Docker

Start the web app:

```fish
docker-compose up
```

Open <http://localhost:8080> in your browser.

## Run without Docker

Install LeasingNinja to your Maven repository:

```fish
./mvnw install
```

Start the web app:

```fish
./mvnw -pl leasingninja-webapp spring-boot:run -Dspring-boot.run.jvmArguments=-enableassertions
```

You might want to see more logging:

```fish
./mvnw -pl leasingninja-webapp spring-boot:run -Dspring-boot.run.jvmArguments=-enableassertions -Dspring-boot.run.arguments=--logging.level.io.leasingninja=TRACE
```

Open <http://localhost:8080> in your browser.
