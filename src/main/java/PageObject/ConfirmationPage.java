package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPage {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By confirmMessage = By.xpath("//h1[@class='hero-primary']");
    By orderID = By.xpath("//label[contains(@class, 'ng-star-inserted')]");

    public String getConfirmMessage() {
        // Wait for the confirmation header to appear before grabbing it
        AbstractComponent.waitForElementToAppear(confirmMessage, driver);
        WebElement confirmElement = driver.findElement(confirmMessage);
        return confirmElement.getText();
    }

    public String getOrderID(){
        WebElement getOrderID = driver.findElement(orderID);
        return getOrderID.getText();
    }

    public void orderMenuClicked(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ordersBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(@routerlink,'myorders')]")
                )
        );
        ordersBtn.click();

    }

}
