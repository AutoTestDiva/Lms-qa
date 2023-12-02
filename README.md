# LMS QA Project

Before start to work with LMS QA Project, you have to create src/test/resources/application.yml file.
Then set your access to database in application.yml
You have to enter your username from database in the "username" field and enter password in "password" field.
### Example:
```
url: jdbc:mysql://localhost:3306/lms
username: root 
password: Password123
```

## Run Rest Assured test.
The first you have do "git pull" of [Back-End](https://github.com/ait-tr/lms-be) project.
Then setting it with local variables. And run in localhost:8080.
All back-end documentation in [swagger](http://localhost:8080/swagger-ui/index.html#/).

## Run UI tests
The first you have do "git pull" of [Front_end](https://github.com/ait-tr/lms-fe) project.
To start frontend you have to enter "ng serve" in terminal.
It will start in http://localhost:4200/.

