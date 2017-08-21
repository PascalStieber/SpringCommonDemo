Microservices blueprint
===================

---


# About SpringCommonDemo
SpringCommonDemo is a Spring &amp; Netflix OSS Stack based blueprint for further application development. SpringCommonDemo should be the basis for applications that are implemented in a microservice architecture style. The challenge is to configure the technologies homogeneously to get rid of the disadvantages in a microservice architecture.

Technologies
-------------
This blueprint uses the following technologies:

> - Docker
> - **Netflix**
>	- Turbine
>	- Zuul
>	- Eureka
>	- Hystrix
> - **Spring Boot**
>	- Spring Cloud Config
>	- Spring Cloud Sleuth
>	- Thymeleaf
> - OAuth2
> - Zipkin
> - MongoDB
> - Kafka
> - Zookeeper

	

It has three small clients that act as a demo application as well.

> - Eventclient: Sends events throug Spring Cloud Stream
> - Democlient: Uses Spring Thymeleaf as frontend for some CRUD functionalities via mongodb
> - Client: This is the overall blueprint client.