package de.aittr.lms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ZoomParametrizedMeetingDto {

    Integer [] cohortIds;
    String lessonType;
    String lessonModule;
    String lessonsNr;
    String lessonTopic;
    //String meetingOwnerEmail; //
    String agenda;
    String dateToStart;
    String timeToStart;
    Integer duration;

}


