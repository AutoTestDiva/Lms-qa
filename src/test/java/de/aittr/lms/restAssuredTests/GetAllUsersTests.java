package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllUsersTests extends TestBaseRA{

    private Cookie cookie;

    @BeforeMethod
    public void precondition(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
    }

    @Test
    public void getAllUsersByAdminPositiveTest(){
        given().contentType(ContentType.JSON).cookie(cookie).when().get("/users").then()
                .assertThat().statusCode(200);
    }

    @Test
    public void getAllUsersWithoutAdminNegativeTest(){
        given().contentType(ContentType.JSON).when().get("/users").then()
                .assertThat().statusCode(403);
    }



}
