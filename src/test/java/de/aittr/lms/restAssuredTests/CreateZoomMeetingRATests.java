package de.aittr.lms.restAssuredTests;

import de.aittr.lms.dto.ZoomMeetingDto;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalTo;

public class CreateZoomMeetingRATests extends TestBaseRA {
    Cookie cookie;
    ZoomMeetingDto zoomMeeting;

//    @Test
//    public void createZoomMeetingByAdminPositiveTest() {
//        String date = "2024-09-22";
//        String time = "19:00:00";
//        LocalDateTime dataT = LocalDateTime.of(2024,
//                9, 24, 17,
//                0, 0, 0);
//        String baseTime = dataT
//                .atZone(ZoneId.of("Europe/Berlin"))
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//
//        cookie = user.getLoginCookie("t03@dev-lms.de", "LMS-dev-pass-2024");
//        zoomMeeting = zoom.ZoomMeetingBuilder(
//                "Java lesson #27", date, time, 300);
//        zoom.createZoomMeeting(cookie, zoomMeeting).then()
//                .log().all()
//                .assertThat().statusCode(200)
//                .assertThat().body("meetingTime", equalTo(baseTime));
//    }

    @Test
    public void createZoomMeetingByTeacherPositiveTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomMeetingBuilder(
                "Java lesson #23", "2024-07-17", "17:00", 300);
        zoom.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(200);
    }

    @Test
    public void createZoomMeeting32MarchNegativeTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomMeetingBuilder(
                "Java lesson #23", "2024-07-32", "17:00", 300);
        zoom.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(400);
    }

    @Test
    public void createZoomMeetingDurationOminNegativeTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomMeetingBuilder(
                "Java lesson #23", "2024-07-02", "17:00", 0);
        zoom.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(400);
    }

    @Test
    public void createZoomMeetingByStudentNegativeTest() {
        cookie = user.getLoginCookie("s03@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomMeetingBuilder(
                "Java lesson #23", "2024-07-02", "17:00", 300);
        zoom.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(403);
    }

    @Test
    public void createZoomMeetingWithEmptyTopicNegativeTest() {
        cookie = user.getLoginCookie("a04@dev-lms.de", "LMS-dev-pass-2024");
        zoomMeeting = zoom.ZoomMeetingBuilder(
                "", "2024-03-17", "17:00", 300);
        zoom.createZoomMeeting(cookie, zoomMeeting).then().
                assertThat().statusCode(400);
    }

}
