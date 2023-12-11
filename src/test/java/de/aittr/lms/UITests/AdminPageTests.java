package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminPageTests extends TestBaseUI {

    @BeforeMethod
    public void precondition(){
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
    }

    @Test
    public void isAdminTeacherStudentDisplayedTest(){
        app.getUserUI().clickOnUsersInSideBar();
        Assert.assertTrue(app.getUserUI().isUserByRoleInTableDisplayed("ADMIN")
                            && app.getUserUI().isUserByRoleInTableDisplayed("TEACHER")
                            && app.getUserUI().isUserByRoleInTableDisplayed("STUDENT"));
    }

    @Test
    public void searchPanelStudent2Test(){
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().searchUser("student2@mail.com");
        Assert.assertEquals("student2@mail.com", app.getUserUI().userOnFirstRow());
    }

    @Test
    public void searchPanelTeacherTest(){
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().searchUser("teacher@mail.com");
        Assert.assertEquals("teacher@mail.com", app.getUserUI().userOnFirstRow());

    }

    @Test
    public void searchPanelStudentTest(){
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().searchUser("student@mail.com");
        Assert.assertEquals("student@mail.com", app.getUserUI().userOnFirstRow());
    }

    @AfterMethod
    public void postCondition(){
        app.getUserUI().logOut();
    }
}
