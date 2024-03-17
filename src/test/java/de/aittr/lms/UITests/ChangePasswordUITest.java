package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordUITest extends TestBaseUI{

    @Test
    public void changePasswordNegativeUITest_withWrongEmail(){
        app.getUserUI().enterEmailData("s01@dev-lms.deaaaaa");
        app.getUserUI().clickOnForgotPassword("s01@dev-lms.deaaaaa");
        app.getUserUI().pause(1000);
        Assert.assertTrue(app.getUserUI().isErrorPresent());
    }

}
