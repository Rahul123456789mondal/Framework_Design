package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Country Selection Fields
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(css = ".ta-results")
    WebElement countryDropdown;

    @FindBy(css = ".ta-item")
    List<WebElement> countryOptions;

    @FindBy(xpath = "//button[contains(.,'India')]")
    WebElement indiaOption;

    // Action Buttons
    @FindBy(css = ".action__submit")
    WebElement submitBtn;



    public void selectCountry(String countryName){
        countryInput.click();
        countryInput.sendKeys(countryName);

    }


    /*
    // Methods for Country Selection
    public void selectCountry(String countryName) {
        countryInput.click();
        countryInput.sendKeys(countryName.substring(0, 3)); // Type first 3 characters
        waitForElementToAppear(By.cssSelector(".ta-results"));

        // Select the specific country from dropdown
        WebElement country = countryOptions.stream()
                .filter(option -> option.getText().equalsIgnoreCase(countryName))
                .findFirst()
                .orElse(null);

        if (country != null) {
            country.click();
        }
    }

    public void selectIndia() {
        countryInput.click();
        countryInput.sendKeys("india");
        waitForElementToAppear(By.cssSelector(".ta-results"));
        indiaOption.click();
    }

    // Method to submit the order
    public void submitOrder() {
        waitForElementToBeClickable(submitBtn);
        submitBtn.click();
    }


    // Method for complete checkout flow
    public void completeCheckout(String country) {
        selectCountry(country);
        submitOrder();
    }

    // Method specifically for India selection (as per your test requirement)
    public void completeCheckoutWithIndia() {
        selectIndia();
        submitOrder();
    }
    */

}
