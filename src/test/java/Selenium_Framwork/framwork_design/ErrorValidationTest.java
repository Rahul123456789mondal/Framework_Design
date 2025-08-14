package Selenium_Framwork.framwork_design;

import PageObject.LandingPage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {

    LandingPage landingPage ;

    // This class uses individual test approach (fresh browser for each test)
    public ErrorValidationTest() {
        this.isSessionBased = false; // Each test gets fresh browser session
    }

    @Test(groups = {"Login Validation"}, priority = 1)
    public void loginErrorValidation(){

        // Add null check for driver
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Check BaseTest configuration.");
        }

        System.out.println("Driver initialized successfully: " + driver);

        landingPage = new LandingPage(driver);
        landingPage.login("arkatest@test.com","Test@1234" );
        System.out.println(landingPage.getErrorMessage());

        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

}
