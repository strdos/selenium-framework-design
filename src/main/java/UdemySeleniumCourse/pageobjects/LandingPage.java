package UdemySeleniumCourse.pageobjects;

import UdemySeleniumCourse.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver); //sending the driver to the parent class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory (driver is initialized in the constructor)
    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement userPassword;
    @FindBy(id="login")
    WebElement submitBtn;
    @FindBy(css = "[class*='flyInOut'")
    WebElement errorToastMsg;


    public void goToUrl(String url) {
        driver.get(url);
    }
    public ProductCatalog loginToApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitBtn.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }

    public String getErrorMsg() {
        waitForElementToAppear(errorToastMsg);
        return errorToastMsg.getText();
    }

}
