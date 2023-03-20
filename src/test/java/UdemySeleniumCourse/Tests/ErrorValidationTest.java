package UdemySeleniumCourse.Tests;

import UdemySeleniumCourse.TestComponents.BaseTest;
import UdemySeleniumCourse.pageobjects.CartPage;
import UdemySeleniumCourse.pageobjects.ProductCatalog;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationTest extends BaseTest {
    @Test
    public void loginErrorValidation() throws IOException {
        String email = "alex@test.com";
        String password = "invalid"; // invalid
        String errorMsg = "Incorrect email or password.";

        landingPage.loginToApplication(email, password); //send invalid password
        Assert.assertEquals(landingPage.getErrorMsg(), errorMsg);
    }
    @Test
    public void productErrorValidation() throws IOException {
        String email = "alex@test.com";
        String password = "Test1234!";
        String itemToAdd = "ZARA COAT 3";

        ProductCatalog productCatalog = landingPage.loginToApplication(email, password);
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(itemToAdd);

        CartPage cartPage = productCatalog.goToCart();
        Assert.assertFalse(cartPage.isProductMatch("invalid"));
    }
}
