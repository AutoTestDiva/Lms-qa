package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllCohortsRATests extends TestBaseRA {

    @Test
    public void getAllCohortsTest(){
        given().contentType(ContentType.JSON).when().get("/cohorts").then().log().all()
                .assertThat().statusCode(200);
    }

}
