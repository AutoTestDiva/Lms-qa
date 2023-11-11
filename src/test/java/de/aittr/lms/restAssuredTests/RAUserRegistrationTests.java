package de.aittr.lms.restAssuredTests;

import de.aittr.lms.dto.NewUserDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class RAUserRegistrationTests extends TestBaseRA{

    private Cookie cookie;

    @BeforeMethod
    public void precondition(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
    }

    @Test
    public void registerUserByAdminPositiveTest(){
        //todo priority register -> setPassword -> logIn -> itsMe -> postCondDelete

        NewUserDto user = NewUserDto.builder()
                .cohort("Cohort21")
                .email("lilu@mail.com")
                .firstName("Lilu")
                .lastName("Test")
                .country("Germany")
                .phone("+490571234567")
                .build();

        given()
                .contentType(ContentType.JSON)
                .cookie(cookie)
                .body(user)
                .when()
                .post("/users")
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    public void registerUserWithoutAdminNegativeTest(){

        NewUserDto user = NewUserDto.builder()
                .cohort("Cohort21")
                .email("lilu@mail.com")
                .firstName("Lilu")
                .lastName("Test")
                .country("Germany")
                .phone("+490571234567")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .assertThat().statusCode(403);
    }

    @Test
    public void deleteTest() throws SQLException {
        int userId = user.getUserIdByEmail("lilu@mail.com");
        System.out.println(userId);
        user.deleteUserById(userId);
//        db.requestDelete("DELETE FROM student_cohort WHERE user_id = 6;");
    }

    //TODO deleteUser: delete connection cohort, confirmCode, then from account




}
