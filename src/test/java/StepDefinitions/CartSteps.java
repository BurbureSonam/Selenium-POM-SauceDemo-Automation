package StepDefinitions;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class CartSteps {

    WebDriver driver = BaseTest.getDriver();
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;

    @Given("the user is on the SauceDemo login page")
    public void the_user_is_on_login_page() {
        loginPage = new LoginPage(driver);
    }

    @When("the user logs in with valid credentials")
    public void the_user_logs_in() {
        homePage = loginPage.login("standard_user", "secret_sauce");
        assertTrue(homePage.getCurrentUrl().contains("inventory.html"), "❌ Not on inventory page.");
    }

    @When("the user adds a product to the cart")
    public void the_user_adds_product_to_cart() {
        homePage.addToCart();
    }

    @When("the user opens the cart page")
    public void the_user_opens_cart_page() {
        cartPage = homePage.openCart();
    }

    @Then("the product should be visible in the cart")
    public void the_product_should_be_visible_in_cart() {
        assertTrue(cartPage.isItemInCart(), "❌ Item not found in cart.");
    }

    @When("the user removes the product")
    public void the_user_removes_product() {
        cartPage.removeItem();
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        assertEquals(cartPage.getItemCount(), 0, "❌ Cart is not empty.");
    }
}
