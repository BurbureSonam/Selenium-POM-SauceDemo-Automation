package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    /* 🔍 Page Elements */
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[text()='Remove']")
    private List<WebElement> removeButtons;

   // Constructor 
    public HomePage(WebDriver driver) {
        this.driver  = driver;
        this.wait    = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        
    }

    /* ✅ Wait until inventory page is fully loaded */
    private void waitForInventoryPage() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    /* ✅ Get inventory‑page title */
    public String getPageTitle() {
        waitForInventoryPage();
        return pageTitle.getText();
    }

    /* ✅ Add first visible product */
    public void addToCart() {
        waitForInventoryPage();
        if (!addToCartButtons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(0))).click();
            System.out.println("🛒 Product added to cart.");
        } else {
            throw new NoSuchElementException("❌ No 'Add to cart' button found.");
        }
    }

    /* ✅ Remove first visible product */
    public void removeFromCart() {
        waitForInventoryPage();
        if (!removeButtons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0))).click();
            System.out.println("🗑️ Product removed from cart.");
        } else {
            System.out.println("⚠️ No 'Remove' button found on inventory page.");
        }
    }

    /* ✅ Check if product already added */
    public boolean isProductRemoveButtonVisible() {
        waitForInventoryPage();
        return !removeButtons.isEmpty();
    }

    /* ✅ Sort products */
    public void sortProducts(String visibleText) {
        waitForInventoryPage();
        new Select(sortDropdown).selectByVisibleText(visibleText);
        System.out.println("🔃 Products sorted by: " + visibleText);
    }

    /* ✅ Open Cart page */
    public CartPage openCart() {
        cartIcon.click();
        return new CartPage(driver);
    }

    /* ✅ Logout from any page */
    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        System.out.println("🚪 Logged out successfully.");
    }

    /* ✅ Current URL helper */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
