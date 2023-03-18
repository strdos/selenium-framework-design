package UdemySeleniumCourse.pageobjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> productNames;
    @FindBy(css = "[class*='subtotal'] button")
    WebElement checkoutBtn;

    public boolean isProductMatch(String itemToAdd) {
        return productNames.stream().anyMatch(p -> p.getText().equalsIgnoreCase(itemToAdd));
    }

    public void goToCheckout() {
        checkoutBtn.click();
    }
}
