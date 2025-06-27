package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 🔍 Elements on Cart Page
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//button[text()='Remove']")
    private List<WebElement> removeButtons;

    // 🛠 Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        waitForCartPage();
    }

    // ✅ Wait for cart page to load
    private void waitForCartPage() {
        wait.until(ExpectedConditions.urlContains("cart.html"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("cart_item")));
    }

    // ✅ Check if any item is present in the cart
    public boolean isItemInCart() {
        return !cartItems.isEmpty();
    }

    // ✅ Remove the first item from the cart
    public void removeItem() {
        if (!removeButtons.isEmpty()) {
            WebElement firstRemove = removeButtons.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(firstRemove)).click();
            System.out.println("🗑️ Item removed from cart.");
        } else {
            System.out.println("⚠️ No item to remove in cart.");
        }
    }

    // ✅ Get the number of items in the cart
    public int getItemCount() {
        return cartItems.size();
    }
}
