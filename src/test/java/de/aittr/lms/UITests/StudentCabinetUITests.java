package de.aittr.lms.UITests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentCabinetUITests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("s03@dev-lms.de", "LMS-dev-pass-2024");
        app.getCSVReaderUI().scrollPageUp();
        app.getUserUI().closeLoginMessage();
        app.getCSVReaderUI().scrollPageUp();
    }

    @Test
    public void verifyTextInPlanIn_StudentCabinetUITest() {
        app.getUserUI().clickOnStudentCabinetOnMenu();
        app.getUserUI().clickOnLessonsInStudentCabinet();
        app.getLesson().selectLessonByModuleByCohort("Cohort 30", "Back End", "Lesson 01");
        app.getUserUI().pause(1000);
        app.getCSVReaderUI().clickOnPlanLine();
        Assert.assertTrue(app.getCSVReaderUI().isTextPresent());
    }

    @Test
    public void verifyPresenceVideoIn_StudentCabinetUITest() {
        app.getUserUI().clickOnStudentCabinetOnMenu();
        app.getUserUI().clickOnLessonsInStudentCabinet();
        app.getLesson().selectLessonByModuleByCohort("Cohort 30", "Back End", "Lesson 01");
        app.getUserUI().pause(1000);
        app.getCSVReaderUI().scrollElementDown(By.xpath("//button[@id='video-toggle']"));
        app.getCSVReaderUI().clickOnVideoLine();
        Assert.assertTrue(app.getCSVReaderUI().isVideoElementsPresent());
        app.getCSVReaderUI().pause(1000);
        app.getCSVReaderUI().clickOnVideoLine();
        app.getCSVReaderUI().scrollPageUp();
    }
       @AfterMethod
    public void postCondition() {
        app.getUserUI().logOut();
    }
}
