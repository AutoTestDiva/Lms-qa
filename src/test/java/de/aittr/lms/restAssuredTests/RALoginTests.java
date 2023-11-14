package de.aittr.lms.restAssuredTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class RALoginTests extends TestBaseRA{

    @BeforeMethod
    public void precondition() throws SQLException {
        user.registerUser("Cohort21", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567");
        user.setPasswordByEmail("lilu@mail.com", "Qwerty123!");
    }

    @AfterMethod
    public void postCondition() throws SQLException {
        user.deleteUser("lilu@mail.com");
    }

    @Test
    public void loginAsAdmin() {
        user.loginUserRA("admin@mail.com", "Admin123!")
                .then()
                .assertThat().statusCode(200);
    }

    @Test ()
    public void loginAsUserPositiveTest() {

        user.loginUserRA("lilu@mail.com", "Qwerty123!")
                .then()
                .assertThat().statusCode(200);
    }

    @Test ()
    public void loginAsUserWithWrongPasswordNegativeTest() {

        user.loginUserRA("lilu@mail.com", "Qwerty132!")
                .then()
                .assertThat().statusCode(401);
    }

    @Test ()
    public void loginNotExistedUserNegativeTest() {

        user.loginUserRA("lilu25@mail.com", "Qwerty123!")
                .then()
                .assertThat().statusCode(401);
    }

}
