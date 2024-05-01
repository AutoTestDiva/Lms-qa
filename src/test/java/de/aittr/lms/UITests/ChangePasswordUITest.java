package de.aittr.lms.UITests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordUITest extends TestBaseUI {
    @Test
    public void changePasswordUITest_withWrongPassword() {
        app.getUserUI().loginWithData("s03@dev-lms.de", "ERROR-dev-pass-2024");
        app.getUserUI().pause(3000);
        app.getCSVReaderUI().scrollElementDown(By.xpath("//a[contains(text(),'Forgot password?')]"));
        app.getUserUI().clickOnForgotPassword("s03@dev-lms.de");
        app.getUserUI().pause(1000);
        Assert.assertTrue(app.getUserUI().isResetPasswordPresent());
        app.getUserUI().clickOnGoToMainPage();
    }
}
