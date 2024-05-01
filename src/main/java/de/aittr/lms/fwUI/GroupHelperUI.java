package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelperUI extends BaseHelperUI{

    public GroupHelperUI(WebDriver driver) {
        super(driver);
    }

    public boolean isTeacherCabinetPresent() {
        return  (isElementPresent(By.xpath("//a[contains(text(),'Teacher cabinet')]")));

    }

    public boolean isAdministrationInHeaderPresent() {
        return  (isElementPresent(By.xpath("//button[contains(text(),'Administration')]")));
    }
}
