package utils;

import com.aventstack.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver; // ✅ Accessible in all test classes
    private ExtentReports extent;

    // ✅ Shared WebDriver access
    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setUpSuiteAndLogin() {
        System.out.println("🔧 Setting up browser and performing login...");

        // Setup ExtentReports
        extent = ExtentReportManager.getReportInstance();

        // Setup Chrome driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Perform login once
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            wait.until(ExpectedConditions.urlContains("inventory.html"));
            System.out.println("✅ Login successful.");
        } catch (TimeoutException e) {
            throw new RuntimeException("❌ Login failed: Check credentials or page load issue.", e);
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("🧹 Closing browser and finalizing report...");
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.flush();
        System.out.println("✅ Test suite execution completed.");
    }
}
