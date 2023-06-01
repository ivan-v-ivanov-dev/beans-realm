This repository contains 2 branches: **main** and **gradle-bootrun-configuration** - check them both

**This branch is under development (need to implement Spring Security). Please build the other gradle-bootrun-configuration branch**

In this **main** branch:

# Beans Realm 

In this repopository i have designed a sample shareplace (Spring Web application) for Beans (non-realistic representation of software programs). Each person can upload and download Beans. The application has publicly available pages and restricted ones only for registered users.

# Brief overview

### Public access pages    
Index home page available on http://localhost:8080.   

**Home** page displays counters for the total number of beans, total Beans downloads, total registered users and total comments. There are also 3 sections with Beans - **Latest**, **Most downloaded** and **Top Rated Beans**. 

![image](https://user-images.githubusercontent.com/51414119/224305946-0626bbef-f012-4854-8b76-f3ebdb7c1675.png)

**Single Bean** page contains detailed information for the current Bean. This page has 3 sections: 
  - **Details** - diplaying the detailed information for the Bean;
  - **Versions** - displaying the version which the Bean has. They can be downloaded through the DOWNLOAD button. 
  - **Reviews (Comments)** - displaying the comments comments for the Beans leeft by other users. Only posting a comment requires authentication (Submit button will redirect you to Keycloak's login page).

![image](https://user-images.githubusercontent.com/51414119/224306147-291fc983-d3ea-4e0b-b3d9-603e431cec11.png)

**Filter Beans** page you can filter Beans according to certain criteria.

![image](https://user-images.githubusercontent.com/51414119/224306367-ea86b45c-2d53-4eb0-843c-b1be0aeca5af.png)

### Restricted access pages     

**Upload Bean** page is restricted for only for **registered users** and **admin**.    

**Approve New Beans** and **View not approved versions** are restricted only for **admin**. 

Admin credentials        
username: **admin**      
password: **admin**         

Registered user credentials        
username: **ivan**     
password: **ivan**      

![image](https://user-images.githubusercontent.com/51414119/224306606-ee4d9af0-66e7-46e8-aae8-a291649ba6ac.png)

# Technical overview

## Start the application

Just clone (**git clone https://github.com/ivanovbiol/beans-realm.git**) the repository and Gradle **bootJar** the project with **docker** profile in order to generate the **.jar** file for the compiled code. After that execute **docker-compose up** command in the root folder to start all Docker containers - Dockerized Spring Boot application, PostgreSQL, PostgreSQL adminer, Liquibase, MongoDB and Redis (see **docker-compose.yml** and **Dockerfile** in root folder). Sample data will be imported automatically in PostgreSQL via Liquibase. In MongoDB sample data will be imported automatically via CommentRepository (see **config/MongoConfig.java** file).  

 - Note: In order to start the application you need to have Docker Desktop installed on your machine.
 
 ![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/0463e627-eb59-4794-ad39-a5c197b90a51)

## Databases

### Primary database 

**PostgreSQL** (official postgres Docker image). 
 - DB: **storage**.

![image](https://user-images.githubusercontent.com/51414119/222958723-a6de941c-e042-46f1-9961-808390f59bfc.png)

I chose the database design to have relation from **versions** table to **beans** because the binary content of the bean will be stored in each version. If we had the opposite relation (from **beans** to **versions**) we would load List of versions for each Bean.     

### Secondary database

**MongoDB** (official mongo Docker image). Only the comments for the Beans are stored in MongoDB.

 - DB: **storage**.
 - Collection: **comments**.

![image](https://user-images.githubusercontent.com/51414119/222959348-8a955f40-a71c-4a89-b06f-0cf8d50b1669.png)

## Backend 

 - **Spring boot** used to create stand-alone application. 
 - **Gradle** used as a build tool. 
 - **Spring MVC framework** used to create the controllers and connect to the frontend.
 - **Thymeleaf** used as a template engine to create and populate the html pages.
 - **Spring Data JPA** with native and JPQL queries adopted as reposity layer for both PostgreSQL and MongoDB (see **repository/postgres** and **repository/mongo** packages).
 - **PostgreSQL** (official postgres Docker image) used a primary database.
 - **Liquibase** (official liquibase Docker image) creates the tables and imports sample data in PostgreSQL DB on project starup (see **resources/db/chaneLog/changeLog.xml**).
 - **Mongo DB** (official mongo Docker image). Sample data are imported using the CommentRepository on project start up (see **MongoConfig.java**)
 - **Redis** cache (official Redis Docker image) was adopted for user experience optimisation when retrieving resources (see **service/RedisCacheServiceImpl.java**). 
 - **Crone expression** deletes regularly the cache every 3 minutes (time selected on perpose to demonstrate the action of deleting while testing the application - see **service/RedisCacheServiceImpl.java**).  
 - **Slf4j** logs performed actions (also instantiating the configurations). Sample snapshot when loading the index page (http://localhost:8080)
 ![image](https://user-images.githubusercontent.com/51414119/222961799-7b921287-d4d5-4710-aba7-10af316951a1.png)
 
 - All tools along with the Dockerized Spring Boot app (**beans** container) are instantiated as Docker containers after **docker-compose up**. (The liquibase container starts, imports the data and after that shuts down - check the logs of the container)
 
![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/2fd3eab7-956e-4fd5-83fd-6e15ac3d87f4)

## Frontend

 - **Paid bootstrap theme** used as base HTML page source

# Disclaimer   

This is personal coding skills training project. NO real software product are distrubuted and/or uploaded in the application. The term "Beans" serves a an non-realistic, non-profitable representation of dummy products. Official Docker images are used for the technologies. Paid bootstrap theme was used for the frontend view (for styled HTML sources). Sample images obtained from https://pixabay.com/bg/.
