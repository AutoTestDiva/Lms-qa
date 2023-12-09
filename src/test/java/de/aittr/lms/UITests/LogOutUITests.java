package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogOutUITests extends TestBaseUI{

    @BeforeMethod
    public void precondition(){
        app.getUserUI().loginWithData("student@mail.com", "Qwer123!");
    }

    @Test
    public void LogOutTest(){
        app.getUserUI().logOut();
        Assert.assertTrue(app.getHeaderUI().isSignInLinkPresent());
    }

}
