package de.aittr.lms.restAssuredTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class RASetUsersPasswordTests extends TestBaseRA{

    @BeforeMethod
    public void precondition(){
        user.registerUser("Cohort21", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567");
    }

    @Test
    public void setUsersPasswordPositiveTest() throws SQLException {
        user.setPasswordByEmail("lilu@mail.com", "Qwerty123!").then()
                .assertThat().statusCode(200);
    }

//    @AfterMethod
    public void deleteUser() throws SQLException {
        user.deleteUser("lilu@mail.com");
    }

//    TODO negative test easy password 400 Validation error


}
