package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By confirmMessage = By.xpath("//h1[@class='hero-primary']");

    public String getConfirmMessage() {
        WebElement confirmElement = driver.findElement(confirmMessage);
        return confirmElement.getText();
    }
}
