package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    public CartPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // xPath Annotation
    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartButton;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@class='cartSection']//h3")
    private WebElement verifyProductName;


    // Go to cart page
    public void goToCart() {
        try {
            if (cartButton == null) {
                System.out.println("CartButton is null - PageFactory not initialized");
            } else {
                // Replaced standard click with JavaScript click to bypass interception
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", cartButton);
            }

        } catch (Exception e) {
            System.out.println("Failed to navigate to cart: " + e.getMessage());
        }
    }


    public boolean verifyProductName(String productName){
        return verifyProductName.getText().equalsIgnoreCase(productName);
    }

    // Proceed to checkout
    public CheckOutPage proceedToCheckout() {
        try {
            AbstractComponent.waitForElementToBeClickable(checkoutButton, driver);

            // Use JavaScript executor to bypass the click interception
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", checkoutButton);

        } catch (Exception e) {
            System.out.println("Failed to click checkout: " + e.getMessage());
            // Throw an exception so the test fails immediately if the transition fails
            throw new RuntimeException("Could not click the checkout button", e);
        }

        return new CheckOutPage(driver);
    }



}
