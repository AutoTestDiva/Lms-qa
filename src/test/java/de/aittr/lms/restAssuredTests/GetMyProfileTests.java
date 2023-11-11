package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetMyProfileTests extends TestBaseRA{
    private Cookie cookie;

    @BeforeMethod
    public void precondition(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
    }

    @Test
    public void getMyProfilePositiveTest(){
        given().cookie(cookie).when().post("/users/me").then()
                .assertThat().statusCode(200);
//                .assertThat().body("email", equalTo("admin@mail.com"))
//                .assertThat().body("firstName", equalTo("Jhon"))
//                .assertThat().body("lastName", equalTo("Admin"))
//                .assertThat().body("country", equalTo("Germany"));
    }

}
