# Microservices Project
## Microservices project using Spring Boot and Spring Cloud
> [!IMPORTANT]
> This project follows the video course on: [Spring Boot Microservice Project Full Course in 6 Hours](https://www.youtube.com/watch?v=mPPhcU7oWDU&t=5704s). Documentation, comments and notes will be done manually by me. The intention is to improve documentation and English skills.



The purpose of this project is to review the topic of microservices and improve the use of `Spring` tools. We will develop different services and each of them will have synchronous and asynchronous communication.
The following are the services we will be working on:
- Product service
- Order service
- Inventory service
- Notification service
- Order service, Inventory service and notification service are going to interact with each other

### Solution Architecture Diagram:
![Diagrama Microservicios](https://github.com/juancarlosp94/MicroservicesProject/assets/70818906/7c564dc5-1075-4587-9a66-1a2c6ecf5a42)

### Logic Architecture of each service
![Diagrama Servicios](https://github.com/juancarlosp94/MicroservicesProject/assets/70818906/37d56374-6073-475e-8342-0d32ffa68e5e)

>[!NOTE]
>Controller: Receives clients requests (protocol:`Http`) <br>
>Service: Business logic execution and communication with message Queue <br>
>Repository: Data storage (`MongoDB`, `MySQL`)

Communication could be either Synchronous or Asynchronous

![Synchronous communication](https://github.com/user-attachments/assets/494cf5d8-d700-4c2c-8882-36313b152401)

Order service will make a request to check product status in Inventory service. Then Inventory service will respond whether the product is in stock or not. Based on the response, Order service will either place the order successfully or throw an exception or an error that the product is out of stock to the cutomer.

Synchronous communication is usually done through `Http` clients. For this demo we are using `Webclient` from `SpringBoot`


