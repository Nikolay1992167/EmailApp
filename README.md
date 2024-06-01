# Email Service

A service that sends messages to the user's email according to the type.

### Technologies that I used on the project:

* Java 17
* Gradle 8.1.1
* Lombok plugin 8.4
* Spring-boot 3.2.3
* Spring-boot-starter-web
* Spring-boot-starter-validation
* Spring-boot-starter-mail
* Spring-boot-starter-amqp
* Spring-boot-starter-test

### Instruction to run application:

1. You must have [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html),
   [Intellij IDEA Ultimate](https://www.jetbrains.com/idea/download/).
2. You should set your data in lines 3-6 in [application.yaml](emailservice/src/main/resources/application.yaml).
3. Run [EmailServiceApplication.java](emailservice/src/main/java/com/solbeg/emailservice/EmailServiceApplication.java) only after run
UserService.
4. This service starts and works correctly in the docker container according to docker-compose.yaml in the directory above.

### Unit tests

1. Tests have been written with 94% coverage of code.
2. Integration tests are not written.
3. You can run the tests for this project, by at the root of the project executing:

```
./gradlew test
```