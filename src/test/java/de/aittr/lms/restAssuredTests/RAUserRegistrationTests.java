package de.aittr.lms.restAssuredTests;

import de.aittr.lms.dto.NewUserDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.*;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class RAUserRegistrationTests extends TestBaseRA{

    @Test ()
    public void registerUserPositiveTest() throws SQLException {
        user.registerUser("Cohort21", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567").then()
                .assertThat().statusCode(201);
        user.deleteUser("lilu@mail.com");
    }

    @Test ()
    public void registerExistedUserNegativeTest() throws SQLException {
        user.registerUser("Cohort21", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567");

        user.registerUser("Cohort21", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567").then()
                .assertThat().statusCode(409);
        user.deleteUser("lilu@mail.com");
    }

    //TODO Validation error register
    @Test
    public void registerWithNotValidData(){

    }




//        @Test // help to deleteUser when it needs.
    public void deleteUser() throws SQLException {
        user.deleteUser("lilu2@mail.com");
    }

}
