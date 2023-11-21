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
        if(isElementPresent(By.xpath("//h5[contains(text(),'Manage Users')]"))){
            return true;
        }
        return false;
    }

    public void logOut() {
        click(By.cssSelector("button.p-element.p-button.p-component:nth-child(2)"));
        click(By.xpath("//span[contains(text(),'Sign Out')]"));
    }
}
