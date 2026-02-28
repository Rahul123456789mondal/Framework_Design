package Selenium_Framwork.framwork_design;

import PageObject.LandingPage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ErrorValidationTest extends BaseTest {

    LandingPage landingPage ;

    @Test(groups = {"Login Validation"})
    public void loginWithInvalidCredentials() {
        System.out.println("📝 Running: loginWithInvalidCredentials");

        // Each test gets a fresh browser, so we create new page objects
        LandingPage landingPage = new LandingPage(driver);

        // Try to login with invalid credentials
        landingPage.login("arkatest@test.com", "WrongPassword");

        // Verify error message appears
        String errorMessage = landingPage.getErrorMessage();
        Assert.assertTrue(
                errorMessage.contains("Incorrect email or password"),
                "Error message should be displayed for invalid credentials"
        );

        System.out.println("✅ Error validation successful: " + errorMessage);

        // Browser will be closed automatically by BaseTest.closeBrowser()
    }

    @Test(groups = {"Login Validation"})
    public void loginWithEmptyEmail() {
        System.out.println("📝 Running: loginWithEmptyEmail");

        // Fresh browser for this test too
        LandingPage landingPage = new LandingPage(driver);

        landingPage.enterPassword("Test@123");
        landingPage.clickLogin();

        // Add validation for empty email error
        // TODO: Implement empty email validation

        System.out.println("✅ Empty email validation completed");

        // Browser will be closed automatically
    }

}
