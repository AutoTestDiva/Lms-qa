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

```sql
INSERT INTO account ()
Germany,admin@mail.com,$2a$10$ZRBVssy0mqXLL1Wv80LaROywrAWdW1mo0.8IAfKrxiswmcBlE1bai,Jhon,Admin,true,+490577777777,ADMIN,CONFIRMED,
Germany,teacher@mail.com,$2a$10$lIVNxb6bl2Uas/bOz6LdAetYzzSfTRIFx4iJS9fR5u1XqkLujcApS,Teacher,Testteach,true,+401871234567,TEACHER,CONFIRMED,
Germany,student2@mail.com,$2a$10$humjffaz25vyY1zmB2FwuONfXnIuN7AKyTJ4nhBAzXG7WfMeLyc9i,Student,Test,true,+490571234567,STUDENT,CONFIRMED,41
Germany,student@mail.com,$2a$10$9QI9ukaxgJTEYMTeuezGKundlfK9pc9WiHmNyQQvxmxaaNyF9y4ca,Student,Test,true,+490571234567,STUDENT,CONFIRMED,39
```

## Run Rest Assured test.
The first you have do "git pull" of [Back-End](https://github.com/ait-tr/lms-be) project.
Then setting it with local variables. And run in localhost:8080.
All back-end documentation in [swagger](http://localhost:8080/swagger-ui/index.html#/).

## Run UI tests
The first you have do "git pull" of [Front_end](https://github.com/ait-tr/lms-fe) project.
To start frontend you have to enter "ng serve" in terminal.
It will start in http://localhost:4200/.

