package Selenium_Framwork.framwork_design;

import PageObject.CheckOutPage;
import PageObject.LandingPage;
import PageObject.ProductList;
import TestComponents.BaseTest;
import TestComponents.config;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {

	@Test
	public void standAloneTest() {
		LandingPage landingPage = new LandingPage(driver);
		landingPage.login("arkatest@test.com","Test@123" );
		ProductList listProduct = new ProductList(driver);
		String product_name = "IPHONE 13 PRO";
		listProduct.addProductToCart(product_name);
		listProduct.goToCart();
		listProduct.proceedToCheckout();
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		String countryName = config.getProperty("Country");
		checkoutPage.selectCountry(countryName);
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