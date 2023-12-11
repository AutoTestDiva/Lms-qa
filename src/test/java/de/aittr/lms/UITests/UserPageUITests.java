package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserPageUITests extends TestBaseUI{

    @BeforeMethod
    public void precondition(){
        app.getUserUI().loginWithData("student@mail.com", "Qwer123!");
        app.getUserUI().closeLoginMessage();
    }

    @Test
    public void verifyTheoryDisplayed(){
        app.getLesson().selectLessonByModuleByCohort("Cohort 34.2","basic_programming","lesson_30");
        app.getLesson().clickOnRUTheory(); // TODO fix
        Assert.assertTrue(app.getLesson().isTheoryContainsText("1. Введение в интерфейсы:"));
    }

    @Test
    public void verifyVideoDisplayed(){
        app.getLesson().selectLessonByModuleByCohort("Cohort 34.2", "basic_programming", "lesson_30");
        Assert.assertTrue(app.getLesson().isVideoDisplayed()); //TODO see video
    }



    @AfterMethod
    public void postCondition(){
        app.getUserUI().logOut();
    }

}
