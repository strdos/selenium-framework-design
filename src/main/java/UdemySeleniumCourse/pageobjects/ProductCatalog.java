package UdemySeleniumCourse.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog {
    WebDriver driver;
    public ProductCatalog(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory (driver is initialized in the constructor)

    // List<WebElement> names = driver.findElements(By.cssSelector("[class*='mb-3']"));

    @FindBy(css="[class*='mb-3']")
    List<WebElement> names;



}
