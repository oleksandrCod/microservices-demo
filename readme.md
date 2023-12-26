# Spring Boot Microservices

## Introduction

I created this application in order to familiarize myself with the microservice architecture. In the process of writing
the code, I got acquainted with many features of the operation of microservice applications. I learned typical flow of
microservice architecture, in my application I use Eureka discovery server, Keycloak, Spring Cloud gateway, Resilience4j
circuit
breaker, event driven architecture with Kafka, and also I add a distribute tracing with Zipkin, Prometheus, Loki, Tempo
and observe it with Grafana. During this project I also got to know about the Google JIB plugin which help to build code
images with best practices and pull it to dockerHub. After all I dockerized all my project and it helps me to improve my
knowledge about docker. I want also to add k8s but for now I think it out of scope.
# Technology's
Spring Boot, Spring Cloud, Spring Cloud Gateway, Spring Cloud Netflix Eureka, Spring Cloud Circuit Breaker, Spring Web,
Spring Data JPA, Lombok, Mapstruct, MySql, MongoDB, Docker, Google JIB, Keycloak, Grafana, Prometheus, Loki, Tempo.
# Diagram

![Untitled Diagram.jpg](Untitled%20Diagram.jpg)

## How to run the application using Docker

1. Run 'mvn clean package' in terminal to build the applications.
2. Run docker engine on your machine.
3. Run 'docker login'.
4. Run 'docker compose up -d' to start the applications it will take some time to up and run all services in docker.

## How to use the application

You can observe all services using [eureka](http://localhost:8761/eureka) server. After you need to use Postman to send
http request to app. Specify authorization use OAuth 2.0 because application use opentID connection.
Paste Access Token URL : "http://localhost:8080/realms/spring-boot-demo-realm/protocol/openid-connect/token", and
clientID:
"spring-cloud-client". After go to [keycloak admin console](http://localhost:8080/) user admin for login and admin for
password. In clients chose "spring-cloud-client" and open credentials generate the secret and paste it to ClientSecret
field in
postman. After push button 'Get New Access Toke' and use generated token.
Now you can sent request to api-gateway with specific endpoint. User POST:http://localhost8181/api/order to place the
order,
specify this request with raw body "{
"orderItemsDto": [
{
"skuCode": "iphone_13",
"price": 1200,
"quantity": 1
}
]
}". Also you can use get request to achieve all products GET:http://localhost:8181/api/product. 
I know it looks very simple but my Java quest only begin)