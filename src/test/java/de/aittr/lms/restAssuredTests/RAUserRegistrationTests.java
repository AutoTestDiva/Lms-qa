package de.aittr.lms.restAssuredTests;

import de.aittr.lms.CSVDataProviders;
import org.testng.annotations.*;
import java.sql.SQLException;

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

    @Test(dataProvider = "provideWrongUserData", dataProviderClass = CSVDataProviders.class)
    public void registerWithNotValidData(String cohort, String email, String firstname, String lastname,
    String country, String phone){
        //TODO Validation error register

        user.registerUser(cohort, email, firstname, lastname, country, phone).then()
                .assertThat().statusCode(400);

    }




//        @Test // help to deleteUser from database when it needs.
    public void deleteUser() throws SQLException {
        user.deleteUser("lilu2@mail.com");
    }

}
