package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUITests extends TestBaseUI{

    @Test(groups = "positive")
    public void loginAsAdminPositiveTest(){
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
        app.getUserUI().clickOnUserList();
        Assert.assertTrue(app.getUserUI().isManageHidderExist());
        app.getUserUI().logOut();
    }

        @Test(groups = "positive")
    public void loginAsTeacherPositiveTest(){
            app.getUserUI().loginWithData("teacher@mail.com", "Qwerty123!");
            Assert.assertTrue(app.getGroupUI().isCohortInSelectPresent("Cohort 23")
                    && app.getGroupUI().isCohortInSelectPresent("Cohort 24")
                    && app.getGroupUI().isCohortInSelectPresent("Cohort 35"));
            app.getUserUI().logOut();
        }

        @Test(groups = "positive")
    public void loginAsStudentPositiveTest(){
            app.getUserUI().loginWithData("student@mail.com","Qwerty123!");
            Assert.assertTrue(app.getGroupUI().isCohortInSelectPresent("Cohort 34.2"));
            app.getUserUI().logOut();
    }

    @Test(groups = "positive")
    public void loginAsStudent2PositiveTest(){
        app.getUserUI().loginWithData("student2@mail.com","Qwerty123!");
        Assert.assertTrue(app.getGroupUI().isCohortInSelectPresent("Cohort 35"));
        app.getUserUI().logOut();
    }

        @Test(groups = "negative")
    public void loginAsNotExistStudentNegativeTest(){
        app.getUserUI().loginWithData("student3@mail.com","Qwerty123!");
        Assert.assertTrue(app.getHeaderUI().isErrorNotValidEmailOrPasswordDisplayed());
    }

        @Test
    public void loginAsStudentWithWrongFormatEmailNegativeTest(){
            app.getUserUI().loginWithData("student.mail.com","Qwerty123!");
            Assert.assertTrue(app.getHeaderUI().isErrorNotValidEmailFormatDisplayed());
    }

        @Test
    public void loginAsStudentWithNotValidPasswordNegativeTest(){
            app.getUserUI().loginWithData("student@mail.com","Qwerty111!");
            Assert.assertTrue(app.getHeaderUI().isErrorNotValidEmailOrPasswordDisplayed());
    }

//    @AfterMethod(groups = "positive") TODO refactoring
//    public void postCondition(){
//            app.getUserUI().logOut();
//    }

}


