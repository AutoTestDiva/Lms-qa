package de.aittr.lms.fwUI;

 import org.openqa.selenium.By;
 import org.openqa.selenium.JavascriptExecutor;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.WebDriverWait;

 import java.time.Duration;
 import java.util.List;

public class UserHelperUI extends BaseHelperUI {
     public UserHelperUI(WebDriver driver) {
         super(driver);
     }

     public void loginWithData(String mail, String password) {
         click(By.cssSelector("[label='Sign in']")); //click on Sign in button
         type(By.id("email-login-page"), mail);
         type(By.cssSelector("[type='password']"), password);
         click(By.cssSelector("[label='Sign In']"));
     }

     public void clickOnUserList() {
         click(By.xpath("//span[contains(text(),'Users')]"));
     }

     public boolean isManageHidderExist() {
         return (isElementPresent(By.xpath("//h5[contains(text(),'Manage Users')]")));
     }

     public void logOut() {
         click(By.cssSelector(".pi-user"));
         pause(1000);
         click(By.xpath("//span[contains(text(),'Sign Out')]"));
         closeLogoutMessage();
     }

     public void clickOnUsersInSideBar() {
         click(By.cssSelector("[href='#/users']"));
     }

     public boolean isUserByRoleInTableDisplayed(String role) {
         return isElementPresent(By.xpath("//tbody/tr/td[2][contains(., '" + role + "')]"));
     }

     public void searchUser(String mail) {
         pause(3000);
         type(By.cssSelector("[placeholder='Search...']"), mail);
     }

     public String userOnFirstRow() {
         pause(1000);
         String text = getText(By.xpath("//tr[1]/td[1]"));
         System.out.println("****" + text + "****");
         return text;
     }

     public void clickOnEmailSort() {
         click(By.xpath("//thead/tr[1]/th[1]/p-sorticon[1]/i[1]"));
     }

     public boolean isUpperEmailPresent(String email1) {
         return isElementPresent(By.xpath("//tbody/tr[1]/td[1][contains(., '" + email1 + "')]"));
     }

     public boolean isDownEmailPresent(String email2) {
         return isElementPresent(By.xpath("//tbody/tr[4]/td[1][contains(., '" + email2 + "')]"));
     }

     public void clickOnRoleSort() {
         click(By.xpath("//thead/tr[1]/th[2]/p-sorticon[1]/i[1]"));
     }

     public boolean isUpperRolePresent(String admin1) {
         return isElementPresent(By.xpath("//tbody/tr[1]/td[2][contains(., '" + admin1 + "')]"));
     }

     public boolean isDownRolePresent(String admin2) {
         return isElementPresent(By.xpath("//tbody/tr[4]/td[2][contains(., '" + admin2 + "')]"));
     }

     public void clickOnStateSort() {
         click(By.xpath("//thead/tr[1]/th[3]/p-sorticon[1]/i[1]"));
     }

     public boolean isUpperStatePresent(String confirmed1) {
         return isElementPresent(By.xpath("//tbody/tr[1]/td[3][contains(., '" + confirmed1 + "')]"));
     }

     public boolean isDownStatePresent(String confirmed2) {
         return isElementPresent(By.xpath("//tbody/tr[4]/td[3][contains(., '" + confirmed2 + "')]"));
     }

     public void clickOnPrimaryGroupSort() {
         click(By.xpath("//thead/tr[1]/th[4]/p-sorticon[1]/i[1]"));
     }

     public boolean isUpperPrimaryGroupPresent(String primaryGroup1) {
         return isElementPresent(By.xpath("//tbody/tr[1]/td[4][contains(., '" + primaryGroup1 + "')]"));
     }

     public boolean isDownPrimaryGroupPresent(String primaryGroup2) {
         return isElementPresent(By.xpath("//tbody/tr[4]/td[4][contains(., '" + primaryGroup2 + "')]"));
     }

     public void closeLoginMessage() {
         click(By.xpath("//div[contains(., 'Login is succes')]/button"));
     }

     public void closeLogoutMessage() {
         click(By.xpath("//div[contains(., 'Sing out')]/button"));
     }

     public boolean isLogoutMessageDisplayed() {
         if (isElementPresent(By.xpath("//div[contains(., 'Sing out')]/button"))) {
             click(By.xpath("//div[contains(., 'Sing out')]/button"));
             return true;
         }
        return false;
    }

    public boolean isErrorNotValidEmailOrPasswordMessageDisplayed() {
        if (isElementPresent(By.xpath("//div[contains(., 'Invalid login or password')]"))) {
            closeErrorNotValidEmailOrPasswordMessage();
            returnBack();
            return true;
        }
        return false;
    }

    public void closeErrorNotValidEmailOrPasswordMessage() {
        click(By.xpath("//div[contains(., 'Invalid login or password')]/button"));
        pause(1000);
    }

    public boolean isErrorNotValidEmailFormatDisplayed() {
        if (isElementPresent(
                By.xpath("//app-input-error-message/div[contains(., ' Invalid email format ')]"))) {
            returnBack();
            return true;
        }
        return false;
    }

    public void clickOnLessonsInSideBar() {
        click(By.xpath("//span[contains(text(),'Lessons')]"));
    }
}