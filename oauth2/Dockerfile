FROM openjdk:9-jre-slim
LABEL maintainer="pascal.stieber.ps@googlemail.com"

ADD target/*.jar executable_service.jar
CMD /usr/bin/java -Xmx600m -Xms600m -jar executable_service.jar