package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class EndToEndTest extends BaseTest {

    @Test
    public void completeUserFlowTest() {
        test = extent.createTest("Complete E2E Flow: Login → Add to Cart → Sort → Logout");

        try {
            // Step 1: Login
            test.info("🔐 Logging in with valid credentials");
            LoginPage login = new LoginPage(driver);
            HomePage home = login.login("standard_user", "secret_sauce");
            Thread.sleep(1000);

            String title = home.getPageTitle();
            Assert.assertEquals(title, "Products", "❌ Login failed or incorrect page title");
            test.pass("Login successful. Landed on page: " + title);

            // Step 2: Add to Cart
            test.info("Adding product to cart");
            home.addToCart();
            Thread.sleep(1000);
            test.pass("Product added to cart");

            // Step 3: Open Cart
            test.info("Opening cart page");
            home.openCart();
            Thread.sleep(1000);
            String cartUrl = driver.getCurrentUrl();
            Assert.assertTrue(cartUrl.contains("cart"), "❌ Cart page was not opened");
            test.pass("Cart opened successfully. URL: " + cartUrl);

            // Step 4: Go Back & Sort
            test.info("Navigating back to product list");
            driver.navigate().back();
            Thread.sleep(1000);

            test.info("Sorting products by 'Price (low to high)'");
            home.sortProducts("Price (low to high)");
            Thread.sleep(1000);
            test.pass("Products sorted by 'Price (low to high)'");

            // Step 5: Logout
            test.info("Logging out from the application");
            home.logout();
            Thread.sleep(1000);
            String logoutUrl = driver.getCurrentUrl();
            Assert.assertTrue(logoutUrl.contains("saucedemo"), "❌ Logout failed");
            test.pass("Logout successful. Redirected to login page: " + logoutUrl);

        } catch (Exception e) {
            test.fail("❌ Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
