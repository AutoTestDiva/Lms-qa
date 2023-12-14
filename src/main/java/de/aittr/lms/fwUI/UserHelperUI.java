package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
//        returnBack();
        pause(1000);
//        driver.navigate().back();
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

    public void clickOnSelectYourGroup() {
        click(By.xpath("//span[contains(text(),'Select your group')]"));
    }

    public void clickOnSelectedGroup(String group) {
        click(By.xpath("//*/text()[normalize-space(.)='" + group + "']/parent::*"));
    }

    public void clickOnSelectModule() {
        click(By.xpath("//span[contains(text(),'Select module')]"));
    }

    public void clickOnSelectedModule(String module) {
        click(By.xpath("//*/text()[normalize-space(.)='" + module + "']/parent::*"));
    }

    public void clickOnSelectLesson() {
        click(By.xpath("//span[contains(text(),'Select lesson')]"));
    }

    public void clickOnSelectedLesson(String lesson) {
        click(By.xpath("//*/text()[normalize-space(.)='" + lesson + "']/parent::*"));
    }

    public void clickOnVideoLine() {
        if(isElementPresent(By.xpath("//*/text()[normalize-space(.)='Video']/parent::*"))) {
            click(By.xpath("//*/text()[normalize-space(.)='Video']/parent::*"));
        }else {
            System.out.println("Элемента Video на вкладке урока нет");
        }
    }
    public List<WebElement> findVideoElements() {
        return driver.findElements(By.tagName("video"));
    }
//------------------------------------------------------------------
public List<WebElement> findElements() {
    return driver.findElements(By.xpath("//p-dropdownitem"));
}


    public List<WebElement> getDropdownListOfGroups(By dropdownLocator) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        List<WebElement> groups = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return groups;
    }

    public List<WebElement> getDropdownListOfModules(By dropdownLocator) {
        WebElement dropdown = driver.findElement(By.xpath("//span[contains(text(),'Select module')]"));
        List<WebElement> modules = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return modules;
    }
    public List<WebElement> getDropdownListOfLessons(By dropdownLocator) {
        WebElement dropdown = driver.findElement(By.xpath("//span[contains(text(),'Select lesson')]"));
        List<WebElement> lessons = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return lessons;
    }

    public void clickOnNextSelectedLesson() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/p-dropdown[1]/div[1]/div[2]/span[1]"));
    }
}