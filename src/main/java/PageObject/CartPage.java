package PageObject;

import AbstractComponents.AbstractComponent;
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
    public CartPage goToCart() {
        try {
            // Debug: Check if element is found
            if (cartButton == null) {
                System.out.println("CartButton is null - PageFactory not initialized");
            }else {
                cartButton.click();
            }

        } catch (Exception e) {
            System.out.println("Failed to navigate to cart: " + e.getMessage());
        }
        return this;
    }


    public boolean verifyProductName(String productName){
        return verifyProductName.getText().equalsIgnoreCase(productName);
    }

    // Proceed to checkout
    public CheckOutPage proceedToCheckout() {
        try {
            AbstractComponent.waitForElementToBeClickable(checkoutButton, driver);
            checkoutButton.click();
            //((JavascriptExecutor) driver).executeScript("button click()", checkoutButton);
        } catch (Exception e) {
            System.out.println("Failed to click checkout: " + e.getMessage());
        }

        return new CheckOutPage(driver);
    }

}
