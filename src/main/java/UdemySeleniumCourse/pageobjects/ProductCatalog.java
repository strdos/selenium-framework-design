package UdemySeleniumCourse.pageobjects;

import UdemySeleniumCourse.AbstractComponents.AbstractComponent;
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
    private List<WebElement> products;
    @FindBy(className="ng-animating")
    private WebElement spinner;
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    private WebElement cartBtn;

    private By productsLocator = By.cssSelector("[class*='mb-3']");
    private By addToCart = By.cssSelector("[class*='mb-3'] button:last-of-type");
    private By toastMsg = By.id("toast-container");

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

    public CartPage goToCart() {
        cartBtn.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

}
