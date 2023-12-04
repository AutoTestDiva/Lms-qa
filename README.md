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

The next step you have to run sql file to add a cohorts ``src/test/resources/sql/Cohort.sql``
to create main users as admin, teacher, student and student2. You have to run sql file:
``src/test/resources/sql/adminTeacherStudents.sql``

Create new user with ADMIN Password -> Hash (admin@mail.com; Admin123!) - $2a$10$ZRBVssy0mqXLL1Wv80LaROywrAWdW1mo0.8IAfKrxiswmcBlE1bai
Create Teacher {"teacher@mail.com", "Qwer123!"}
Create Student {"student@mail.com", "Qwer123!"} - cohort 34.2
Create Student {"student2@mail.com", "Qwer123!"} - cohort 35, cohort 36

## Run Rest Assured test.
The first you have do "git pull" of [Back-End](https://github.com/ait-tr/lms-be) project.
Then set local variables. Where you see [info] you have to put there your data.

```
base.url=http://localhost:8080;
DATABASE_HOST=127.0.0.1;
DATABASE_NAME=lms;
DATABASE_PORT=3306; 
DATABASE_USERNAME= [here must be your name to conect to database] ;
DATABASE_PASSWORD= [here must be your password];
MAIL_USERNAME=johndoe.aittr@gmail.com;
MAIL_PASSWORD=jjxvtlblshoswhzr;
ZOOM_ACCOUNT_ID= [your zoom accaunt id] ;
ZOOM_CLIENT_ID= [your zoom client id];
ZOOM_CLIENT_SECRET= [ZOOM_CLIENT_SECRET]
```

And run in localhost:8080.
All back-end documentation in [swagger](http://localhost:8080/swagger-ui/index.html#/).

## Run UI tests
The first you have do "git pull" of [Front_end](https://github.com/ait-tr/lms-fe) project.
To start frontend you have to enter "ng serve" in terminal.
It will start in http://localhost:4200/.

