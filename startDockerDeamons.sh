#!/bin/sh
docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube 
docker run -d -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts 
docker run -d --name mongoclient -p 3000:3000 mongoclient/mongoclient 
docker run -d --name nexus -p 8081:8081  sonatype/nexus 
