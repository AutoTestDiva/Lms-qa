package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RAGetMyProfileTests extends TestBaseRA{
    private Cookie cookie;

    @BeforeMethod
    public void precondition(){
        cookie = user.getLoginCookie("lilu@mail.com", "Qwerty123!");
    }

    @Test (groups = {"newUser", "positive"}, dependsOnMethods = {"loginAsUserPositiveTest"})
    public void getMyProfilePositiveTest(){
        given().contentType(ContentType.JSON).cookie(cookie).when().get("/users/me").then()
                .assertThat().statusCode(200)
                .assertThat().body("email", equalTo("lilu@mail.com"))
                .assertThat().body("firstName", equalTo("Lilu"))
                .assertThat().body("lastName", equalTo("Test"))
                .assertThat().body("country", equalTo("Germany"));
    }

}
