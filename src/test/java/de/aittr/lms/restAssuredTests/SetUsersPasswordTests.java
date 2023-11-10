package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class SetUsersPasswordTests extends TestBaseRA{

//    @Test(dependsOnMethods = "registerUserByAdminPositiveTest")
    @Test
    public void setUsersPasswordPositiveTest() throws SQLException {
        user.setPasswordByEmail("lilu2@mail.com", "Qwerty123!").then()
                .assertThat().statusCode(200);

    }


}
