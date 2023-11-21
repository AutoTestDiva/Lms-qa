package de.aittr.lms.fwRA;

import de.aittr.lms.DataBase;
import org.springframework.web.util.UriComponentsBuilder;

public class BaseHelperRA {

    protected static DataBase db;

    public BaseHelperRA() {
    }

    public String urlBuilderGetVideo(String cohort, String module, String type, int lesson){
        String parametrUrl = "get-videos?cohort=" + cohort
                + "&module=" + module + "&type=" + type + "&lessonsNr=" + lesson + "";
        return parametrUrl;
    }


}
