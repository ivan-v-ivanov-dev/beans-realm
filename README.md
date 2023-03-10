# Beans Realm

In this repopository i have designed a sample shareplace (Spring Web application) for Beans (non-realistic representation of software programs). Each person can upload and download Beans. The application has publicly available pages and restricted ones only for registered users. (still the project is **under development**)

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

**Approve New Beans** is restricted only for **admin**. If you click on the pages it will redirect you to Keycloak's login page.      

Admin credentials      

username: **admin**    
password: **admin**       

Registered user credentials      

username: **ivan**   
password: **ivan**    

![image](https://user-images.githubusercontent.com/51414119/222964073-a9163a98-752b-41a0-8ae0-bc5c3f37749d.png)    

![image](https://user-images.githubusercontent.com/51414119/224306606-ee4d9af0-66e7-46e8-aae8-a291649ba6ac.png)

# Technical overview

## Start the application

Just clone (**git clone https://github.com/ivanovbiol/beans-realm.git**) the repository and Gradle **bootRun** the project in IntelliJ IDEA. All needed components (PostgreSQL, PostgreSQL adminer, MongoDB, Keycloak and Redis) are instantiated on project starup in Docker containers (see **docker-compose.yml** in root folder). Sample data will be imported automatically in PostgreSQL via Liquibase. In MongoDB sample data will be imported automatically via CommentRepository (see **config/MongoConfig** file).  

 - Note: In order to start the application you need to have Docker Desktop installed on your machine.
 
 ![image](https://user-images.githubusercontent.com/51414119/222958784-e4a9d2fb-c715-4080-b2a4-b4f00a99da67.png)

## Databases

### Primary database 

**PostgreSQL** (official postgres Docker image). 
 - DB: **storage**.

![image](https://user-images.githubusercontent.com/51414119/222958723-a6de941c-e042-46f1-9961-808390f59bfc.png)

I chose the database design to have relation from **versions** table to **beans** because the binary content of the bean will be stored in each version. If we had the opposite relation (from **beans** to **versions**) we would load List of versions for each Bean.     

**Adminer** (offical adminer Docker image) used as database management tool to access and manipulate the PostgreSQL database through the browser.

Adminer GUI login credentials:  

URL: http://localhost:8082  
System:  PostgreSQL  
Server: postgres:5432  
Username: root  
Password: root  
Database: storage  
 
![image](https://user-images.githubusercontent.com/51414119/222959114-8a0845c7-6448-43da-9ebe-67d47f97b413.png)

![image](https://user-images.githubusercontent.com/51414119/222959150-74f838a1-f20a-482a-a672-f734377aca35.png)

### Secondary database

**MongoDB** (official mongo Docker image). Only the comments for the Beans are stored in MongoDB.

 - DB: **storage**.
 - Collection: **comments**.

![image](https://user-images.githubusercontent.com/51414119/222959348-8a955f40-a71c-4a89-b06f-0cf8d50b1669.png)

## Backend 

 - **Spring boot** used to create stand-alone application. 
 - **Gradle** used as a build tool. 
 - **Spring MVC framework** used to create the controllers and connec to the frontend.
 - **Thymeleaf** used as a template engine to create and populate the html pages.
 - **Keycloak** (as jboss/keycloak Docker image) used for authentication. The embedded H2 database in the jboss/keycloak's image in replaced with PostgreSQL database while Keycloak's Docker container is being instantiated on project startup using a bash script (both project's and keycloak's databases are created using the bash script). Keycloak's realm settings are imported via Docker volume using JSON configuration file (see **keycloak-realm-setting.json** in project root and **docker-compose.yml**). Project's MVC endpoints secured with custom role for registered users **authorised-user** (credentials mentioned above). 
 
 Keycloak administation console credentials:  
 URL: http://localhost:8081/auth/   
 username: admin    
 password: admin 
 
 ![image](https://user-images.githubusercontent.com/51414119/222963817-745bb1fa-5f56-47a3-b08a-a5033ba7c3e3.png)
 
 - **Spring Data JPA** with native and JPQL queries adopted as reposity layer for both PostgreSQL and MongoDB (see **repository/postgres** and **repository/mongo** packages).
 - **Liquibase** creates the tables and imports sample data in PostgreSQL DB on project starup (see **resources/db/chaneLog/changeLog.xml**).
 - Sample data in Mongo DB are imported using the CommentRepository on (see **MongoConfig.java**)
 - **Redis** cache (official redis Docker image) was adopted for user experience optimisation when retrieving resources (see **service/RedisCacheServiceImpl.java**). 
 - **Crone expression** deletes regularly the cache every 3 minutes (time selected on perpose to demonstrate the action of deleting while testing the application - see **service/RedisCacheServiceImpl.java**).  
 - **Slf4j** logs performed actions (also instantiating the configurations). Sample snapshot when loading the index page (http://localhost:8080)
 ![image](https://user-images.githubusercontent.com/51414119/222961799-7b921287-d4d5-4710-aba7-10af316951a1.png)
 
 - All tools instantiated as Docker containers on project startup.
 
 ![image](https://user-images.githubusercontent.com/51414119/222963580-f13f6549-1d10-4432-ab84-c3f9a93b012b.png)

## Frontend

 - **Paid bootstrap theme** used as base HTML page source

# Disclaimer   

This is personal coding skills training project. NO real software product are distrubuted and/or uploaded in the application. The term "Beans" serves a an non-realistic, non-profitable representation of dummy products. Official Docker images are used for the technologies. Paid bootstrap theme was used for the frontend view (for styled HTML sources). Sample images obtained from https://pixabay.com/bg/.
