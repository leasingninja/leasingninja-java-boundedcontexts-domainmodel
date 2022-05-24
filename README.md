# leasingninja-java-boundedcontexts-domainmodel
The LeasingNinja in Java with DDD style bounded contexts and domain model. UI in Spring WebMVC.

If you haven't visited leasingninja.io yet, should do so now, as its the introduction to the further text.

In Java we implement entities and value objects as classes.
We start with the central entity _Contract_.

## Build Instructions

For the time being we’re wrestling with the module system, so we have to play some tricks. It is either:

* build with Maven without tests:

    ```fish
    mvn package -Dmaven.test.skip=true
    ```

* build with Maven without Jigsaw

    ```fish
    bin/enable-jigsaw.sh disable && mvn test && bin/enable-jigsaw.sh enable
    ```
