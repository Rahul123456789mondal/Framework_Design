package Selenium_Framwork.framwork_design;

import PageObject.*;
import TestComponents.BaseTest;
import BaseConfig.config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {

	LandingPage landingPage ;
	ProductList productList ;
	CartPage cartPage ;
	CheckOutPage checkoutPage;
	ConfirmationPage confirmationPage;

	@Test
	public void standAloneTest() {

		// Add null check for driver
		if (driver == null) {
			throw new RuntimeException("Driver is not initialized. Check BaseTest configuration.");
		}

		System.out.println("Driver initialized successfully: " + driver);

		landingPage = new LandingPage(driver);
		productList = landingPage.login("arkatest@test.com","Test@123" );

		String product_name = "IPHONE 13 PRO";
		cartPage = productList.addProductToCart(product_name);

		cartPage.goToCart();
		Assert.assertTrue(cartPage.verifyProductName(product_name), "Product Matched");
		checkoutPage = cartPage.proceedToCheckout();

		String countryName = config.getProperty("Country");
		checkoutPage.selectCountry(countryName);
		confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.toLowerCase().equalsIgnoreCase("Thankyou for the order."));

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