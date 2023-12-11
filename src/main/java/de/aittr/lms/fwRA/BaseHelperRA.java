package de.aittr.lms.fwRA;

import de.aittr.lms.DataBase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseHelperRA {

    protected static DataBase db;

    public BaseHelperRA() {
    }

    public String urlBuilderGetVideo(String cohort, String module, String type, String lesson){
        String parametrUrl = "get-videos?cohort=" + cohort
                + "&module=" + module + "&type=" + type + "&lessonsNr=" + lesson + "";
        return parametrUrl;
    }

    public String timeMinus1Hour(String baseTime){
        LocalDateTime currentDateTime = LocalDateTime.parse(baseTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime newDateTime = currentDateTime.minusHours(1); // Добавление 1 часа
        return newDateTime.format(DateTimeFormatter.ISO_DATE_TIME); // Преобразование обратно в строку
    }

}
