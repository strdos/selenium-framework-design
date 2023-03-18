package UdemySeleniumCourse.pageobjects;

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

import java.time.Duration;
import java.util.List;

public class StandaloneTest2 {
    public static void main(String[] args) {
        String baseUrl = "https://rahulshettyacademy.com/client/";
        String email = "alex@test.com";
        String password = "Test1234!";
        String itemToAdd = "ZARA COAT 3";
        String country = "Australia";
        String confirmMsgText = "THANKYOU FOR THE ORDER.";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup(); //sets up chrome driver without the need to download the executable and use System.SetProperty
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goToUrl(baseUrl);
        landingPage.loginToApplication(email, password);

        ProductCatalog productCatalog = new ProductCatalog(driver);
        List<WebElement> products = productCatalog.getProductList();

        productCatalog.addProductToCart(itemToAdd);
        productCatalog.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isProductMatch(itemToAdd));
        cartPage.goToCheckout();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.selectCountry(country);
        paymentPage.placeOrder();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmMsgText, confirmationPage.getConfirmationText());

        driver.close();
    }
}
