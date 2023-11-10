package de.aittr.lms.restAssuredTests;

import org.testng.annotations.Test;

public class RALoginTests extends TestBaseRA{

    @Test
    public void loginAsAdmin() {
        user.loginUserRA("admin@mail.com", "Admin123!")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void loginAsUser() {
        user.loginUserRA("lilu2@mail.com", "Qwerty123!")
                .then()
                .assertThat().statusCode(200);
    }


    //TODO not valid data
    //TODO not exist user data

}
