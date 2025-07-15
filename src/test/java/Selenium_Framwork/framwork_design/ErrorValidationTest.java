package Selenium_Framwork.framwork_design;

import PageObject.LandingPage;
import PageObject.ProductList;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {

    LandingPage landingPage ;

    @Test(groups = {"Error Validation"})
    public void loginErrorValidation(){

        // Add null check for driver
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Check BaseTest configuration.");
        }

        System.out.println("Driver initialized successfully: " + driver);

        landingPage = new LandingPage(driver);
        landingPage.login("arkatest@test.com","Test@1234" );
        System.out.println(landingPage.getErrorMessage());

        //Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

}
