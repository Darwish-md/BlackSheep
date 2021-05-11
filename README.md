# BlackSheep Web Application

- BlackSheep is a web-based ecommerce application which has a user-friendly interface and a great UX.
- The website is now deployed and can be accessed here: http://blacksheepecommerce.herokuapp.com
- The project is a Spring Boot application built using Maven. You can build a war file and run it locally from the command line:
```
git clone https://github.com/Darwish-md/BlackSheep.git
cd BlackSheep
mvn package 
mvn spring-boot:run
```
## Database configuration ##
- The project is configured to work with PostgreSQL Database. You can find the default username and password for database connection in `application.properties` file where you can change it according to your local host:
```
spring.datasource.url = jdbc:postgresql://localhost:5432/blacksheep
spring.datasource.driverClassName = org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=postgres
```
- After setting the configurations, you need then to execute the `database.sql` file to insert the records into the local database server. you can find the file here in the  repository.
- You can then access BlackSheep locally on: http://localhost:8080/ 

![home-page](https://user-images.githubusercontent.com/72353586/117778313-59b26e80-b1f2-11eb-8a78-8621d21f7f47.PNG)

## In case you find a bug or suggested improvement for BlackSheep ##
- You can report any issues or suggest improvements through here: https://github.com/Darwish-md/BlackSheep/issues



