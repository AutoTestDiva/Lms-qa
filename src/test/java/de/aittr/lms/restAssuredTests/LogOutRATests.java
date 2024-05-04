package de.aittr.lms.restAssuredTests;

import io.restassured.http.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class LogOutRATests extends TestBaseRA{

    private Cookie cookie;
    @BeforeMethod
    public void precondition() {
        user.registerUser("Cohort 99", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567", "STUDENT");
        user.setPasswordByEmail("lilu@mail.com", "LMS-dev-pass-2024");
        cookie = user.getLoginCookie("lilu@mail.com", "LMS-dev-pass-2024");
    }

    @Test
    public void userLogOutPositiveTest(){

        given().cookie(cookie).when().post("/logout").then()
                .assertThat().statusCode(200);

    }
    @AfterMethod
    public void postCondition() {
        user.deleteUser("lilu@mail.com");
    }

}
