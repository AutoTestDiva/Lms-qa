package de.aittr.lms.restAssuredTests;

import de.aittr.lms.dto.ZoomParametrizedMeetingDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateParamZoomMeetingTests extends TestBaseRA{

    Cookie cookie;
    ZoomParametrizedMeetingDto zoomMeeting;
    String uuidMeeting;

    @AfterMethod
    public void deleteMeeting(){
        zoom.deleteZoomMeeting(uuidMeeting);
    }

    @Test // Help to delete zoom_meeting if test failed
    public void delete(){
        zoom.deleteZoomMeeting("e6wQm0sOSaa6LGG9pQ28FA==");
    }

    @Test
    public void CreateParamZoomMeetingByTeacherPositiveTest(){

        ZonedDateTime date = ZonedDateTime.of(2024,
                9,
                20,
                17,
                0,
                0,
                0,
                ZoneId.of("Europe/Berlin"));

//        LocalTime time = LocalTime.of(17, 00);
//        ZonedDateTime meetingTimeZone = ZonedDateTime.of(date, time, ZoneId.of("Europe/Berlin"));
//        String dateOfMeeting = meetingTimeZone.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String timeOfMeeting = meetingTimeZone.format(DateTimeFormatter.ofPattern("HH:mm"));
        String dateOfMeeting = date.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeOfMeeting = date.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        int cohortId = 24;
        String module = "QA";
        int lessonNr = 22;
        String topic = "Jenkins";

        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortId, "LECTURE", module, lessonNr,
                topic, "teacher@mail.com", "Some info of lesson",
                dateOfMeeting, timeOfMeeting, 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(200)
                .assertThat().body("lesson.cohortId", equalTo(cohortId))
                .assertThat().body("lesson.lessonModul", equalTo(module))
                .assertThat().body("lesson.lessonNr", equalTo(lessonNr))
                .assertThat().body("lesson.lessonTopic", equalTo(topic))
// TODO               .assertThat().body("meeting.meetingTime", equalTo(meetingTimeZone)); //"meetingTime": "2024-12-13T12:45:00"
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertTrue(zoom.isMeetingInDatabaseInLessonsAndZoomMeetings(cohortId, module, lessonNr, topic));
    }

    @Test
    public void CreateParamZoomMeetingByAdminPositiveTest(){
        int cohortId = 24;
        String module = "QA";
        int lessonNr = 22;
        String topic = "Jenkins";

        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortId, "LECTURE", module, lessonNr,
                topic, "admin@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertTrue(zoom.isMeetingInDatabaseInLessonsAndZoomMeetings(cohortId, module, lessonNr, topic));
    }

    @Test
    public void CreateParamZoomMeetingWithoutAuthNegativeTest(){
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 22,
                "Jenkins", "admin@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        given().contentType(ContentType.JSON).body(zoomMeeting)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(403);
    }

    @Test
    public void CreateParamZoomMeetingByStudentNegativeTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 23,
                "Jenkins", "student@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(403);
    }

    @Test
    public void CreateParamZoomMeetingByTeacherOwnerAdminNegativeTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 24,
                "Jenkins", "admin@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(403);
    }

    @Test
    public void CreateParamZoomMeetingByTeacherOwnerTeacher2NegativeTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 25,
                "Jenkins", "teacher2@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(403);
    }

    @Test
    public void CreateParamZoomMeetingByTeacherOwnerNotExistNegativeTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 26,
                "Jenkins", "example@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(404);
    }

    @Test
    public void zoomMeetingByTeacherNotExistCohortNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(200, "LECTURE", "QA", 27,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(404);
    }

    @Test
    public void zoomMeetingByTeacherNotExistTypeNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "READING", "QA", 28,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertEquals(zoom.getLessonType(uuidMeeting), "UNDEFINED");
    }

//    @Test
//    this test doesn`t show status 500, because meeting can be duplicated.
    public void zoomMeetingByTeacherExistThisMeetingNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");

        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 29,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);

//        uuidMeeting =
                zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .extract().response().jsonPath().getString("meeting.uuid");

        zoom.createParamZoomMeeting(cookie, zoomMeeting).then().assertThat().statusCode(500);

    }

    @Test
    public void zoomMeetingByTeacherModuleNullNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "", 30,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherCohortIDLetterNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String body = "{\n" +
                "  \"cohortId\": \"Second\",\n" +
                "  \"lessonType\": \"LECTURE\",\n" +
                "  \"lessonModule\": \"QA\",\n" +
                "  \"lessonsNr\": 32,\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
                "  \"meetingOwnerEmail\": \"teacher@mail.com\",\n" +
                "  \"agenda\": \"Some info of lesson\",\n" +
                "  \"dateToStart\": \"2024-09-20\",\n" +
                "  \"timeToStart\": \"17:00\",\n" +
                "  \"duration\": 240\n" +
                "}";
        given().contentType(ContentType.JSON).cookie(cookie).body(body)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherType22NegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String body = "{\n" +
                "  \"cohortId\": 24,\n" +
                "  \"lessonType\": 22,\n" +
                "  \"lessonModule\": \"QA\",\n" +
                "  \"lessonsNr\": 33,\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
                "  \"meetingOwnerEmail\": \"teacher@mail.com\",\n" +
                "  \"agenda\": \"Some info of lesson\",\n" +
                "  \"dateToStart\": \"2024-09-20\",\n" +
                "  \"timeToStart\": \"17:00\",\n" +
                "  \"duration\": 240\n" +
                "}";
        uuidMeeting = given().contentType(ContentType.JSON).cookie(cookie).body(body)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertEquals(zoom.getLessonType(uuidMeeting), "UNDEFINED");
    }

    @Test
    public void zoomMeetingByTeacherModuleCyrillicNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "Тестирование",
                34, "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertEquals(zoom.getLessonModule(uuidMeeting), "UNDEFINED");
    }

    @Test
    public void zoomMeetingByTeacherLessonNumFiveNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String body = "{\n" +
                "  \"cohortId\": 24,\n" +
                "  \"lessonType\": \"LECTURE\",\n" +
                "  \"lessonModule\": \"QA\",\n" +
                "  \"lessonsNr\": \"five\",\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
                "  \"meetingOwnerEmail\": \"teacher@mail.com\",\n" +
                "  \"agenda\": \"Some info of lesson\",\n" +
                "  \"dateToStart\": \"2024-09-20\",\n" +
                "  \"timeToStart\": \"17:00\",\n" +
                "  \"duration\": 240\n" +
                "}";
        given().contentType(ContentType.JSON).cookie(cookie).body(body)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherOwnerWrongEmailNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 30,
                "Jenkins", "teacher.mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherOwnerWrongDateNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 30,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "20-09-2024", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherOwnerWrongTimeNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 30,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17.00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherDuration0TimeNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", 30,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 0);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherNegativeCohortNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(-24, "LECTURE", "QA", 30,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherNegativeLessonNegTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", -30,
                "Jenkins", "teacher@mail.com", "Some info of lesson",
                "2024-09-20", "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }



}








