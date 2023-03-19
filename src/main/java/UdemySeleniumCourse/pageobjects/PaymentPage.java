package UdemySeleniumCourse.pageobjects;

import UdemySeleniumCourse.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage extends AbstractComponent {

    WebDriver driver;

    public PaymentPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder*='Country']")
    WebElement country;
    @FindBy(css = "[class*='ta-results'] [class*='ta-item']")
    List<WebElement> countryResults;
    @FindBy(css = "[class*='action__submit']")
    WebElement placeOrderBtn;

    public void selectCountry (String countryName) {
        country.sendKeys(countryName.substring(0,3));
        WebElement countryResult = countryResults.stream().filter(c -> c.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
        countryResult.click();
    }

    public ConfirmationPage placeOrder() {
        placeOrderBtn.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
