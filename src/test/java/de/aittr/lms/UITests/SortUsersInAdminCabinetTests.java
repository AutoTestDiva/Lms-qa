package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortUsersInAdminCabinetTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a04@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @AfterMethod
    public void postCondition() {
        app.getUserUI().logOut();
    }

    @Test
    public void isEmailSortUpPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnEmailSort();
        Assert.assertTrue(app.getUserUI().isUpperEmailPresent("a01@dev-lms.de"));
    }

    @Test
    public void isEmailSortDownPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnEmailSort();
        app.getUserUI().clickOnEmailSort();
        Assert.assertTrue(app.getUserUI().isDownEmailPresent("teacher@mal.com"));
    }

    @Test
    public void isRoleSortUpPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnRoleSort();
        Assert.assertTrue(app.getUserUI().isUpperRolePresent("ADMIN"));
    }

    @Test
    public void isRoleSortDownPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnRoleSort();
        app.getUserUI().clickOnRoleSort();
        Assert.assertTrue(app.getUserUI().isDownRolePresent("TEACHER"));
    }

    @Test
    public void isStateSortUpPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnStateSort();
        Assert.assertTrue(app.getUserUI().isUpperStatePresent("CONFIRMED"));
    }

    @Test
    public void isStateSortDownPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnStateSort();
        app.getUserUI().clickOnStateSort();
        Assert.assertTrue(app.getUserUI().isDownStatePresent("NOT_CONFIRMED"));
    }


    @Test
    public void isPrimaryGroupSortUpPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnPrimaryGroupSort();
        Assert.assertTrue(app.getUserUI().isUpperPrimaryGroupPresent(""));
    }

    @Test
    public void isPrimaryGroupSortDownPositiveTest() {
        app.getUserUI().clickOnAdministrationOnMenu();
        app.getUserUI().clickOnUsers();
        app.getUserUI().clickOnPrimaryGroupSort();
        app.getUserUI().clickOnPrimaryGroupSort();
        Assert.assertTrue(app.getUserUI().isDownPrimaryGroupPresent("Cohort 99"));
    }
}
