package UdemySeleniumCourse.AbstractComponents;

import UdemySeleniumCourse.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(css = "[routerlink*='myorders'")
    WebElement orderHeader;
    public OrderPage goToOrdersPage() {
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

    public void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToAppear(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
