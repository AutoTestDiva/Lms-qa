package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VideoUITests extends TestBaseUI{

    @BeforeClass
    public void precondition(){
        app.getUserUI().loginWithData("teacher@mail.com", "Qwer123!");
        app.getUserUI().closeLoginMessage();
    }

    @AfterClass
    public void postCondition(){
        app.getUserUI().logOut();
    }

    @Test
    public void getVideoCohort342BasProgLess30TestPositive(){
        app.getLesson().selectLessonByModuleByCohort(
                "Cohort 34.2","basic_programming","lesson_30");
        Assert.assertTrue(app.getLesson().isAllVideoInLessonEnabled());

    }

    @Test
    public void getVideoCohort312Frontend01Test(){
        app.getLesson().selectLessonByModuleByCohort(
                "Cohort 31.2","front_end","lesson_01"); // 2 video, 1 exist
        Assert.assertTrue(app.getLesson().isAllVideoInLessonEnabled());
    }

    @Test
    public void verifyVideoDisplayed2(){
        app.getLesson().selectLessonByModuleByCohort(
                "Cohort 34.2","linux_git","lesson_03");
        Assert.assertTrue(app.getLesson().isAllVideoInLessonEnabled());
    }

    @Test(dataProvider = "provideGetVideoData", dataProviderClass = CSVDataProviders.class)
    public void verifyAllVideoDisplayedByTeacher(String cohort, String module, String lesson){
        app.getLesson().selectLessonByModuleByCohort(
                "Cohort " + cohort,module,"lesson_" + lesson);
        Assert.assertTrue(app.getLesson().isAllVideoInLessonEnabled());
    }

}
