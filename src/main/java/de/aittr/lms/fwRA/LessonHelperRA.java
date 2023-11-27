package de.aittr.lms.fwRA;

import de.aittr.lms.dto.ZoomMeetingDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LessonHelperRA extends BaseHelperRA{

    public static ZoomMeetingDto ZoomMeetingBuilder(String topic, String dateToStart, String timeToStart, Integer duration) {
        return ZoomMeetingDto.builder()
                .topic(topic)
                .dateToStart(dateToStart)
                .timeToStart(timeToStart)
                .duration(duration)
                .build();
    }

    public Response createZoomMeeting(Cookie cookie, ZoomMeetingDto zoomMeeting) {
        return given().contentType(ContentType.JSON).cookie(cookie).body(zoomMeeting)
                .when().post("/create-meeting");
    }
}
