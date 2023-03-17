package UdemySeleniumCourse.pageobjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
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


    public void goToUrl(String url) {
        driver.get(url);
    }
    public void loginToApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitBtn.click();
    }

}
