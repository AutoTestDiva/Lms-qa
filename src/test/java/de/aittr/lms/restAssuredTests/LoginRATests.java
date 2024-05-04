package de.aittr.lms.restAssuredTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class LoginRATests extends TestBaseRA{


    @Test
    public void loginAsAdmin() {
        user.loginUserRA("a01@dev-lms.de", "LMS-dev-pass-2024")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void loginAsTeacher() {
        user.loginUserRA("t03@dev-lms.de", "LMS-dev-pass-2024")
                .then()
                .assertThat().statusCode(200);
    }

    @Test ()
    public void loginAsStudentPositiveTest() {

        user.loginUserRA("s01@dev-lms.de", "LMS-dev-pass-2024")
                .then()
                .assertThat().statusCode(200);
    }

    @Test ()
    public void loginAsUserWithWrongPasswordNegativeTest() {

        user.loginUserRA("s01@dev-lms.de", "test-dev-pass-2024")
                .then()
                .assertThat().statusCode(401);
    }

    @Test ()
    public void loginNotExistedUserNegativeTest() {
        user.loginUserRA("lilu225@mail.com", "LMS-dev-pass-2024")
                .then()
                .assertThat().statusCode(401);
    }
}
