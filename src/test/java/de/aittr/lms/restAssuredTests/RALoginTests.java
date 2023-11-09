package de.aittr.lms.restAssuredTests;

import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class LoginTests extends TestBaseRA{

//    @Test
//    public void tryToSelectById() throws SQLException {
//        String result = db.request("SELECT * FROM account WHERE id = 2;").getNString(4);
//        System.out.println(result);
//    }


    @Test
    public void loginAsAdmin() {
        given()
                .
    }

}
