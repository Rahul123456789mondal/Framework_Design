package Selenium_Framwork.framwork_design;

import PageObject.CartPage;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class OrderValidationTest extends BaseTest {

    CartPage cartPage ;

    // This class uses individual test approach (fresh browser for each test)
    public OrderValidationTest() {
        this.isSessionBased = false; // Each test gets fresh browser session
    }


    @Test(groups = {"Order Validation Test"}, priority = 1)
    public void orderValidationTest() {
        System.out.println("Running orderValidationTest with existing session");

        // Navigate to orders page and validate
        cartPage.orderMenuClicked();
        // Add your order validation logic here

        System.out.println("Order validation completed");
    }

}
