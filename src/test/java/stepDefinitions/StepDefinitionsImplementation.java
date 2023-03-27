package stepDefinitions;

import UdemySeleniumCourse.TestComponents.BaseTest;
import UdemySeleniumCourse.pageobjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionsImplementation extends BaseTest {

    private LandingPage landingPage;
    private ProductCatalog productCatalog;
    private CartPage cartPage;
    private PaymentPage paymentPage;

    @Given("I am on the landing page")
    public void I_am_on_the_landing_page () throws IOException {
        landingPage = launchApplication();
    }

    @Given("^I am logged in with (.+) and (.+)$")
    public void I_am_logged_in_with_email_and_password(String email, String password) {
        productCatalog = landingPage.loginToApplication(email, password);
    }

    @When("^I add (.+) to the cart$")
    public void I_add_product_to_cart(String product) {
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(product);
    }
    @When("^And I verify that (.+) has been added to the cart$")
    public void I_verify_product_added_to_cart(String product) {
        cartPage = productCatalog.goToCart();
        Assert.assertTrue(cartPage.isProductMatch(product));
    }
    @When("I submit the order")
    public void I_submit_the_order() {
        paymentPage = cartPage.goToCheckout();
    }
    @Then("^I verify that correct (.+) is displayed$")
    public void I_verify_that_correct_confirmation_message_is_displayed (String confirmationMsg) {
        ConfirmationPage confirmationPage = paymentPage.placeOrder();
        Assert.assertEquals(confirmationMsg, confirmationPage.getConfirmationText());
    }

}
