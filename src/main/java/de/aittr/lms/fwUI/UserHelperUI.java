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
        pause(1000);
        click(By.xpath("//button[contains(text(),'Login')]")); //click on Sign in button
        type(By.id("email-login-page"), mail);
        type(By.cssSelector("[type='password']"), password);
        clickWithScroll(By.xpath("//span[@class='p-button-label' and text()='Sign In']"));
        scrollPageUp();
    }
    public void clickWithScroll(By locator) {
        // Прокрутка вниз на 500 пикселей (можете настроить значение по вашему усмотрению)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Выполнение клика
        driver.findElement(locator).click();
    }
   public void logOut() {
        pause(1000);
       scrollPageUp();
        click(By.cssSelector(".d-inline-block.ng-star-inserted.dropdown"));
        pause(1000);
        click(By.xpath("//button[contains(text(),'SignOut')]"));
        closeLogoutMessage();
    }

    public void clickOnAdministrationOnMenu() {
        pause(1000);
           click(By.xpath("//button[contains(text(),'Administration')]"));
    }

    public boolean isUserByRoleInTableDisplayed(String role) {
        return isElementPresent(By.xpath("//button[contains(text(),'" + role + "')]"));
    }
    public String userOnFirstRow() {
        pause(1000);
        String text = getText(By.xpath("//tbody/tr[1]/td[1]"));
        return text;
    }

    public void clickOnEmailSort() {
        click(By.xpath("//thead/tr[1]/th[1]"));
    }

    public boolean isUpperEmailPresent(String email1) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[1][contains(., '" + email1 + "')]"));
    }

    public boolean isDownEmailPresent(String email2) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[1][contains(., '" + email2 + "')]"));
    }

    public void clickOnRoleSort() {
        click(By.xpath("//thead/tr[1]/th[2]"));
    }

    public boolean isUpperRolePresent(String admin1) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[2][contains(., '" + admin1 + "')]"));
    }

    public boolean isDownRolePresent(String admin2) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[2][contains(., '" + admin2 + "')]"));
    }

    public void clickOnStateSort() {
        click(By.xpath("//thead/tr[1]/th[3]"));
    }

    public boolean isUpperStatePresent(String confirmed1) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[3][contains(., '" + confirmed1 + "')]"));
    }

    public boolean isDownStatePresent(String confirmed2) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[3][contains(., '" + confirmed2 + "')]"));
    }

    public void clickOnPrimaryGroupSort() {
        click(By.xpath("//thead/tr[1]/th[4]"));
    }

    public boolean isUpperPrimaryGroupPresent(String primaryGroup1) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[4][contains(., '" + primaryGroup1 + "')]"));
    }

    public boolean isDownPrimaryGroupPresent(String primaryGroup2) {
        return isElementPresent(By.xpath("//tbody/tr[1]/td[4][contains(., '" + primaryGroup2 + "')]"));
    }

    public void closeLoginMessage() {
       click(By.cssSelector(".p-icon.p-toast-icon-close-icon"));
       pause(500);
        scrollPageUp();
    }

    public void closeLogoutMessage() {
        click(By.cssSelector(".p-icon.p-toast-icon-close-icon"));
    }

    public boolean isLogoutMessageDisplayed() {
        if (isElementPresent(By.xpath("//div[contains(text(),'Success')]"))) {
            click(By.xpath("//body/app-root[1]/p-toast[1]/div[1]/p-toastitem[1]/div[1]/div[1]/button[1]/timesicon[1]/*[1]"));
            return true;
        }
        return false;
    }

    public boolean isErrorNotValidEmailOrPasswordMessageDisplayed() {
        if (isElementPresent(By.xpath("//div[contains(text(),'Error')]"))) {
            closeErrorNotValidEmailOrPasswordMessage();
            returnBack();
            return true;
        }
        return false;
    }

    public void closeErrorNotValidEmailOrPasswordMessage() {
        click(By.xpath("//body/app-root[1]/p-toast[1]/div[1]/p-toastitem[1]/div[1]/div[1]/button[1]/timesicon[1]/*[1]"));
        pause(1000);
    }

    public boolean isErrorNotValidEmailFormatDisplayed() {
        if (isElementPresent(
                By.xpath("//div[contains(text(),'Invalid email format')]"))) {
            returnBack();
            return true;
        }
        return false;
    }

        public void clickOnSelectYourGroup() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public void clickOnSelectedGroup(String group) {
        click(By.xpath("//*/text()[normalize-space(.)='" + group + "']/parent::*"));
    }

    public void clickOnSelectModule() {
        click(By.xpath("//span[contains(text(),'Select module')]"));
    }

    public void clickOnSelectedModule(String module) {
        click(By.xpath("//div[contains(text(),'" + module + "')]"));
    }

    public void clickOnSelectLesson() {
        click(By.xpath("//span[contains(text(),'Select lesson')]"));
    }

    public void clickOnSelectedLesson(String lesson) {
        click(By.xpath("//*/text()[normalize-space(.)='" + lesson + "']/parent::*"));
    }

    public void clickOnVideoLine() {
        if (isElementPresent(By.xpath("//button[@id='video-toggle']"))) {
            clickWithScroll(By.cssSelector("#video-toggle"));
        } else {
            System.out.println("Элемента Video на вкладке урока нет");
        }
    }

    public List<WebElement> findVideoElements() {
        return driver.findElements(By.tagName("video"));
    }
    public List<WebElement> getDropdownListOfGroups() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> groups = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return groups;
    }

    public List<WebElement> getDropdownListOfModules() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[2]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> modules = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return modules;
    }
    public List<WebElement> getDropdownListOfLessons() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[3]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> lessons = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return lessons;
    }
      public boolean isModulePresent() {
        return isElementPresent(By.xpath("//span[contains(text(),'Select module')]"));
    }
    public boolean isTextPresent() {
        // Находим элемент с тегом <ol>
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement tabContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li")));
        // Проверьте, есть ли текст внутри элемента
        return (!tabContent.getText().isEmpty());
    }

    public void clickOnPlanLine() {
        click(By.cssSelector("#plan-toggle"));
    }

    public void clickOnTheoryLine() {
        click(By.cssSelector("#theory-toggle"));
    }

    public void clickOnHomeWorkLine() {
        click(By.cssSelector("#home_work-toggle"));
    }

    public void clickOnCodeLine() {
        click(By.cssSelector("#code-toggle"));
    }
    public void scrollPageUp() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }
       public boolean isPlanLinePresent() {
        return (isElementPresent(By.cssSelector("#plan-toggle")));
    }

    public boolean isTheoryLinePresent() {
        return (isElementPresent(By.cssSelector("#theory-toggle")));
    }

    public boolean isHomeWorkLinePresent() {
        return (isElementPresent(By.cssSelector("#home_work-toggle")));
    }

    public boolean isCodeLinePresent() {
        return (isElementPresent(By.cssSelector("#code-toggle")));
    }

    public boolean isVideoLinePresent() {
        return (isElementPresent(By.cssSelector("#video-toggle")));
    }

    public void selectModule(String module) {
        click(By.xpath("//span[contains(text(),'Select module')]"));
        click(By.xpath("//div[contains(text(),'" + module + "')]"));
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public boolean selectMyGroup(String group) {
        return isElementPresent(By.cssSelector("[aria-label='" + group + "']"));
          }


    public void clickOnMyGroup(String group) {
             click(By.xpath("//span[contains(text(),'" + group + "')]"));
    }

    public boolean selectMyLesson(String lesson) {
        return isElementPresent(By.cssSelector("[aria-label='" + lesson + "']"));
    }

    public void clickOnMyLesson(String lesson) {
              click(By.xpath("//div[contains(text(),'" + lesson + "')]"));
    }
    public void clickOnForgotPassword(String mail) {
        click(By.xpath("//a[contains(text(),'Forgot password?')]"));
        click(By.id("email-reset-page"));
        type(By.id("email-reset-page"), mail);
        click(By.xpath("//button[@label='Reset Password']"));
}

    public boolean isResetPasswordPresent() {
        return isElementPresent(By.xpath("//div[contains(text(),'Reset password')]"));
    }

    public void clickOnStudents() {
        click(By.xpath("//button[contains(text(),'Students')]"));
    }

    public void clickOnUsers() {
        click(By.xpath("//button[contains(text(),'Users')]"));
    }

    public void clickOnSearch() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-users-list[1]/div[1]/div[1]/div[1]/p-table[1]/div[1]/div[1]/div[1]/div[1]/span[1]/i[1]"));

    }

    public void clickOnFieldSearchEndEnter(String mail) {
        click(By.xpath("//input[@placeholder='Search...']"));
        driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(mail);
    }

    public void clickOnCohorts() {
        click(By.xpath("//button[contains(text(),'Cohorts')]"));
    }

    public String cohortOnFirstRow() {
        pause(1000);
        String text = getText(By.xpath("//tbody/tr[1]/td[2]"));
        return text;
    }

    public String studentOnFirstRow() {
        pause(1000);
        String text = getText(By.xpath("//tbody/tr[3]/td[5]"));
        return text;
    }

    public void loginWithWrongData(String mail, String password) {
        pause(1000);
        click(By.xpath("//button[contains(text(),'Login')]")); //click on Sign in button
        type(By.id("email-login-page"), mail);
        type(By.cssSelector("[type='password']"), password);
    }

    public void clickOnStudentCabinetOnMenu() {
        pause(1000);
        click(By.xpath("//button[contains(text(),'Student cabinet')]"));
    }

    public void clickOnLessonsInStudentCabinet() {
        click(By.xpath("//button[contains(text(),'Lessons')]"));
    }

    public void clickOnGoToMainPage() {
        click(By.xpath("//a[contains(text(),'Go to Main page')]"));

    }
}
