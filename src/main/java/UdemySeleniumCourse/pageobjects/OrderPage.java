package UdemySeleniumCourse.pageobjects;

import UdemySeleniumCourse.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    public boolean isAddedProductDisplayed(String itemToAdd) {
        return productNames.stream().anyMatch(p -> p.getText().equalsIgnoreCase(itemToAdd));
    }
}
