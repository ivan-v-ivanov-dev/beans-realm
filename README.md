# Beans Realm

In this repopository i have designed a sample shareplace (Spring Web application) for Beans (non-realistic representation of software programs). Each person can upload and download Beans. The application has publicly available pages and restricted ones only for registered users. (still the project is under development)

# Brief overview

### Public access
Application runs on http://localhost:8080. On the main page are displayed counters for the total number of beans, total Beans downloads, total registered users and total comments. There are also 3 sections with Beans - Latest, Most downloaded and Top Rated Beans. If you click on a Bean you will land on a single Bean page with detailed information for the current Bean. This page has 3 sections: 
  - Details - diplaying the detailed information for the Bean;
  - Versions - displaying the version which the Bean has. They can be downloaded through the DOWNLOAD button. 
  - Reviews (Comments) - displaying the comments comments for the Beans leeft by other users. Only posting a comment requires authentication (Submit button will redirect you to Keycloak's login page).

From the Filter Beans page you can filter Beans according to certain criteria.

### Restricted access
The Upload Bean page is restricted for only for registered users. If you click on the page it will redirect you to Keycloak's login page.    

Registered user credentials   

username: **ivan**  
password: **ivan**  

# Technical overview

## Start the application

Just clone the repository and Gradle bootRun the project in IntelliJ IDEA. All needed components (PostgreSQL, PostgreSQL adminer, MongoDB, Keycloak and Redis) are instantiated on project starup in Docker containers (see docker-compose.yml in root folder). Sample data will be imported automatically in PostgreSQL via Liquibase. In MongoDB sample data will be imported automatically via CommandLineRunner (see MongoConfig.java file).  

 - Note: In order to start the application you need to have Docker Desktop installed on your machine.
 
 ![image](https://user-images.githubusercontent.com/51414119/222919495-f7ace0dc-b946-4abb-9069-c38edbbd4e7e.png)

## Backend part

### Databases 

**Primary database:** PostgreSQL (official postgres Docker image). Adminer (offical adminer Docker image) used as database management tool to access and manipulate the PostgreSQL database through the browser.

Adminer GUI login credentials:  

URL: http://localhost:8082  
System:  PostgreSQL  
Server: postgres:5432  
Username: root  
Password: root  
Database: storage  
 
 ![image](https://user-images.githubusercontent.com/51414119/222920142-094f473f-6405-4482-89b9-b7d5c6a93f4c.png)

**Secondary database**: MongoDB (official mongo Docker image). Only the comments for the Beans are stored in MongoDB.  

### Databases Design


