package Selenium_Framwork.framwork_design;

import PageObject.*;
import TestComponents.BaseTest;
import BaseConfig.config;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {

	LandingPage landingPage ;
	ProductList productList ;
	CartPage cartPage ;
	CheckOutPage checkoutPage;
	ConfirmationPage confirmationPage;
	String orderId ;

	// Override the session flag
	public StandAloneTest() {
		this.isSessionBased = true;
	}

	@BeforeClass(alwaysRun = true)
	public void performLogin() {
		// Add null check for driver
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check BaseTest configuration.");
		}

		System.out.println("Performing one-time login with driver: " + driver);

		// Perform login once for all tests in this class
		landingPage = new LandingPage(driver);
		productList = landingPage.login("arkatest@test.com", "Test@123");

		System.out.println("Login completed successfully. Session will be maintained for all tests.");
	}



	@Test(priority = 1, groups = {"Session Tests"})
	public void addProductToCartTest() {

		// Add null check for driver
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check BaseTest configuration.");
		}

		System.out.println("Running addProductToCartTest with existing session");

		String product_name = "IPHONE 13 PRO";
		cartPage = productList.addProductToCart(product_name);
		cartPage.goToCart();
		Assert.assertTrue(cartPage.verifyProductName(product_name), "Product Matched");
		System.out.println("Product added to cart successfully");

	}

	@Test(priority = 2, groups = {"Session Tests"}, dependsOnMethods = {"addProductToCartTest"})
	public void checkoutTest() {
		System.out.println("Running checkoutTest with existing session");

		checkoutPage = cartPage.proceedToCheckout();
		String countryName = config.getProperty("Country");
		checkoutPage.selectCountry(countryName);

		confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmMessage();

		if (confirmMessage.equalsIgnoreCase("Thankyou for the order.")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(confirmMessage.contains("Thank you for the order."));
		}

		orderId = confirmationPage.getOrderID();
		System.out.println("Order completed with ID: " + orderId);
	}

}



// Main Code
	/*	List<WebElement> products = driver.findElements(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
		WebElement iPhoneProduct = products.stream().filter(product ->
		{
            try {
                return product.findElement(By.xpath(".//b[normalize-space()='IPHONE 13 PRO']")).getText().equals("IPHONE 13 PRO");
            } catch (Exception e) {
                return false; // avoid NoSuchElementException
            }
        }).findFirst().orElse(null);
		if (iPhoneProduct != null) {
			try {
				WebElement addToCartButton = iPhoneProduct.findElement(By.xpath(".//button[normalize-space()='Add To Cart']"));
				addToCartButton.click();
				System.out.println("Clicked Add To Cart successfully.");
			} catch (Exception e) {
				System.out.println("Add To Cart button not found in the selected product.");
			}
		} else {
			System.out.println("IPHONE 13 PRO product not found.");
		}
		*/
// ******** This Is The Optimized Code ... *******
/*
* List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'col-lg-4')]"));
		products.stream()
				.filter(product -> {
					try {
						return product.findElement(By.xpath(".//b[normalize-space()='IPHONE 13 PRO']"))
								.getText().equals("IPHONE 13 PRO");
					} catch (Exception e) {
						return false;
					}
				})
				.findFirst()
				.ifPresent(product -> {
					try {
						product.findElement(By.xpath(".//button[normalize-space()='Add To Cart']")).click();
						System.out.println("Clicked Add To Cart successfully.");
					} catch (Exception e) {
						System.out.println("Add To Cart button not found.");
					}
				});
* */