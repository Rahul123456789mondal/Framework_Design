package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckOutPage {

    WebDriver driver;
    WebDriverWait wait;
    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    /*@FindBy(css = ".ta-results")
    WebElement countryDropdown;

    @FindBy(css = ".ta-item")
    List<WebElement> countryOptions;

    @FindBy(xpath = "//button[contains(.,'India')]")
    WebElement indiaOption;*/

    // Locators
    By countryInput = By.xpath("//input[@placeholder='Select Country']");
    By submitBtn = By.xpath("//a[normalize-space()='Place Order']");

    public void selectCountry(String countryName) {

        try {
            WebElement clickOnCountry = driver.findElement(countryInput);
            // Scroll to country input first
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", clickOnCountry);

            // Clear and enter country name
            clickOnCountry.click();
            clickOnCountry.clear();
            clickOnCountry.sendKeys(countryName);

            List<WebElement> countryList = driver.findElements(By.xpath("//button[contains(@class,'ta-item')]"));
            System.out.println("Available countries: " + countryList.size());

            for (WebElement country : countryList) {
                String countryText = country.getText().trim();
                System.out.println("Country option: " + countryText);

                if (countryText.equalsIgnoreCase(countryName)) {
                    // Click the option
                    country.click();
                    System.out.println("Selected country: " + countryText);
                }
            }
        } catch (Exception e) {
            System.out.println("Error selecting country: " + e.getMessage());
            e.fillInStackTrace();
        }

    }

    // Method to submit the order with multiple click strategies
    public ConfirmationPage submitOrder() {
        WebElement submitButton = driver.findElement(submitBtn);
        try {
            // Scroll to submit button - arguments[0].scrollIntoView({block: 'center'});
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Add delay for page stability
            Thread.sleep(1000);
            AbstractComponent.waitForElementToBeClickable(submitButton, driver);
            submitButton.click();
            return new ConfirmationPage(driver);
        }
        catch (Exception e) {
            System.out.println("Error submitting order: " + e.getMessage());
            e.fillInStackTrace();
            throw new RuntimeException("Failed to submit order", e);
        }
    }



}

/*
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
        }*/

/*    // Method to submit the order
    public ConfirmationPage submitOrder() {
        AbstractComponent.waitForElementToBeClickable(submitBtn, driver);
        submitBtn.click();
        return new ConfirmationPage(driver);
    }*/
