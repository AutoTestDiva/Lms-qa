package de.aittr.lms.restAssuredTests;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class SetUsersPasswordTests extends TestBaseRA{

//    @Test(dependsOnMethods = "registerUserByAdminPositiveTest")
    @Test (groups = {"newUser", "positive"}, dependsOnMethods = {"registerUserByAdminPositiveTest"})
    public void setUsersPasswordPositiveTest() throws SQLException {
        user.setPasswordByEmail("lilu@mail.com", "Qwerty123!").then()
                .assertThat().statusCode(200);

    }


}
