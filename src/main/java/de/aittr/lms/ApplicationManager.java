package de.aittr.lms.fw;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {

  String browser;
  WebDriver driver;

  UserHelper user;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

//  public ApplicationManager() {
//  }

  public void init () {

    if(browser.equalsIgnoreCase("chrome")){
//      System.setProperty("webdriver.chrome.driver", "C:/Tools/chromedriver.exe");
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

    user = new UserHelper(driver);

  }

  public void stop(){
    driver.quit();
  }

  public UserHelper getUser() {
    return user;
  }
}
