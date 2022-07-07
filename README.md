# leasingninja-java-boundedcontexts-domainmodel

The LeasingNinja in Java with DDD style bounded contexts and domain model. UI in Spring WebMVC.

If you haven't visited leasingninja.io yet, should do so now, as its the introduction to the further text.

In Java we implement entities and value objects as classes.
We start with the central entity _Contract_.

## Build Instructions

For the time being we’re wrestling with the module system, so we have to play some tricks. It is either:

* build with Maven without tests:

    ```fish
    ./mvnw package -Dmaven.test.skip=true
    ```

* build with Maven without Jigsaw

    ```fish
    bin/enable-jigsaw.sh disable && ./mvnw test && bin/enable-jigsaw.sh enable
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
bin/enable-jigsaw.sh disable && ./mvnw install && bin/enable-jigsaw.sh enable
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
