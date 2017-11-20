#official supported jdk version
#FROM openjdk:8-jdk
FROM ewolff/docker-java
ADD target/customerclient-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx600m -Xms600m -jar customerclient-0.0.1-SNAPSHOT.jar
EXPOSE 8080