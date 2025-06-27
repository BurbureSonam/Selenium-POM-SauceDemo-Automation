package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ✅ Login method that returns HomePage
    public HomePage login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();

        // Optional: wait for inventory page
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlContains("inventory.html"));

        return new HomePage(driver);
    }
}
