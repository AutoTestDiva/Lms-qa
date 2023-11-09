package de.aittr.lms.restAssuredTests;

import de.aittr.lms.fwRA.UserHelperRA;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RALoginTests extends TestBaseRA{

//    @Test
//    public void tryToSelectById() throws SQLException {
//        String result = db.request("SELECT * FROM account WHERE id = 2;").getNString(4);
//        System.out.println(result);
//    }


    @Test
    public void loginAsAdmin() {

        given()
                .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .body(user.loginDataEncoded("admin@mail.com", "Admin123!"))
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void simpleTest(){

        System.out.println(user.loginDataEncoded("admin@mail.com", "Admin123!"));

    }

}
