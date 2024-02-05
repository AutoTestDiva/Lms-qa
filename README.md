# LMS QA Project

Before start to work with LMS QA Project, you have to create src/test/resources/application.yml file.
Then set your access to database in application.yml
You have to enter your username from database in the "username" field and enter password in "password" field.

## Documentation
[Test plan of project](https://docs.google.com/document/d/115SB79CmmhLYndd9Wf6cEAfDaiN7c6eA8NFb9hjn_I4/edit?usp=drive_link)

Documentation is on google account:
```
Login: project.lms.2023@gmail.com
Password: Qwer123!
```
With this email you can enter in zoom account. There are data for back-end project to set up environments variable.

### Example:
```
url: jdbc:mysql://localhost:3306/lms
username: root 
password: Password123
```

## Run SQL files

1. The next step you have to run sql file to add a cohorts ``src/test/resources/sql/Cohort.sql``
to create main users as admin, teacher, student and student2. 
2. You have to run sql file:
``src/test/resources/sql/adminTeacherStudents.sql``.
3. Then run sql file ``src/test/resources/sql/student_cohort.sql`` following the instruction in this file.

### Data to Login as User.
- ADMIN  ->  {email: "admin@mail.com", password: "Admin123!"} Hash - $2a$10$ZRBVssy0mqXLL1Wv80LaROywrAWdW1mo0.8IAfKrxiswmcBlE1bai
- TEACHER -> {email: "teacher@mail.com", password: "Qwer123!"}
- STUDENT -> {email: "student@mail.com", password: "Qwer123!"} - cohort 34.2
- STUDENT -> {email: "student2@mail.com", password: "Qwer123!"} - cohort 35, cohort 36

[To encoding password](https://bcrypt-generator.com/)

## Run Rest Assured test.
The first you have to do "git pull" of [Back-End](https://github.com/ait-tr/lms-be) project.
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
The first you have to do "git pull" of [Front_end](https://github.com/ait-tr/lms-fe) project.
To start frontend you have to enter "ng serve" in terminal.
It will start in http://localhost:4200/.

## Getting complete information on a Cohort, Module and Lesson
- Open file 'GetGroupModuleLessonData.csv', enter the cohort, module and lesson for which you want 
  to get information about the presence/absence of tabs (Plan, Theory, Homework, Code, Video) and 
  information about the presence/absence of data in each of these tabs.
- Open the 'GroupModuleLessonInfoTests' class in UI-tests.
  The report folder gives the following information options:
1) if the entered cohort, module and lesson exist, it gives information about the presence of tabs 
  in the lesson 
  (if there is no tab, then "0"; 
  if there is a tab and it contains information, then "+";
  if there is a tab and it doesn't contain information, then "-";
  if there is a video tab and it contains video, then the number of attached videos is written,
  if there is a video tab and it doesn't contain video, then "-");
2) if the entered cohort or module or lesson does not exist, it gives information about absence
  of such cohort or module or lesson.
- The result look in folder 'result'.

## Getting complete information on a Cohort and Module
- Open file 'GetGroupAndModuleData.csv', enter the cohort and module for which you want
  to get information in all lessons about the presence/absence of tabs (Plan, Theory, Homework, Code, Video) and
  information about the presence/absence of data in each of these tabs.
- Open the 'GroupAndModuleInfoTests' class in UI-tests.
  The report folder gives the following information options:
1) if the entered cohort and module exist, it gives information about all lessons and the presence 
  of tabs in the lesson
  (if there is no tab, then "0";
  if there is a tab and it contains information, then "+";
  if there is a tab and it doesn't contain information, then "-";
  if there is a video tab and it contains video, then the number of attached videos is written,
   if there is a video tab and it doesn't contain video, then "-");
2) if the entered cohort or module does not exist, it gives information about absence
   of such cohort or module.
- The result look in folder 'result'.

## Getting complete  information on a Cohort 
- Open file 'GetGroupData.csv', enter the cohort for which you want to get information 
  in all modules and lessons about the presence/absence of tabs (Plan, Theory, Homework, Code, Video) and
  information about the presence/absence of data in each of these tabs.
- Open the 'GroupInfoUITests' class in UI-tests.
  The report folder gives the following information options:
1) if the entered cohort exist, it gives information about all modules and all lessons and the presence
   of tabs in the lesson
   (if there is no tab, then "0";
   if there is a tab and it contains information, then "+";
   if there is a tab and it doesn't contain information, then "-";
   if there is a video tab and it contains video, then the number of attached videos is written,
   if there is a video tab and it doesn't contain video, then "-").
2) if the entered cohort does not exist, it gives information about absence of such cohort.
- The result look in folder 'result'.

## Getting complete information on all  Cohorts, all Modules and all Lessons 
- To get the information in all cohorts, all modules and lessons about the presence/absence of tabs 
  (Plan, Theory, Homework, Code, Video) and information about the presence/absence of data in each of these tabs
  you need to open the 'AllElementsInLmsTests' class in UI-tests.
  The report folder gives the following information options:
  it gives information about all cohorts, all modules and all lessons and the presence of tabs in each lesson
   (if there is no tab, then "0";
   if there is a tab and it contains information, then "+";
   if there is a tab and it doesn't contain information, then "-";
   if there is a video tab and it contains video, then the number of attached videos is written,
   if there is a video tab and it doesn't contain video, then "-").
- The result look in folder 'result'.


**********************
https://marketplace.zoom.us/