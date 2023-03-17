package UdemySeleniumCourse.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
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



/*        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='mb-3']")));
        //List<WebElement> products = driver.findElements(By.cssSelector("[class*='mb-3']"));
*//*        for (WebElement product : products) {
           if (product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(itemToAdd)) {
                driver.findElement(By.cssSelector("[class*='mb-3'] button:last-of-type")).click();
            }
        }*//*
        //same as above but with streams:
        WebElement itemToBuy = products.stream().filter(product -> product.findElement(By.cssSelector("[class*='mb-3'] b")).getText().equals(itemToAdd)).findFirst().orElse(null);
        itemToBuy.findElement(By.cssSelector("[class*='mb-3'] button:last-of-type")).click();*/

/*        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));*/

        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("infoWrap")));
        /*List<WebElement> cartItems = driver.findElements(By.className("infoWrap"));
        WebElement itemToBuyNow = cartItems.stream().filter(item -> item.findElement(By.cssSelector(".infoWrap h3")).getText().equalsIgnoreCase(itemToAdd)).findFirst().orElse(null);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button")));
        itemToBuy.findElement(By.tagName("button")).click();*/
        List<WebElement> itemNames = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = itemNames.stream().anyMatch(s -> s.getText().equalsIgnoreCase(itemToAdd));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector("[class*='subtotal'] button")).click();
        driver.findElement(By.cssSelector("input[placeholder*='Country']")).sendKeys(country.substring(0,3));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
        List<WebElement> countryResults = driver.findElements(By.cssSelector("[class*='ta-results'] [class*='ta-item']"));
        WebElement countryResult = countryResults.stream().filter(c -> c.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
        countryResult.click();
        driver.findElement(By.cssSelector("[class*='action__submit']")).click();
        String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
        driver.close();
    }
}
