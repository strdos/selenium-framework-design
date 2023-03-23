package UdemySeleniumCourse.TestComponents;

import UdemySeleniumCourse.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); //thread-safe
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //each object will run in its own thread
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        //test.log(Status.PASS,"Passed");
        extentTest.get().log(Status.PASS,"Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL,"Failed");
        //test.fail(result.getThrowable());
        extentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        String screenShotPath;
        try {
            screenShotPath = getScreenshot(driver, result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //test.addScreenCaptureFromPath(screenShotPath, result.getMethod().getMethodName());
        extentTest.get().addScreenCaptureFromPath(screenShotPath, result.getMethod().getMethodName());
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
