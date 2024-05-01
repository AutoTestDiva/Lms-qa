package de.aittr.lms.restAssuredTests;

import de.aittr.lms.dto.ZoomParametrizedMeetingDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateParamZoomMeetingTests extends TestBaseRA {
    Cookie cookie;
    ZoomParametrizedMeetingDto zoomMeeting;
    String uuidMeeting;
    Integer[] cohortIds = new Integer[]{38}; // это id когорта 99
    String module = "BASIC_PROGRAMMING";
    String lessonNr = "01";
    String lessonTopic = "Jenkins";
    ZonedDateTime date = ZonedDateTime.of(2024,
            10,
            01,
            17,
            0,
            0,
            0,
            ZoneId.of("Europe/Berlin"));
    String dateToStart = date.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String timeToStart = date.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

    @AfterMethod
    public void deleteMeeting() {
        zoom.deleteZoomMeeting(uuidMeeting);
    }

    @Test
    public void CreateParamZoomMeetingByTeacherPositiveTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", module, lessonNr,
                lessonTopic, "new lesson 45", dateToStart, timeToStart, 140);

        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("lesson.cohortDto.id", equalTo(Arrays.asList(cohortIds)))
                .assertThat().body("lesson.lessonModul", hasItem(module))
                .assertThat().body("lesson.lessonNr", hasItem(Integer.valueOf(lessonNr)))
                .assertThat().body("lesson.lessonTopic", hasItem(lessonTopic))
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertTrue(zoom.isMeetingInDatabaseInLessonsAndZoomMeetings(cohortIds, module, lessonTopic, lessonNr));
    }

    @Test
    public void CreateParamZoomMeetingByAdminPositiveTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024"); // пока указан логин тичера, т.к. у админа нет зумаккаунта
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", module, lessonNr,
                lessonTopic, "Some info of lesson", "2024-09-20"
                , "07:22", 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertTrue(zoom.isMeetingInDatabaseInLessonsAndZoomMeetings(cohortIds, module, lessonTopic, lessonNr));
    }

    @Test
    public void CreateParamZoomMeetingWithoutAuthNegativeTest() {
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", "BASIC_PROGRAMMING", "22",
                "Jenkins", "Some info of lesson", "2024-09-20",
                "17:00", 240);
        given().contentType(ContentType.JSON).body(zoomMeeting)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(401); // в свагере тоже 401 но как недокументированная ошибка, хотя должна быть 403.... видимо баг
    }

    @Test
    public void CreateParamZoomMeetingByStudentNegativeTest() {
        cookie = user.getLoginCookie("s03@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", "BASIC_PROGRAMMING", "22",
                "Jenkins", "Some info of lesson", "2024-09-20",
                "17:00", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(403);
    }
//    @Test //непонятна целесообразность теста - возможно у учителя ранее не было права создавать митинг
//    public void CreateParamZoomMeetingByTeacherOwnerAdminNegativeTest(){
//        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
//        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", "BASIC_PROGRAMMING", "22",
//                "Jenkins", "Some info of lesson", "2024-09-20",
//                "17:00",  240);
//        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
//                .assertThat().statusCode(403);
//    }

    //    @Test //тест уже неактуален, т.к. со Сваггера удалено значение "meetingOwnerEmail"
//    public void CreateParamZoomMeetingByTeacherOwnerTeacher2NegativeTest(){
//        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
//        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", "25",
//                "Jenkins", "teacher2@mail.com", "Some info of lesson",
//                "2024-09-20", "17:00", 240);
//        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
//                .assertThat().statusCode(403);
//    }
//
//    @Test //тест уже неактуален, т.к. со Сваггера удалено значение "meetingOwnerEmail"
//    public void CreateParamZoomMeetingByTeacherOwnerNotExistNegativeTest(){
//        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
//        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", "26",
//                "Jenkins", "example@mail.com", "Some info of lesson",
//                "2024-09-20", "17:00", 240);
//        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
//                .assertThat().statusCode(404);
//    }

    @Test
    public void zoomMeetingByTeacherNotExistLessonModuleNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", "Test", "22",
                "Jenkins", "Some info of lesson", "2024-09-20",
                "17:00", 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then().log().all()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertEquals(zoom.getLessonModule(uuidMeeting), "UNDEFINED");
    }

    @Test
    public void zoomMeetingByTeacherNotExistTypeNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024"); // пока указан логин тичера, т.к. у админа нет зумаккаунта
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "Test", "BASIC_PROGRAMMING", "22",
                "Jenkins", "Some info of lesson", "2024-09-20",
                "17:00", 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then().log().all()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertEquals(zoom.getLessonType(uuidMeeting), "UNDEFINED");
    }

