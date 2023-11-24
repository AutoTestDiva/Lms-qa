package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


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
        click(By.xpath("//span[contains(text(),'Sign Out')]"));
    }

    public void clickOnUsersInSideBar() {
        click(By.cssSelector("[href='#/users']"));
    }

    public boolean isUserByRoleInTableDisplayed(String role) {
        return isElementPresent(By.xpath("//tbody/tr/td[2][contains(., '" + role + "')]"));
    }

    public void searchUser(String mail) {
        type(By.cssSelector("[placeholder='Search...']"), mail);
        pause(1000);
    }

    public String userOnFirstRow() {
        return getText(By.xpath("//tr[1]/td[1]"));
    }
}
