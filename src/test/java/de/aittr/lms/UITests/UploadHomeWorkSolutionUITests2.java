package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UploadHomeWorkSolutionUITests2 extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a01@dev-lms.de", "lms-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }
    @Test(dataProvider = "provideUploadHomeWorkSolutionData", dataProviderClass = CSVDataProviders.class)
    public void isUploadHomeWorkSolutionTest2(String group, String module, String lesson) {

        app.getUserUI().clickOnLessonsInSideBar();
        app.getCSVReaderUI().clickOnSelectYourGroup();

        app.getUserUI().selectMyGroup(group);
        app.getUserUI().clickOnMyGroup(group);

        app.getUserUI().isModulePresent();
        app.getUserUI().selectModule(module);

        app.getUserUI().clickOnSelectLesson();
        app.getUserUI().selectMyLesson(lesson);
        app.getUserUI().clickOnMyLesson(lesson);

        Assert.assertTrue(app.getCSVReaderUI().isUploadHomeWorkSolutionPresent());
    }
}