//   @Test // баг-    this test doesn`t show status 500, because meeting can be duplicated.
//    public void zoomMeetingByTeacherExistThisMeetingNegTest(){
//        cookie = user.getLoginCookie("t04@dev-lms.de", "LMS-dev-pass-2024");
//        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", module, lessonNr,
//                lessonTopic, "new lesson 45", dateToStart, timeToStart, 140);
//
//                zoom.createParamZoomMeeting(cookie, zoomMeeting);
//                zoom.createParamZoomMeeting(cookie, zoomMeeting).then().assertThat().statusCode(500);
//    }

    @Test
    public void zoomMeetingByTeacherModuleNullNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", "", lessonNr,
                lessonTopic, "Some info of lesson", "2024-09-20"
                , "07:22", 240);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherCohortIDLetterNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        String body = "{\n" +
                "  \"cohortIds\": \"[\"a\"]\",\n" +
                "  \"lessonType\": \"LECTURE\",\n" +
                "  \"lessonModule\": \"BASIC_PROGRAMMING\",\n" +
                "  \"lessonsNr\": \"01\",\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
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
    public void zoomMeetingByTeacherType22NegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        String body = "{\n" +
                "  \"cohortIds\": \"[38]\",\n" +
                "  \"lessonType\": 22,\n" +
                "  \"lessonModule\": \"BASIC_PROGRAMMING\",\n" +
                "  \"lessonsNr\": \"01\",\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
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
    public void zoomMeetingByTeacherModuleCyrillicNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", "Тестирование", lessonNr,
                lessonTopic, "Some info of lesson", "2024-09-20"
                , "07:22", 240);
        uuidMeeting = zoom.createParamZoomMeeting(cookie, zoomMeeting).then().log().all()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getString("meeting.uuid");
        Assert.assertEquals(zoom.getLessonModule(uuidMeeting), "UNDEFINED");
    }

    @Test
    public void zoomMeetingByTeacherLessonNumOneNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        String body = "{\n" +
                "  \"cohortIds\": \"[38]\",\n" +
                "  \"lessonType\": \"LECTURE\",\n" +
                "  \"lessonModule\": \"BASIC_PROGRAMMING\",\n" +
                "  \"lessonsNr\": 1,\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
                "  \"agenda\": \"Some info of lesson\",\n" +
                "  \"dateToStart\": \"2024-09-20\",\n" +
                "  \"timeToStart\": \"17:00\",\n" +
                "  \"duration\": 240\n" +
                "}";
        given().contentType(ContentType.JSON).cookie(cookie).body(body)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(400);
    }

//    @Test //тест уже неактуален, т.к. со Сваггера удалено значение "meetingOwnerEmail"
//    public void zoomMeetingByTeacherOwnerWrongEmailNegTest(){
//        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
//        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", "30",
//                "Jenkins", "teacher.mail.com", "Some info of lesson",
//                "2024-09-20", "17:00", 240);
//        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
//                .assertThat().statusCode(400);
//    }

    //    @Test //тест уже неактуален, т.к. со Сваггера удалено значение "meetingOwnerEmail"
//    public void zoomMeetingByTeacherOwnerWrongDateNegTest(){
//        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
//        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", "30",
//                "Jenkins", "teacher@mail.com", "Some info of lesson",
//                "20-09-2024", "17:00", 240);
//        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
//                .assertThat().statusCode(400);
//    }
//
//    @Test //тест уже неактуален, т.к. со Сваггера удалено значение "meetingOwnerEmail"
//    public void zoomMeetingByTeacherOwnerWrongTimeNegTest(){
//        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
//        zoomMeeting = zoom.ZoomParamMeetingBuilder(24, "LECTURE", "QA", "30",
//                "Jenkins", "teacher@mail.com", "Some info of lesson",
//                "2024-09-20", "17.00", 240);
//        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
//                .assertThat().statusCode(400);
//    }
//
    @Test
    public void zoomMeetingByTeacherDuration0TimeNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomParamMeetingBuilder(cohortIds, "LECTURE", module, lessonNr,
                lessonTopic, "new lesson 45", dateToStart, timeToStart, 0);
        zoom.createParamZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(400);
    }

    @Test
    public void zoomMeetingByTeacherNegativeCohortNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        String body = "{\n" +
                "  \"cohortIds\": \"[-38]\",\n" +
                "  \"lessonType\": \"LECTURE\",\n" +
                "  \"lessonModule\": \"BASIC_PROGRAMMING\",\n" +
                "  \"lessonsNr\": \"01\",\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
                "  \"agenda\": \"Some info of lesson\",\n" +
                "  \"dateToStart\": \"2024-09-20\",\n" +
                "  \"timeToStart\": \"17:00\",\n" +
                "  \"duration\": 240\n" +
                "}";
        given().contentType(ContentType.JSON).cookie(cookie).body(body)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(400);
    }

    @Test //тест повторяющийся. Возник в старой версии данных, где lessonNr было интеджер
    public void zoomMeetingByTeacherNegativeLessonNegTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        String body = "{\n" +
                "  \"cohortIds\": \"[38]\",\n" +
                "  \"lessonType\": \"LECTURE\",\n" +
                "  \"lessonModule\": \"BASIC_PROGRAMMING\",\n" +
                "  \"lessonsNr\": -30,\n" +
                "  \"lessonTopic\": \"Appium inspector\",\n" +
                "  \"agenda\": \"Some info of lesson\",\n" +
                "  \"dateToStart\": \"2024-09-20\",\n" +
                "  \"timeToStart\": \"17:00\",\n" +
                "  \"duration\": 240\n" +
                "}";
        given().contentType(ContentType.JSON).cookie(cookie).body(body)
                .when().post("/create-param-meeting").then()
                .assertThat().statusCode(400);
    }
}








