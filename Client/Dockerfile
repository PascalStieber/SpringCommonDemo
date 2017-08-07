#official supported jdk version
#FROM openjdk:8-jdk
FROM ewolff/docker-java
ADD target/client-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx600m -Xms600m -jar client-0.0.1-SNAPSHOT.jar
EXPOSE 8080