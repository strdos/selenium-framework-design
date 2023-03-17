package UdemySeleniumCourse.pageobjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;
    public ProductCatalog(WebDriver driver) {
        super(driver); //every child class has to send the driver to the parent class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[class*='mb-3']")
    List<WebElement> products;
    @FindBy(className="ng-animating")
    WebElement spinner;

    By productsLocator = By.cssSelector("[class*='mb-3']");
    By addToCart = By.cssSelector("[class*='mb-3'] button:last-of-type");
    By toastMsg = By.id("toast-container");

    //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsLocator);
        return products;
    }

    private WebElement getProductByName(String productName) {
        WebElement product = products.stream().filter(p -> p.findElement(By.cssSelector("[class*='mb-3'] b")).getText().equals(productName)).findFirst().orElse(null);
        return product;
    }

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(addToCart).click();
        waitForElementToAppear(toastMsg);
        waitForElementToDisappear(spinner);
    }

}
