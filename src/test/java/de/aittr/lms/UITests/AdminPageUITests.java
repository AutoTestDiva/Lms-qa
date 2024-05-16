//package de.aittr.lms.UITests;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class AdminPageUITests extends TestBaseUI {
//    @BeforeMethod
//    public void precondition() {
//        app.getUserUI().loginWithData("a04@dev-lms.de", "LMS-dev-pass-2024");
//        app.getUserUI().closeLoginMessage();
//    }
//
//    @AfterMethod
//    public void postCondition() {
//        app.getUserUI().logOut();
//    }
//
//    @Test
//    public void isAdminTeacherStudentDisplayedTest() {
//        app.getUserUI().clickOnAdministrationOnMenu();
//        Assert.assertTrue(app.getUserUI().isUserByRoleInTableDisplayed("Students")
//                && app.getUserUI().isUserByRoleInTableDisplayed("Cohorts")
//                && app.getUserUI().isUserByRoleInTableDisplayed("Users"));
//    }
//
//    @Test
//    public void searchPanelStudent2Test() {
//        app.getUserUI().clickOnAdministrationOnMenu();
//        app.getUserUI().clickOnStudents();
//        Assert.assertEquals("s03@dev-lms.de", app.getUserUI().studentOnFirstRow());
//    }
//
//    @Test
//    public void searchPanelTeacherTest() {
//        app.getUserUI().clickOnAdministrationOnMenu();
//        app.getUserUI().clickOnUsers();
//        app.getUserUI().clickOnFieldSearchEndEnter("t03@dev-lms.de");
//        app.getUserUI().clickOnSearch();
//        Assert.assertEquals("t03@dev-lms.de", app.getUserUI().userOnFirstRow());
//    }
//
//    @Test
//    public void searchPanelCohortsTest() {
//        app.getUserUI().clickOnAdministrationOnMenu();
//        app.getUserUI().clickOnCohorts();
//        Assert.assertEquals("Cohort 30", app.getUserUI().cohortOnFirstRow());
//    }
//}
