package UdemySeleniumCourse.Tests;

import UdemySeleniumCourse.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidation extends BaseTest {
    @Test
    public void submitOrder() throws IOException {
        String email = "alex@test.com";
        String password = "invalid"; // invalid
        String errorMsg = "Incorrect email or password.";

        landingPage.loginToApplication(email, password); //send invalid password
        Assert.assertEquals(landingPage.getErrorMsg(), errorMsg);
    }
}
