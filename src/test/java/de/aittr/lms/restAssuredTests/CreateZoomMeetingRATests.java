package de.aittr.lms.restAssuredTests;

import de.aittr.lms.dto.ZoomMeetingDto;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class CreateZoomMeetingRATests extends TestBaseRA{

    Cookie cookie;
    ZoomMeetingDto zoomMeeting;

    @Test
    public void createZoomMeetingByAdminPositiveTest(){
        String date = "2024-03-15";
        String time = "17:00";
        String baseTime = date + "T" + time + ":00";
        String meetingTime = lesson.timeMinus1Hour(baseTime); // depends of local timeZone QA, if Germany -> don`t need

        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        zoomMeeting = lesson.ZoomMeetingBuilder(
                "Java lesson #22", date, time, 300);
        lesson.createZoomMeeting(cookie, zoomMeeting).then()
                .assertThat().statusCode(200)
                .assertThat().body("meetingTime", equalTo(meetingTime));


    }

    @Test
    public void createZoomMeetingByTeacherPositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwerty123!");
        zoomMeeting = lesson.ZoomMeetingBuilder(
                "Java lesson #23", "2024-03-17", "17:00", 300);
        lesson.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(200);
    }

    @Test
    public void createZoomMeeting32MarchNegativeTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwerty123!");
        zoomMeeting = lesson.ZoomMeetingBuilder(
                "Java lesson #23", "2024-03-32", "17:00", 300);
        lesson.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(400);
    }

    @Test
    public void createZoomMeetingDurationOminNegativeTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwerty123!");
        zoomMeeting = lesson.ZoomMeetingBuilder(
                "Java lesson #23", "2024-03-02", "17:00", 0);
        lesson.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(400);
    }

    @Test
    public void createZoomMeetingByStudentNegativeTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        zoomMeeting = lesson.ZoomMeetingBuilder(
                "Java lesson #23", "2024-03-02", "17:00", 300);
        lesson.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(403);
    }

    @Test
    public void createZoomMeetingWithEmptyTopicNegativeTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwerty123!");
        zoomMeeting = lesson.ZoomMeetingBuilder(
                "", "2024-03-17", "17:00", 300);
        lesson.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(400);
    }

}
