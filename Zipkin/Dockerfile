FROM ewolff/docker-java
ADD target/zipkin-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx600m -Xms600m -jar zipkin-0.0.1-SNAPSHOT.jar
EXPOSE 9411