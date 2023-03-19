package UdemySeleniumCourse.Tests;

import UdemySeleniumCourse.TestComponents.BaseTest;
import UdemySeleniumCourse.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StandaloneTest2 extends BaseTest {
    @Test
    public void submitOrder() throws IOException {
        String email = "alex@test.com";
        String password = "Test1234!";
        String itemToAdd = "ZARA COAT 3";
        String country = "Australia";
        String confirmMsgText = "THANKYOU FOR THE ORDER.";

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
}
