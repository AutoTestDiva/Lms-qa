package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderHelper extends BaseHelperUI{
    public HeaderHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isSignInLinkPresent() {
        if(isElementPresent(By.cssSelector("[label='Sign in']"))){
            return true;
        }
        return false;
    }
}
