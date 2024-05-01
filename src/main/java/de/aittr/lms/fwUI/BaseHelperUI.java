package de.aittr.lms.fwUI;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelperUI {
    protected WebDriver driver;

    public BaseHelperUI(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.alertIsPresent());
        if (alert != null) {
            driver.switchTo().alert();
            alert.accept();
            return true;
        } else {
            driver.switchTo().alert();
            alert.dismiss();
            return false;
        }
    }

    public void type(By locator, String text) {
        if (text != null) {
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/screen" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();
    }

    public boolean verifyLinks(String linkURL) {
        Boolean status = false;
        try {
            URL url = new URL(linkURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() <= 400) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(linkURL + " - " + e.getMessage() + " is broken link");
        }
        return status;
    }

    public boolean shouldHaveText(WebElement locator, String text, int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.textToBePresentInElement(locator, text));
    }

    public void clickWithJSExecutor(WebElement element, int x, int y) {
        JavascriptExecutor jsEx = (JavascriptExecutor) driver;
        jsEx.executeScript("window.scrollBy(" + x + "," + y + ")");
        element.click();
    }

    public void clickWithRectangle(By locator, int x, int y) {
        WebElement element = driver.findElement(locator);
        Rectangle rectangle = element.getRect();

        int offSetX = rectangle.getWidth() / x;
        int offSetY = rectangle.getHeight() / y;

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        actions.moveByOffset(-offSetX, -offSetY).click().perform();
    }

    public String getTextByValue(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean isTooltipVisible(By locator) {
        String tooltip = driver.findElement(locator).getAttribute("title");
        if (tooltip.contains("@")) {
            System.out.println("************************ [ " + tooltip + " ]");
            return true;
        } else {
            System.out.println("********** No TOOLTIP");
        }
        return false;
    }

    public Integer randomInt() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public boolean isErrorWindowPresent() {
        return isElementPresent(By.cssSelector(".error-message"));
    }

    public void returnBack() {
        driver.navigate().back();
    }

    public boolean isEnable(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    public void scrollDown(int downValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, " + downValue + ");");
    }
}
