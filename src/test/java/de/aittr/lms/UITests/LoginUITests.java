package de.aittr.lms.UITests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUITests extends TestBaseUI{

    @Test
    public void loginAsAdminPositiveTest(){
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
        app.getUserUI().clickOnUserList();
        Assert.assertTrue(app.getUserUI().isManageHidderExist());
    }

        @Test
    public void loginAsTeacherPositiveTest(){
        // TODO loginAsTeacherPositiveTest
            app.getUserUI().loginWithData("teacher@mail.com", "Qwerty123!");
            //  Click on select
            // verify I see many groups
//            Assert.assertTrue(app.getUserUI().);
        }

        @Test
    public void loginAsStudentPositiveTest(){
        // TODO loginAsStudentPositiveTest
            app.getUserUI().loginWithData("student@mail.com", "Qwerty123!");
            //  Click on select
            // verify I see only "Cohort 34.2"
//            Assert.assertTrue(app.getUserUI().);
    }

        @Test
    public void loginAsNotExistStudentNegativeTest(){
        // TODO loginAsNotExistStudentNegativeTest
    }

        @Test
    public void loginAsStudentWithWrongFormatEmailNegativeTest(){
        // TODO loginAsStudentWithWrongFormatEmailNegativeTest
    }

        @Test
    public void loginAsStudentWithPasswordNegativeTest(){
        // TODO loginAsStudentWithPasswordNegativeTest
    }




}
