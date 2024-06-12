package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoToMyHomeWorkUITests2 extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a01@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @Test(dataProvider = "provideGoToMyHomeWorkData", dataProviderClass = CSVDataProviders.class)
    public void goToMyHomeWorkTest2(String group, String module, String lesson) {
        app.getCSVReaderUI().clickOnSelectYourGroup();
        app.getUserUI().selectMyGroup(group);
        app.getUserUI().clickOnMyGroup(group);
        app.getUserUI().isModulePresent();
        app.getUserUI().selectModule(module);
        app.getUserUI().clickOnSelectLesson();
        app.getUserUI().selectMyLesson(lesson);
        app.getUserUI().clickOnMyLesson(lesson);
        Assert.assertTrue(app.getCSVReaderUI().goToMyHomeWorkPresent());
    }

    @AfterMethod
    public void postCondition() {
        app.getUserUI().logOut();
    }
}

