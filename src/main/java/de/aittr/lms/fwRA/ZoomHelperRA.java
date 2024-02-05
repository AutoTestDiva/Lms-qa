package de.aittr.lms.fwRA;

import de.aittr.lms.dto.ZoomMeetingDto;
import de.aittr.lms.dto.ZoomParametrizedMeetingDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.hamcrest.Condition;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class ZoomHelperRA extends BaseHelperRA{

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

    public Response createParamZoomMeeting(Cookie cookie, ZoomParametrizedMeetingDto zoomMeeting) {
        return given().contentType(ContentType.JSON).cookie(cookie).body(zoomMeeting)
                .when().post("/create-param-meeting");
    }

    public ZoomParametrizedMeetingDto ZoomParamMeetingBuilder(
            int cohortId, String lessonType, String lessonModule, int lessonsNr, String lessonTopic,
            String meetingOwnerEmail, String agenda, String dateToStart, String timeToStart, int duration) {

        return ZoomParametrizedMeetingDto.builder()
                .cohortId(cohortId)
                .lessonType(lessonType)
                .lessonModule(lessonModule)
                .lessonsNr(lessonsNr)
                .lessonTopic(lessonTopic)
                .meetingOwnerEmail(meetingOwnerEmail)
                .agenda(agenda)
                .dateToStart(dateToStart)
                .timeToStart(timeToStart)
                .duration(duration)
                .build();

    }


    public void deleteZoomMeeting(String uuidMeeting) {
        if(uuidMeeting != null){
            db.requestDelete("DELETE FROM lesson WHERE zoom_meeting_id = \"" + uuidMeeting + "\";");
            db.requestDelete("DELETE FROM zoom_meeting WHERE uuid = \"" + uuidMeeting + "\";");
        }
    }

    public boolean isMeetingInDatabaseInLessonsAndZoomMeetings(int cohortId, String module, int lessonNr, String topic) {
        String zoom_meeting_id;
        String meetingTopic;

        try {
            zoom_meeting_id = db.requestSelect("SELECT zoom_meeting_id FROM lesson" +
                    "    WHERE cohort_id = " + cohortId +
                    "    AND lesson_modul = \"" + module + "\"" +
                    "    AND lesson_topic = \"" + topic + "\"" +
                    "    AND lesson_nr = " + lessonNr + ";").getString(1);
        } catch (SQLException e){
            zoom_meeting_id = null;
            System.out.println("No such meeting in database in 'lessons'.");
        }

        try {
            meetingTopic = db.requestSelect(
                    "SELECT topic FROM zoom_meeting WHERE uuid = \"" + zoom_meeting_id + "\";")
                    .getString(1);
        } catch (SQLException e){
            meetingTopic = null;
            System.out.println("No such meeting in database in 'zoom_meeting'.");
        }

        if(zoom_meeting_id != null && topic.equalsIgnoreCase(meetingTopic) ){
            return true;
        } else {
            return false;
        }

    }


    public String getLessonModule(String uuidMeeting) {
        String lessonModule = null;
        try {
            lessonModule = db.requestSelect(
                            "SELECT lesson_modul FROM lesson WHERE zoom_meeting_id = \"" + uuidMeeting + "\";")
                    .getString(1);
        } catch (SQLException e){
            System.out.println("The uuid is not found." + e.getMessage());
        }
        return  lessonModule;
    }

    public String getLessonType(String uuidMeeting) {
        String lessonType = null;
        try {
            lessonType = db.requestSelect(
                            "SELECT lesson_type FROM lesson WHERE zoom_meeting_id = \"" + uuidMeeting + "\";")
                    .getString(1);
        } catch (SQLException e){
            System.out.println("The uuid is not found." + e.getMessage());
        }
        return  lessonType;
    }
}
