package de.aittr.lms.fw;

import io.restassured.http.ContentType;
import org.openqa.selenium.WebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;

public class UserHelper extends BaseHelper{
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public UserHelper(){super();}

    public String loginBodyBuilder(String mail, String password){
//        return  "username=" + mail + "&password=" + password;
          return "hello";
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


}
