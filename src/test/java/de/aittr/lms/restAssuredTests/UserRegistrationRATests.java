package de.aittr.lms.restAssuredTests;

import de.aittr.lms.CSVDataProviders;
import org.testng.annotations.*;

import java.sql.SQLException;

public class UserRegistrationRATests extends TestBaseRA {
    @Test()
    public void registerUserPositiveTest() throws SQLException, InterruptedException {
        user.registerUser("Cohort 99", "lilu3@mail.com", "Lilu", "Test",
                        "Germany", "+490571234567", "STUDENT")
                .then()
                .assertThat()
                .statusCode(201);
        user.deleteUser("lilu3@mail.com");
    }

//    @Test //нет ограничений в количестве цифр телефона
//    public void registerUserNumberPhone14PositiveTest() throws SQLException, InterruptedException {
//        Thread.sleep(500);
//        user.registerUser("Cohort 99", "testlu@mail.com", "Lilu", "Test",
//                        "Germany", "+4915172667876", "STUDENT").then()
//                .assertThat().statusCode(201);
//        Thread.sleep(500);
//        user.deleteUser("testlu@mail.com");
//    }

    @Test()
    public void registerExistedUserNegativeTest() throws SQLException, InterruptedException {
        user.registerUser("Cohort 99", "lilu@mail.com", "Lilu", "Test",
                "Germany", "+490571234567", "STUDENT");

        user.registerUser("Cohort 99", "lilu@mail.com", "Lilu", "Test",
                        "Germany", "+490571234567", "STUDENT").then()
                .assertThat().statusCode(409);
        user.deleteUser("lilu@mail.com");
    }

    @Test(dataProvider = "provideWrongUserData", dataProviderClass = CSVDataProviders.class)
    public void registerWithNotValidData(String cohort, String email, String firstname, String lastname,
                                         String country, String phone, String role) throws SQLException, InterruptedException {
        user.registerUser(cohort, email, firstname, lastname, country, phone, role).then()
                .assertThat().statusCode(400);
        user.deleteUser(email);
    }
}
