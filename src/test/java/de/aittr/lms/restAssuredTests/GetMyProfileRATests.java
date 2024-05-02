package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetMyProfileRATests extends TestBaseRA{

    private Cookie cookie;

    @BeforeMethod
    public void precondition() throws SQLException{
        user.registerUser("Cohort 99", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567", "STUDENT");
        user.setPasswordByEmail("lilu@mail.com", "LMS-dev-pass-2024");
        cookie = user.getLoginCookie("lilu@mail.com", "LMS-dev-pass-2024");
    }

    @AfterMethod
    public void postCondition() throws SQLException {
        user.deleteUser("lilu@mail.com");
    }

    @Test ()
    public void getMyProfilePositiveTest(){
        given().contentType(ContentType.JSON).cookie(cookie).when().get("/users/me").then()
                .assertThat().statusCode(200)
                .assertThat().body("email", equalTo("lilu@mail.com"))
                .assertThat().body("firstName", equalTo("Lilu"))
                .assertThat().body("lastName", equalTo("Test"))
                .assertThat().body("country", equalTo("Germany"));
    }

    @Test ()
    public void getMyProfileWithoutLoginNegativeTest(){
        given().contentType(ContentType.JSON).when().get("/users/me").then()
                .assertThat().statusCode(401);
    }


}
