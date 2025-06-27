package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    @Before
    public void beforeScenario() {
        // Only start the browser & navigate to login page
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().addArguments("--incognito", "--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        BaseTest.driver = driver;                   // assign into your BaseTest.driver
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @After
    public void afterScenario() {
        BaseTest.driver.quit();
    }
}
