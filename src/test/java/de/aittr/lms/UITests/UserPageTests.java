package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserPageTests extends TestBaseUI{

    @BeforeMethod
    public void precondition(){
        app.getUserUI().loginWithData("student@mail.com", "Qwer123!");
    }

    @Test
    public void verifyTheoryDisplayed(){
        app.getLesson().selectStudentGroup("Cohort 34.2");
        app.getLesson().selectModule("basic_programming");
        app.getLesson().selectLesson("lesson_30");
        app.getLesson().clickOnTheory();
        app.getLesson().selectRULanguage();
        Assert.assertTrue(app.getLesson().isTheoryContainsText("1. Введение в интерфейсы:"));
                //[header="Home work"]
    }

    @Test
    public void verifyVideoDisplayed(){
        app.getLesson().selectStudentGroup("Cohort 34.2");
        app.getLesson().selectModule("basic_programming");
        app.getLesson().selectLesson("lesson_30");
//        app.getLesson(). TODO see video
    }

    @AfterMethod
    public void postCondition(){
        app.getUserUI().logOut();
    }

}
