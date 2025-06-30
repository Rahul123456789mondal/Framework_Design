package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Country Selection Fields
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement countryInput;

    /*@FindBy(css = ".ta-results")
    WebElement countryDropdown;

    @FindBy(css = ".ta-item")
    List<WebElement> countryOptions;

    @FindBy(xpath = "//button[contains(.,'India')]")
    WebElement indiaOption;*/

    // Action Buttons
    @FindBy(xpath = "//a[normalize-space()='Place Order']")
    private WebElement submitBtn;



    public CheckOutPage selectCountry(String countryName) {
        countryInput.click();
        countryInput.clear();
        countryInput.sendKeys(countryName);

        List<WebElement> countryList = driver.findElements(By.xpath("//button[contains(@class,'ta-item')]"));
        System.out.println("Available countries: " + countryList.size());

        for (WebElement country : countryList) {
            String countryText = country.getText().trim();
            System.out.println("Country option: " + countryText);

            if (countryText.equalsIgnoreCase(countryName)) {
                // Wait for element to be clickable
                AbstractComponent.waitForElementToBeClickable(country, driver);

                // Scroll into view if needed
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", country);

                // Click the option
                country.click();
                System.out.println("Selected country: " + countryText);
                return this;
            }
        }
        return this;
    }

    // Method to submit the order
    public ConfirmationPage submitOrder() {
        AbstractComponent.waitForElementToBeClickable(submitBtn, driver);
        submitBtn.click();
        return new ConfirmationPage(driver);
    }


}
