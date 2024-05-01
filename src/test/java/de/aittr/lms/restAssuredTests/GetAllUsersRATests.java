package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllUsersRATests extends TestBaseRA{

    private Cookie cookie;

    @BeforeMethod
    public void precondition(){
        cookie = user.getLoginCookie("a01@dev-lms.de", "LMS-dev-pass-2024");
    }

    @Test
    public void getAllUsersByAdminPositiveTest(){
        String body = given().contentType(ContentType.JSON).cookie(cookie).when().get("/users").then()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(body);
    }

    @Test
    public void getAllUsersWithoutAdminNegativeTest(){
        given().contentType(ContentType.JSON).when().get("/users").then()
                .assertThat().statusCode(401);
    }



}
