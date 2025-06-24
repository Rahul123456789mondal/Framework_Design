package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductList {

    WebDriver driver;

    public ProductList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Using xpath properly with FindBy annotation
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    @FindBy(xpath = "//div[contains(@class, 'col-lg-4')]")
    private List<WebElement> products;

    // Locators
    By productBy = By.cssSelector(".mb-3");
    By productNameBy = By.cssSelector("b");
    By addToCartButton = By.xpath(".//button[contains(text(),'Add To Cart')]");
    By toastMessage = By.cssSelector("#toast-container");

    // Get a specific product by name
    public WebElement getProductByName(String productName) {
        AbstractComponent.waitForElementToAppear(productBy, driver);

        return products.stream()
                .filter(product -> {
                    try {
                        String actualName = product.findElement(productNameBy).getText().trim();
                        return actualName.equalsIgnoreCase(productName);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }

    // Method to add a product to cart
    public CartPage addProductToCart(String productName) {
        WebElement productElement = getProductByName(productName);

        if (productElement != null) {
            try {
                // Try clicking using JavaScript as a more reliable method
                WebElement addToCartBtn = productElement.findElement(addToCartButton);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", addToCartBtn);

                // Wait for toast notification to appear and then disappear
                AbstractComponent.waitForElementToAppear(toastMessage, driver);
                AbstractComponent.waitForElementToDisappear(toastMessage, driver);

                System.out.println("Added " + productName + " to cart successfully");
            } catch (Exception e) {
                System.out.println("Failed to add product to cart: " + e.getMessage());
            }
        } else {
            System.out.println("Product " + productName + " not found");

            // If product not found by name, click the first product's details
            try {
                AbstractComponent.waitForElementToAppear(productBy, driver);
                WebElement firstProduct = products.get(0);
                firstProduct.click();

                // On product details page, click Add To Cart
                By detailsAddToCartBtn = By.xpath("//button[contains(text(),'Add To Cart')]");
                AbstractComponent.waitForElementToBeClickable((WebElement) detailsAddToCartBtn, driver);
                WebElement addBtn = driver.findElement(detailsAddToCartBtn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

                // Wait for toast notification
                AbstractComponent.waitForElementToAppear(toastMessage, driver);
                AbstractComponent.waitForElementToDisappear(toastMessage, driver);

                System.out.println("Added product from details page to cart successfully");
            } catch (Exception e) {
                System.out.println("Failed to add product from details page: " + e.getMessage());
            }
        }

        return new CartPage(driver);
    }



}