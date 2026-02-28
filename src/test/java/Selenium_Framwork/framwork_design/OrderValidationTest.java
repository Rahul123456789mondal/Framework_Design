package Selenium_Framwork.framwork_design;

import PageObject.CartPage;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class OrderValidationTest extends BaseTest {

    CartPage cartPage ;

    @Test(groups = {"Order Validation Test"}, priority = 1)
    public void orderValidationTest() {
        System.out.println("Running orderValidationTest with existing session");

        // Navigate to orders page and validate
        //cartPage.orderMenuClicked();
        // Add your order validation logic here

        System.out.println("Order validation completed");
    }

}
