package de.aittr.lms;

import java.time.Duration;

import de.aittr.lms.fwUI.GroupHelper;
import de.aittr.lms.fwUI.HeaderHelper;
import de.aittr.lms.fwUI.UserHelperUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {

  String browser;
  WebDriver driver;

  UserHelperUI userUI;
  HeaderHelper headerUI;
  GroupHelper groupUI;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init () {

    if(browser.equalsIgnoreCase("chrome")){
      driver = new ChromeDriver();
    } else if (browser.equalsIgnoreCase("firefox")) {
      driver = new FirefoxDriver();
    } else if (browser.equalsIgnoreCase("edge")){
      EdgeOptions options = new EdgeOptions();
      options.addArguments("remote-allow-origins=*");
      driver = (WebDriver) new EdgeDriver();
    }

    driver.get("http://localhost:4200");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    userUI = new UserHelperUI(driver);
    headerUI = new HeaderHelper(driver);
    groupUI = new GroupHelper(driver);

  }

  public void stop(){
    driver.quit();
  }

  public UserHelperUI getUserUI() {
    return userUI;
  }

  public HeaderHelper getHeaderUI() {
    return headerUI;
  }

  public GroupHelper getGroupUI() {
    return groupUI;
  }
}
