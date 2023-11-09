package de.aittr.lms.fwRA;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;

public class UserHelperRA extends BaseHelperRA {

    public UserHelperRA() {
    }

    public void loginUserRA(String email, String password){
        given()
                .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .body("username=" + email + "&password=" + password)
                .when()
                .post("/login");
    }

    public String loginDataEncoded(String mail, String password) {
        String encodedMail;
        String encodedPassword;

        try {
            encodedMail = URLEncoder.encode(mail, "UTF-8");
            encodedPassword = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return "username=" + encodedMail + "&password=" + encodedPassword;

    }

    public  Cookie getLoginCookie(String email, String password){
        Response response = given()
                .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .body(loginDataEncoded(email, password))
                .when()
                .post("/login");

        return response.getDetailedCookie("JSESSIONID");
    }

}
