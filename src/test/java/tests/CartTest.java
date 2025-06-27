package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import utils.BaseTest;
import utils.TestListener;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {

    @Test(description = "🛒 Add item to cart, verify, remove it, and logout")
    public void testCartFunctionality() {
        var test = TestListener.getTest();

        try {
            test.info("🚀 Starting Cart Test");

            // Step 1: Navigate to Home Page
            HomePage home = new HomePage(getDriver());
            Assert.assertTrue(home.getCurrentUrl().contains("inventory.html"), "❌ Not on inventory page");

            // Step 2: Add product to cart
            home.addToCart();
            test.info("📦 Product added to cart");

            // Step 3: Go to cart page
            CartPage cart = home.openCart();
            Assert.assertTrue(cart.isItemInCart(), "❌ No item found in cart");
            test.pass("✅ Product visible in cart");

            // Step 4: Remove item
            cart.removeItem();
            test.info("🗑️ Product removed from cart");

            // Step 5: Assert cart is empty
            Assert.assertEquals(cart.getItemCount(), 0, "❌ Cart not empty after removal");
            test.pass("✅ Cart is empty after item removal");

            // ✅ Step 6: Logout
            home = new HomePage(getDriver()); // reinitialize HomePage
            home.logout();
            test.info("🚪 Logged out of the application");
            test.pass("✅ Logout successful");

        } catch (Exception e) {
            test.fail("❌ Cart test failed: " + e.getMessage());
            throw e;
        } finally {
            test.info("🏁 Cart Test completed");
        }

        System.out.println("✅ Cart Test executed");
    }
}
