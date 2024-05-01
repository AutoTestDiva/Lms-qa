package de.aittr.lms.restAssuredTests;

import de.aittr.lms.CSVDataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class SetUsersPasswordRATests extends TestBaseRA {
    @BeforeMethod
    public void precondition() throws SQLException {
        user.registerUser("Cohort 99", "lilu3@mail.com", "Tester", "Test",
                "Germany", "+490123651812", "STUDENT");
    }

    @Test
    public void setUsersPasswordPositiveTest() throws SQLException {
        user.setPasswordByEmail("lilu3@mail.com", "LMS-dev-pass-2024").then()
                .assertThat().statusCode(200);
    }

    @Test(dataProvider = "provideNotValidPassword", dataProviderClass = CSVDataProviders.class)
    public void setNotValidPasswordTest(String password) throws SQLException {
        user.setPasswordByEmail("lilu3@mail.com", password).then()
                .assertThat().statusCode(400);
    }

    @AfterMethod
    public void afterTest() throws SQLException {
        user.deleteUser("lilu3@mail.com");
    }
}
