# java-app

This is the first application. It is written in Java with SpringBoot.

## Prerequisites
- java 21, JDK
- maven
- docker

## Building

maven build
```shell
mvn clean install
```

docker build:
```shell
docker build -t java-app .
```

Run the container
```shell
docker run -d -p 8080:8080 java-app
```

For running without docker, you can run it with:
```shell
java -jar ./target/java-app-1.0-SNAPSHOT.jar
```