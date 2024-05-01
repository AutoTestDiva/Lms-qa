package de.aittr.lms.restAssuredTests;

import de.aittr.lms.CSVDataProviders;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PasswordChangeRATests extends TestBaseRA {
    public static final String PASS_CHANGED_SUCCESSFULLY_MSG = "Your password has been successfully changed";
    private Cookie cookie;

   @BeforeMethod
    public void precondition() throws SQLException {
       user.registerUser("Cohort 99", "lilu3@mail.com", "Tester", "Test",
               "Germany", "+490123651812","STUDENT");
        user.setPasswordByEmail("lilu3@mail.com", "LMS-dev-pass-2024");
        cookie = user.getLoginCookie("lilu3@mail.com", "LMS-dev-pass-2024");
       user.userStatusConfirmed("lilu3@mail.com"); //changes the status to CONFIRMED in 2 database tables
   }

    @AfterMethod
    public void afterTest() throws SQLException {
        user.deleteUser("lilu3@mail.com");
    }

    //@BeforeMethod
//    @Test
//    public void precondition(){
//        user.loginUserRA("a01@dev-lms.de", "LMS-dev-pass-2024");
//    }

    @Test
    public void passwordChangeSuccessPositiveTest() {
        String requestBody = """
                {
                 "oldPassword" : "LMS-dev-pass-2024",
                 "newPassword" : "LMS-dev-pass-2024!"
                }
                """;
        given()
                .cookie(cookie)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users/password-change").then().log().all().assertThat().statusCode(201)
                .assertThat().body("message", equalTo(PASS_CHANGED_SUCCESSFULLY_MSG));

    }
//    @AfterMethod
//    public void returnOldPassword() {
//        String requestBody = """
//                {
//                 "oldPassword" : "LMS-dev-pass-2024!",
//                 "newPassword" : "LMS-dev-pass-2024"
//                }
//                """;
//        given()
//                .cookie(cookie)
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .post("/users/password-change").then().log().all().assertThat().statusCode(201)
//                .assertThat().body("message", equalTo(PASS_CHANGED_SUCCESSFULLY_MSG));
//
//    }

    @Test
    public void invalidOldPasswordNegativeTest() {
        String invalidOldPassword = "wrong-password123!";
        String requestBody = "{ \"oldPassword\": \"" + invalidOldPassword + "\", \"newPassword\": \"StrongPassword-123\" }";
        Response response = given()
                .cookie(cookie)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("users/password-change");
        Assert.assertEquals(response.getStatusCode(), 401);
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Invalid old password.");
        int status = response.jsonPath().getInt("status");
        Assert.assertEquals(status, 0);
    }


    @Test(dataProvider = "provideNotValidPassword", dataProviderClass = CSVDataProviders.class)
    public void validationErrorPasswordTest(String password) throws SQLException {
        user.setPasswordByEmail("lilu3@mail.com", password).then()
                .assertThat().statusCode(400);
    }
}

