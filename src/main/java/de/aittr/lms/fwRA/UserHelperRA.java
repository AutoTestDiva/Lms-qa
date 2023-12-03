package de.aittr.lms.fwRA;

import de.aittr.lms.dto.NewUserDto;
import de.aittr.lms.dto.NewUserWithRoleDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class UserHelperRA extends BaseHelperRA {

    public UserHelperRA() {
    }

    public static String loginDataEncoded(String mail, String password) {
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

    public Response registerUser(String cohort, String email, String firstname, String lastname,
    String country, String phone){
        NewUserDto user = NewUserDto.builder()
                .cohort(cohort)
                .email(email)
                .firstName(firstname)
                .lastName(lastname)
                .country(country)
                .phone(phone)
                .build();

        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users");
    }

    public  Cookie getLoginCookie(String email, String password){
        Response response = given()
                .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .body(loginDataEncoded(email, password))
                .when()
                .post("/login");

        return response.getDetailedCookie("JSESSIONID");
    }

    public String getUserUuidByEmail(String email) {
        String userUuid;
        try{
            String userId = getUserIdByEmail(email);
            userUuid = db.requestSelect("SELECT uuid FROM confirmation_code WHERE user_id = " + userId + ";")
                    .getString(1);
        }catch (SQLException e){
            userUuid = null;
            System.out.println("User is not found.  " + e);
        }
        return userUuid; // TODO change getUserUuidByEmail
    }

    public String getUserIdByEmail(String email) throws SQLException {
        String userId;
        try{
            userId = db.requestSelect("SELECT id FROM account WHERE email = \"" + email + "\";")
                    .getString(1);
        } catch (SQLException e){
            userId = null;
            System.out.println("The user is not found" + e);
        }
        return userId;
    }

    public static Response loginUserRA(String email, String password) {
        return given()
                .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .body(loginDataEncoded(email, password))
                .when()
                .post("/login");
    }

    public Response setPasswordByEmail(String email, String password) throws SQLException {
        String userId = getUserIdByEmail(email);
        String userUuid = getUserUuidByEmail(email);
        System.out.println("**********" + userUuid + "************");
        return given().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"uuid\": \"" + userUuid + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}")
                .when()
                .post("/users/"  + userId +"/password");
    }

    private void deleteUserById(String userId) throws SQLException {

        db.requestDelete("DELETE FROM confirmation_code WHERE user_id = " + userId + ";");
        db.requestDelete("DELETE FROM student_cohort WHERE user_id = " + userId + ";");
        db.requestDelete("DELETE FROM account WHERE id = " + userId + ";");
    }

    public void deleteUser(String email) throws SQLException {
        String userId = getUserIdByEmail(email);
        if(userId != null){
            deleteUserById(userId);
        }
    }

    public static NewUserWithRoleDto userWithRoleBuilder (String cohort, String email, String firstName,
                                                          String lastName, String country, String role, String phone) {
        NewUserWithRoleDto user = NewUserWithRoleDto.builder()
                .cohort(cohort)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .country(country)
                .phone(phone)
                .role(role)
                .build();
        return user;
    }

    public static Response passwordRecovery(String mail) {
        return given().contentType(ContentType.JSON).body("{\"email\": \"" + mail + "\"}").when().post("/users/password-recovery");
    }


}
