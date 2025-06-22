package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage login(String username, String password) {
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        // Check for login error message
        try {
            WebElement errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']"));
            String errorText = errorMsg.getText();
            throw new RuntimeException("Login failed with message: " + errorText);
        } catch (Exception ignored) {
            // No error found – continue
        }

        return new HomePage(driver);
    }
}
