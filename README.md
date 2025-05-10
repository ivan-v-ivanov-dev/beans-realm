This repository contains 2 branches: **main** and **gradle-bootrun-configuration** - check them both. 

In this **main** branch:

# Beans Realm 

In this repository, I have designed a sample Shareplace (Spring Web application) for Beans (a non-realistic representation of software programs). Each person can upload, download and comment on Beans. The uploaded new Beans and their versions must be approved (revised) by admin before being publicly accessible. Only admin users can approve new beans and block or unblock registered users.   

# Brief overview

### Login    
Application runs on http://localhost:8080.   

All pages are restricted either for **users** and **admins** or for **admins** only. Depending on the logged-in user (**user** or **admin**), different menus will be accessible or visible in the navigation bar. You will land on the Spring Security sign-in page initially.

**Credentials**

Admin     

username: **admin**      
password: **admin**         

User       

username: **user1**     
password: **user1**   

### Pages  

**Home** page displays counters for the total number of beans, total Beans downloads, total registered users and total comments (restricted for **user** and **admin**). There are also 3 sections with Beans - **Latest**, **Most downloaded** and **Top Rated Beans**. 

Admin login example:  

![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/4f746d9b-e484-4f86-a749-1f5d432f28d0)

User login example:  

![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/75a0f2c5-a107-4ff7-9401-0bbe865d1963)


**Single Bean** page contains detailed information for the current Bean (restricted for **user** and **admin**). This page has 3 sections: 
  - **Details** - diplaying the detailed information for the Bean;
  - **Versions** - displaying the version which the Bean has. They can be downloaded through the DOWNLOAD button. 
  - **Reviews (Comments)** - displaying the comments comments for the Beans leeft by other users. Only posting a comment requires authentication (Submit button will redirect you to Keycloak's login page).

![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/78e620bf-93cd-4810-ac35-ffecb3e2dad6)

**Filter Beans** page you can filter Beans according to certain criteria (restricted for **user** and **admin**).

![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/cfe041bc-37d1-4ed0-9cc5-8630490a68b8)

**Upload Bean** page is restricted for only for **user** and **admin**.    

![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/4b0208ef-e2ba-4fea-bb3d-4f601be0126b)

**Approve New Beans** and **Disable users** are restricted only for **admin**. 

![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/bb518e32-975e-4e30-866e-7b4103349a0c)

# Technical overview

## Start the application

Just clone (git clone https://github.com/ivanovbiol/beans-realm.git) the repository and Gradle **bootJar** the project with **docker** profile in order to generate the **.jar** file for the compiled code. After that, execute the **docker-compose up** command in the root folder to start all Docker containers: Dockerized Spring Boot application, PostgreSQL, Liquibase, MongoDB, and Redis (see **docker-compose.yml** and **Dockerfile** in the root folder). Sample data will be imported automatically into PostgreSQL via Liquibase container. In MongoDB, sample data will be imported automatically via CommentRepository (see the **config/MongoConfig.java** file).

 - Note: In order to start the application you need to have Docker Desktop installed on your machine.
 
 ![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/0463e627-eb59-4794-ad39-a5c197b90a51)

## Databases

### Primary database 

**PostgreSQL** (official postgres Docker image). 
 - DB: **storage**.

![image](https://user-images.githubusercontent.com/51414119/222958723-a6de941c-e042-46f1-9961-808390f59bfc.png)

I chose the database design to have relation from **versions** table to **beans** because the binary content of the bean will be stored in each version. If we had the opposite relation (from **beans** to **versions**) we would load List of versions for each Bean. (The picture is with the old DB design - the **roles** table and **password** and **role_id** columns for the **users** are not demostrated. It's just that my IntelliJ ultimate license expired - sorry abouth that.)    

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
 - **Spring Security** used as authentication and access-control (BCrypt Password Encoder endoded user credentials stored in database - see **SecurityConfig.java**).
 - **Spring Data JPA** with native and JPQL queries adopted as reposity layer for both PostgreSQL and MongoDB (see **repository/postgres** and **repository/mongo** packages).
 - **PostgreSQL** (official postgres Docker image) used a primary database.
 - **Liquibase** (official liquibase Docker image) creates the tables and imports sample data in PostgreSQL DB on project starup (see **resources/db/chaneLog/changeLog.xml**).
 - **Mongo DB** (official mongo Docker image). Sample data are imported using the CommentRepository on project start up (see **MongoConfig.java**)
 - **Redis** cache (official Redis Docker image) was adopted for user experience optimisation when retrieving resources (see **service/RedisCacheServiceImpl.java**). 
 - **Crone expression** deletes regularly the cache every 3 minutes (time selected on perpose to demonstrate the action of deleting while testing the application - see **service/RedisCacheServiceImpl.java**).  
 - **Slf4j** logs performed actions (also instantiating the configurations). Sample snapshot when loading the index page (http://localhost:8080)
 ![image](https://user-images.githubusercontent.com/51414119/222961799-7b921287-d4d5-4710-aba7-10af316951a1.png)
 
 - All tools along with the Dockerized Spring Boot app (**beans** container) are instantiated as Docker containers after **docker-compose up**. (The liquibase container starts, imports the data and after that shuts down - check the logs of the container. If by any chance the container can't connect with the database, just restart it and it will import the data.)
 
![image](https://github.com/ivanovbiol/beans-realm/assets/51414119/2fd3eab7-956e-4fd5-83fd-6e15ac3d87f4)

## Frontend

 - **Paid bootstrap theme** used as base HTML page source

# Disclaimer   

This is personal coding skills training project. NO real software product are distrubuted and/or uploaded in the application. The term "Beans" serves a an non-realistic, non-profitable representation of dummy products. Official Docker images are used for the technologies. Paid bootstrap theme was used for the frontend view (for styled HTML sources). Sample images obtained from https://pixabay.com/bg/.
