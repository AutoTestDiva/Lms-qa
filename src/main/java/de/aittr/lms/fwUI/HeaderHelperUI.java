package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderHelperUI extends BaseHelperUI{
    public HeaderHelperUI(WebDriver driver) {
        super(driver);
    }

    public boolean isSignInLinkPresent() {
        if(isElementPresent(By.cssSelector("[label='Sign in']"))){
            return true;
        }
        return false;
    }

    public boolean isErrorNotValidEmailOrPasswordDisplayed() {
        if(isElementPresent(By.xpath("//div[contains(., 'Invalid login or password')]"))){
            closeErrorMessageAndReturn();
            return true;
        }
        return false;

    }

    public boolean isErrorNotValidEmailFormatDisplayed() {
        if(isElementPresent(By.xpath("//app-input-error-message/div[contains(., ' Invalid email format ')]"))){
            returnBack();
            return true;
        }
        return false;

    }

    public void closeErrorMessageAndReturn() {
        click(By.className("error-message-close"));
        returnBack();
        pause(1000);
//        driver.navigate().back();
    }
}
