# LogStorage
App with panel for analyze,take and saved logs

___

### Stack:
- Java
- Spring
- PostgreSQL
- Maven 


![Текст с описанием картинки](/images/Screenshot_17.png)
![Текст с описанием картинки](/images/img.png)
![Текст с описанием картинки](/images/img_1.png)

___

### Start
- 1 Configure PostgreSQL access at **application.properties**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=admin

#drop n create table again, good for testing, comment this in production
spring.jpa.hibernate.ddl-auto=update
```
- 2  Run app with maven