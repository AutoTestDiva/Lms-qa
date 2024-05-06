package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogOutUITests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a04@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }
    @Test
    public void LogOutTest() {
        app.getUserUI().logOut();
        Assert.assertTrue(app.getUserUI().isLogoutMessageDisplayed());
    }
}
