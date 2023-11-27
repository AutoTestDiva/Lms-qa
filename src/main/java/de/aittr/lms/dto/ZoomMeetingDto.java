package de.aittr.lms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ZoomMeetingDto {

    String topic;
    String dateToStart;
    String timeToStart;
    Integer duration;

}
