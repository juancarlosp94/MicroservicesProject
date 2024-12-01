# Microservices Project
## Microservices project using Spring Boot and Spring Cloud
> [!IMPORTANT]
> This project follows the video course on: [Spring Boot Microservice Project Full Course in 6 Hours](https://www.youtube.com/watch?v=mPPhcU7oWDU&t=5704s). Documentation, comments and notes will be done manually by me. The intention is to improve documentation and English skills.

> [!CAUTION]
> This project uses an outdated version of SpringBoot cloud. Some changes will be made under the feature-version-fix branch to update to SpringBoot 3. Video guide : [Spring Boot 3 Microservices with Kubernetes and Angular Complete Course in 7 Hours](https://www.youtube.com/watch?v=yn_stY3HCr8). I will leave the documentation from previous version in order to identify what changes were made

The purpose of this project is to review the topic of microservices and improve the use of `Spring` tools. We will develop different services and each of them will have synchronous and asynchronous communication.
The following are the services we will be working on:
- Product service
- Order service
- Inventory service
- Notification service
- Order service, Inventory service and notification service are going to interact with each other

### Solution Architecture Diagram:
Sprinboot 2

![Diagrama Microservicios](https://github.com/juancarlosp94/MicroservicesProject/assets/70818906/7c564dc5-1075-4587-9a66-1a2c6ecf5a42)

SpringBoot 3

![MicoservicesProjectVersion2 drawio](https://github.com/user-attachments/assets/c7a7a54c-c5d2-4b96-a9a4-6d79a1c198cc)


### Logic Architecture of each service
![Diagrama Servicios](https://github.com/juancarlosp94/MicroservicesProject/assets/70818906/37d56374-6073-475e-8342-0d32ffa68e5e)

>[!NOTE]
>Controller: Receives clients requests (protocol:`Http`) <br>
>Service: Business logic execution and communication with message Queue <br>
>Repository: Data storage (`MongoDB`, `MySQL`)

Communication could be either Synchronous or Asynchronous

![Synchronous communication](https://github.com/user-attachments/assets/494cf5d8-d700-4c2c-8882-36313b152401)

Order service will make a request to check product status in Inventory service. Then Inventory service will respond whether the product is in stock or not. Based on the response, Order service will either place the order successfully or throw an exception or an error that the product is out of stock to the cutomer.

>[!NOTE]
>To make an integration test of such interaction between order service and inventory service we will use Wiremock which is a better option instead of Mockito. This ensures that http requests are tested and mocked. Another advantage of using this approach is that testing connection to another service API increases the cost each time the availability of the service is checked.

Synchronous communication is usually done through `Http` clients. For this demo we are using `Webclient` from `SpringBoot`

>[!CAUTION]
>After Java 10+ `stream().toList()` method, convert an object to an immutable. The class OrderService tries to map OrderLineItems entities and transform them to a DTO. This causes UnsupportedOperationException: null - ImmutableCollections error on the `clear()` method. So for versions above, the  stream method should be changed to `collect(Collectors.toList()` in line 34 on placeOrder method.

The project is set to run locally. But microservices environments usually run on cloud servers. We might have multiple instances of the Inventory service and each instance can have different dynamic IP addresses, as we can see in the following diagram:

![CloudInstances](https://github.com/user-attachments/assets/fe72f60c-2be4-487c-b012-7b0d255545a8)

How can Order service know which instance to call? To solve this we have Service Discovery Pattern.

![ServiceDiscoveryPattern drawio](https://github.com/user-attachments/assets/a920e64c-0c96-4bac-9c99-9e1cfadc1620)

The Discovery Server will store information about server instances, their names and IP addresses. Whenever a request is made by a service to the Discovery Server its IP is registered in the registry as an entry.

![DiscoveryServiceCommunication](https://github.com/user-attachments/assets/63789793-23ca-48ba-8c43-624b43c9b452)

The Discovery Server will provide the correct IP address for each request. We avoid hardcoding `URL` of the inventory service. If for some reason Discovery Server is not avaiable, client will store a copy of registries.

![DiscoveryServiceCommunication2 drawio](https://github.com/user-attachments/assets/ef9078b0-ba77-4fae-95f7-42f28693f591)






