# CrowdStreet-Backend Interview Project

### Run & Build Instructions:
1. Open terminal/console
1. Clone the project 
   ```git clone https://github.com/bgs473/crowdstreet-backend```
1. Change directory into the project 
   ```cd crowdstreet-backend```
1. Initialize Gradle
   ```gradle init```
1. Build the project 
   ```gradle build ```
1. Run the project 
   ```gradlew run```

###H2 Database
1. To access the in memory H2 Database, go to the following url:
   http://localhost:8080/h2-console
1. Use the following configuration
    - Driver Class: org.h2.Driver
    - JDBC URL: jdbc:h2:mem:db
    - User Name: sa
    - Password: sa
1. You are logged in.