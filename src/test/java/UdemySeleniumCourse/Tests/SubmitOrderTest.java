package UdemySeleniumCourse.Tests;

import UdemySeleniumCourse.AbstractComponents.AbstractComponent;
import UdemySeleniumCourse.TestComponents.BaseTest;
import UdemySeleniumCourse.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String email = "alex@test.com";
    String password = "Test1234!";
    String itemToAdd = "ZARA COAT 3";
    String country = "Australia";
    String confirmMsgText = "THANKYOU FOR THE ORDER.";
    @Test
    public void submitOrder() throws IOException {
        ProductCatalog productCatalog = landingPage.loginToApplication(email, password);
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(itemToAdd);

        CartPage cartPage = productCatalog.goToCart();
        Assert.assertTrue(cartPage.isProductMatch(itemToAdd));

        PaymentPage paymentPage = cartPage.goToCheckout();
        paymentPage.selectCountry(country);

        ConfirmationPage confirmationPage = paymentPage.placeOrder();
        Assert.assertEquals(confirmMsgText, confirmationPage.getConfirmationText());
    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void checkOrderHistory() {
        ProductCatalog productCatalog = landingPage.loginToApplication(email, password);
        OrderPage orderPage = productCatalog.goToOrdersPage();
        Assert.assertTrue(orderPage.isAddedProductDisplayed(itemToAdd));
    }
}
