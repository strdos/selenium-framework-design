package UdemySeleniumCourse.TestComponents;

import UdemySeleniumCourse.pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    String baseUrl = "https://rahulshettyacademy.com/client/";
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties(); //create object of Properties class to read the properties from .properties file in the resources folder
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\UdemySeleniumCourse\\resources\\global_data.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") :  prop.getProperty("browser");

        switch (browserName){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*"); // https://github.com/SeleniumHQ/selenium/issues/11750
                WebDriverManager.chromedriver().setup(); //sets up chrome driver without the need to download the executable and use System.SetProperty
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        return driver;

    }

    public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //convert string to hashmap
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public String getScreenshot(WebDriver driver, String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        File dest = new File(filePath);
        FileUtils.copyFile(src, dest);
        return filePath;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToUrl(baseUrl);
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() { driver.close(); }
}
