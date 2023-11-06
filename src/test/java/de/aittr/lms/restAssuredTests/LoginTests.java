package de.aittr.lms.restAssuredTests;

import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class LoginTests extends TestBaseRA{

    @Test
    public void tryToSelect(){
        //        String result = db.request("SELECT * FROM account;").toString();
//        System.out.println(result);
    }


    @Test
    public void loginAsAdmin() {
        System.out.println("HELLO");
    }

}
