package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;


public class UploadYourHomeworkTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a01@dev-lms.de", "LMS-dev-pass-2024");
        app.getCSVReaderUI().scrollPageUp();
        app.getUserUI().closeLoginMessage();
    }

    @Test(dataProvider = "provideUploadYourHomeworkData", dataProviderClass = CSVDataProviders.class)
    public void isUploadHomeWorkSolutionUITest2(String group, String module, String lesson) {
        app.getCSVReaderUI().clickOnSelectYourGroup();
        app.getCSVReaderUI().selectMyGroup(group);
        app.getUserUI().clickOnMyGroup(group);
        app.getCSVReaderUI().isModulePresent();
        app.getUserUI().selectModule(module);
        app.getCSVReaderUI().clickOnSelectLesson();
        app.getCSVReaderUI().selectMyLesson(lesson);
        app.getUserUI().clickOnMyLesson(lesson);
        app.getCSVReaderUI().clickGoToMyHomeWork();
        Assert.assertTrue(app.getCSVReaderUI().isUploadYourHomeworkPresent());
    }

    @Test(dataProvider = "provideUploadYourHomeworkData", dataProviderClass = CSVDataProviders.class)
    public void isSelectDirectoryToUploadFilesPresentUITest(String group, String module, String lesson) {
        app.getCSVReaderUI().clickOnSelectYourGroup();
        app.getCSVReaderUI().selectMyGroup(group);
        app.getUserUI().clickOnMyGroup(group);
        app.getCSVReaderUI().isModulePresent();
        app.getUserUI().selectModule(module);
        app.getCSVReaderUI().clickOnSelectLesson();
        app.getCSVReaderUI().selectMyLesson(lesson);
        app.getUserUI().clickOnMyLesson(lesson);
        app.getCSVReaderUI().clickGoToMyHomeWork();
        app.getCSVReaderUI().clickOnUploadYourHomework();
        Assert.assertTrue(app.getCSVReaderUI().isSelectDirectoryToUploadPresent());
        app.getCSVReaderUI().clickOnCloseButtonInUploadHomework();
    }

    @Test(dataProvider = "provideUploadYourHomeworkData", dataProviderClass = CSVDataProviders.class)
    public void isClearSelectionPresentUITest(String group, String module, String lesson) {
        app.getCSVReaderUI().clickOnSelectYourGroup();
        app.getCSVReaderUI().selectMyGroup(group);
        app.getUserUI().clickOnMyGroup(group);
        app.getCSVReaderUI().isModulePresent();
        app.getUserUI().selectModule(module);
        app.getCSVReaderUI().clickOnSelectLesson();
        app.getCSVReaderUI().selectMyLesson(lesson);
        app.getUserUI().clickOnMyLesson(lesson);
        app.getCSVReaderUI().clickGoToMyHomeWork();
        app.getCSVReaderUI().clickOnUploadYourHomework();
        Assert.assertTrue(app.getCSVReaderUI().isClearSelectionPresent());
        app.getCSVReaderUI().clickOnCloseButtonInUploadHomework();
    }

    @Test(dataProvider = "provideUploadYourHomeworkData", dataProviderClass = CSVDataProviders.class)
    public void isSelectedFilesToUploadUITest(String group, String module, String lesson) throws InterruptedException, AWTException {
        app.getCSVReaderUI().clickOnSelectYourGroup();
        app.getCSVReaderUI().selectMyGroup(group);
        app.getUserUI().clickOnMyGroup(group);
        app.getCSVReaderUI().isModulePresent();
        app.getUserUI().selectModule(module);
        app.getCSVReaderUI().clickOnSelectLesson();
        app.getCSVReaderUI().selectMyLesson(lesson);
        app.getUserUI().clickOnMyLesson(lesson);
        app.getCSVReaderUI().clickGoToMyHomeWork();
        app.getCSVReaderUI().clickOnUploadYourHomework();
        app.getCSVReaderUI().clickOnSelectFilesToUpload();
        app.getCSVReaderUI().uploadFile_OnSelectFilesToUpload();
        Assert.assertTrue(app.getCSVReaderUI().isTotalFilesToUploadPresent());
        app.getCSVReaderUI().clickOnClearSelection();
        app.getUserUI().pause(1000);
        app.getCSVReaderUI().clickOnCloseButtonInUploadHomework();
    }

    @Test(dataProvider = "provideUploadYourHomeworkData", dataProviderClass = CSVDataProviders.class)
    public void isSelectedDirectoryToUploadUITest(String group, String module, String lesson) throws InterruptedException, AWTException {
        app.getCSVReaderUI().clickOnSelectYourGroup();
        app.getCSVReaderUI().selectMyGroup(group);
        app.getUserUI().clickOnMyGroup(group);
        app.getCSVReaderUI().isModulePresent();
        app.getUserUI().selectModule(module);
        app.getCSVReaderUI().clickOnSelectLesson();
        app.getCSVReaderUI().selectMyLesson(lesson);
        app.getUserUI().clickOnMyLesson(lesson);
        app.getCSVReaderUI().clickGoToMyHomeWork();
        app.getCSVReaderUI().clickOnUploadYourHomework();
        app.getCSVReaderUI().clickOnSelectDirectoryToUpload();
        app.getUserUI().pause(1000);
        app.getCSVReaderUI().uploadDirectoryOn_SelectDirectoryToUpload();
        app.getUserUI().pause(1000);
        Assert.assertTrue(app.getCSVReaderUI().isTotalFilesToUploadPresent());
        app.getCSVReaderUI().clickOnClearSelection();
        app.getUserUI().pause(1000);
        app.getCSVReaderUI().clickOnCloseButtonInUploadHomework();
    }

    @AfterMethod
    public void postCondition() {
        app.getUserUI().pause(1000);
        app.getUserUI().logOut();

    }
}
