package UdemySeleniumCourse.Tests;

import UdemySeleniumCourse.TestComponents.BaseTest;
import UdemySeleniumCourse.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String email = "alex@test.com";
    String password = "Test1234!";
    String itemToAdd = "ZARA COAT 3";
    String country = "Australia";
    String confirmMsgText = "THANKYOU FOR THE ORDER.";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> dataSet) throws IOException {
        ProductCatalog productCatalog = landingPage.loginToApplication(dataSet.get("email"), dataSet.get("password"));
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(dataSet.get("itemToAdd"));

        CartPage cartPage = productCatalog.goToCart();
        Assert.assertTrue(cartPage.isProductMatch(dataSet.get("itemToAdd")));

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
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String >> data = getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");
        return new Object[][] { {data.get(0)}, {data.get(1)} };
    }
}
