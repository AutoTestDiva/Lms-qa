package de.aittr.lms.restAssuredTests;

import de.aittr.lms.CSVDataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class SetUsersPasswordRATests extends TestBaseRA{

    @BeforeMethod
    public void precondition(){
        user.registerUser("Cohort 33", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567");
    }



    @Test
    public void setUsersPasswordPositiveTest() throws SQLException {
        user.setPasswordByEmail("lilu@mail.com", "Qwer123!").then()
                .assertThat().statusCode(200);
    }

    @Test(dataProvider = "provideNotValidPassword", dataProviderClass = CSVDataProviders.class)
    public void setNotValidPasswordTest(String password) throws SQLException {
        user.setPasswordByEmail("lilu@mail.com", password).then()
                .assertThat().statusCode(400);
    }

    @AfterMethod
    public void deleteUser() throws SQLException {
        user.deleteUser("lilu@mail.com");
    }



}
