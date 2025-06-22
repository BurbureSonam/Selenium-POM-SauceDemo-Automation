package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.HashMap;


public class BaseTest {
    protected static WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

       @BeforeSuite
    public void initializeExtentReports() {
        extent = ExtentReportManager.getReportInstance();
    }

   
    @BeforeClass
    public void initializeWebDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        // Disable Chrome password manager prompt
      
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

  
    @AfterClass
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void closeExtentReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
