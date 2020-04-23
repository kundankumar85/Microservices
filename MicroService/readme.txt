This is parent project which is having

1. AppConfigServer : This is configuration server which will read data from git.
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties

where application is injected as the spring.app.name in the SpringApplication (what is normally application in a regular Spring Boot app), profile is an active profile (or comma-separated list of properties), and label is an optional git label (defaults to master.)


2. Api Gateway : Zuul (Gateway to redirect request it is having pre,post,route,error filters also) port :2224
3. RegistrationServer : Eureca other option is consul, port 1111
4. CustomerGateway : Dicovery Client , internally calling CustomerService using Loadbalanced RestTemplate (defalult load balensor is ribbon).port :2222

It reads configuration for dev profile (bootstrap.properties active profile is dev spring.app.name ) from git through AppConfigServer.
5.CustomerService : rest discovery client providing services. port :2223


Example:http://192.168.0.103:2224/customer-gateway/findAllCustomers

Api - getway will redirect it to customer-gateway/findAllCustomers method.

http://192.168.0.103:2224/customer-service/findall

Api - getway will redirect it to customer-service/findall method.


Note: More details

https://howtodoinjava.com/spring/spring-cloud/spring-cloud-api-gateway-zuul/
https://cloud.spring.io/spring-cloud-netflix/multi/multi__router_and_filter_zuul.html (Filters)


zuul:
  ignoredServices: '*'
  routes:
    users:
      path: /customer/**    #any request is matching this pattern then It will get forwarded to customer-service
      serviceId: customer-service
    customer-gateway:       #any request which  having customer-gateway will get forwarded to serviceId mentioned sevice name.
      #path: /gateway/**
      serviceId: customer-gateway
      #url: http://192.168.0.103:2223 #Its working redirect using url
      #zuul.host.socket-timeout-millis=30000



#Url for microservice board
http://localhost:1111/

#Feign Client
Customer Gateway will call Customer Service using feign client.


#Swagger
Customer-Gateway
http://localhost:2222/swagger-ui.html
http://localhost:2224/customer-gateway/swagger-ui.html

#config server
Property urls for config-server
http://localhost:3333/customer-gateway/dev

# Actuator
Url to refresh properties
http://localhost:2224/customer-gateway/actuator/refresh
