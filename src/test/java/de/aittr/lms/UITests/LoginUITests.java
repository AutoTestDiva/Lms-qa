package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginUITests extends TestBaseUI {
    @Test(groups = "positive")
    public void loginAsAdminPositiveTest() {
        app.getUserUI().loginWithData("a01@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
        Assert.assertTrue(app.getGroupUI().isAdministrationInHeaderPresent());
    }

    @Test(groups = "positive")
    public void loginAsTeacherPositiveTest() {
        app.getUserUI().loginWithData("t03@dev-lms.de", "LMS-dev-pass-2024");
        app.getCSVReaderUI().scrollPageUp();
        app.getUserUI().closeLoginMessage();
        Assert.assertTrue(app.getGroupUI().isTeacherCabinetPresent());
    }

    @Test(groups = "negative")
    public void loginAsNotExistStudentNegativeTest() {
        app.getUserUI().loginWithData("test@dev-lms.de", "LMS-dev-pass-2024");
        Assert.assertTrue(app.getUserUI().isErrorNotValidEmailOrPasswordMessageDisplayed());
        //1 part of aftermethod
        app.getUserUI().loginWithData("a01@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @Test
    public void loginAsStudentWithWrongFormatEmailNegativeTest() {
        app.getUserUI().loginWithWrongData("s03.dev-lms.de", "LMS-dev-pass-2024");
        Assert.assertTrue(app.getUserUI().isErrorNotValidEmailFormatDisplayed());
        //1 part of aftermethod
        app.getUserUI().loginWithData("a01@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @Test
    public void loginAsStudentWithNotValidPasswordNegativeTest() {
        app.getUserUI().loginWithData("s03@dev-lms.de", "TEST-dev-pass-2024");
        app.getUserUI().pause(500);
        Assert.assertTrue(app.getUserUI().isErrorNotValidEmailOrPasswordMessageDisplayed());
        //1 part of aftermethod
        app.getUserUI().loginWithData("a01@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @AfterMethod
    public void postCondition() {
        app.getUserUI().pause(500);
        app.getUserUI().logOut();
    }
}


