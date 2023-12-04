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
    public void precondition() throws SQLException {
        user.registerUser("Cohort 33", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567");
        user.setPasswordByEmail("lilu@mail.com", "Qwer123!");
        cookie = user.getLoginCookie("lilu@mail.com", "Qwer123!");
    }

    @Test
    public void userLogOutPositiveTest(){

        given().cookie(cookie).when().post("/logout").then()
                .assertThat().statusCode(200);

    }

    @Test
    public void notLoggedInUserLogOuNegativeTest(){
        given().when().post("/logout").then()
                .assertThat().statusCode(403);
    }

    @AfterMethod
    public void postCondition() throws SQLException {
        user.deleteUser("lilu@mail.com");
    }

}
