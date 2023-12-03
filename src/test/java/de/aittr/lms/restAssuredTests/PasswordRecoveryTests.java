package de.aittr.lms.restAssuredTests;

import org.testng.annotations.Test;


public class PasswordRecoveryTests extends TestBaseRA {

    @Test
    public void passwordRecoveryPositiveTest(){
        user.passwordRecovery("student@mail.com").then()
                .assertThat().statusCode(201);
    }

    @Test
    public void passwordRecoveryWithNotValidEmailNegativeTest(){
        user.passwordRecovery("student.mail.com").then()
                .assertThat().statusCode(400);
    }

    @Test
    public void passwordRecoveryNotExistedUserNegativeTest(){
        user.passwordRecovery("example@mail.com").then()
                .assertThat().statusCode(404);
    }

}
