package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortUsersInAdminCabinet extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
    }

    @Test
    public void isEmailSortUpPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().clickOnEmailSort();
        Assert.assertTrue(app.getUserUI().isUpperEmailPresent("admin@mail.com"));
    }
    @Test
    public void isEmailSortDownPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().clickOnEmailSort();
        app.getUserUI().clickOnEmailSort();
        Assert.assertTrue(app.getUserUI().isDownEmailPresent("admin@mail.com"));
    }

    @Test
    public void isRoleSortUpPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().clickOnRoleSort();
        Assert.assertTrue(app.getUserUI().isUpperRolePresent("ADMIN"));
    }
    @Test
    public void isRoleSortDownPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().clickOnRoleSort();
        app.getUserUI().clickOnRoleSort();
        Assert.assertTrue(app.getUserUI().isDownRolePresent("ADMIN"));
    }

    @Test
    public void isStateSortUpPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().pause(5000);
        app.getUserUI().clickOnStateSort();
        Assert.assertTrue(app.getUserUI().isUpperStatePresent("CONFIRMED"));
    }
    @Test
    public void isStateSortDownPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().pause(5000);
        app.getUserUI().clickOnStateSort();
        app.getUserUI().clickOnStateSort();
        Assert.assertTrue(app.getUserUI().isDownStatePresent("CONFIRMED"));
    }


    @Test
    public void isPrimaryGroupSortUpPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().pause(5000);
        app.getUserUI().clickOnPrimaryGroupSort();
        Assert.assertTrue(app.getUserUI().isUpperPrimaryGroupPresent(""));
    }
    @Test
    public void isPrimaryGroupSortDownPositiveTest() {
        app.getUserUI().clickOnUsersInSideBar();
        app.getUserUI().pause(5000);
        app.getUserUI().clickOnPrimaryGroupSort();
        app.getUserUI().clickOnPrimaryGroupSort();
        Assert.assertTrue(app.getUserUI().isDownPrimaryGroupPresent(""));
    }


    @AfterMethod
    public void postCondition(){
        app.getUserUI().logOut();
    }
}

