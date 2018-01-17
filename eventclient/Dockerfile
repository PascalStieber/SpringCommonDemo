FROM openjdk:9-jre-slim
LABEL maintainer="pascal.stieber.ps@googlemail.com"

ARG JAR_FILE
ADD target/$JAR_FILE executable_service.jar
CMD /usr/bin/java -Xmx600m -Xms600m -jar executable_service.jar