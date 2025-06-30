package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    // Wait for element to appear
    public static void waitForElementToAppear(By findBy, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    // Wait for element to disappear
    public static void waitForElementToDisappear(By findBy, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    // Wait for element to be clickable (WebElement version)
    public static void waitForElementToBeClickable(WebElement element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Legacy method for backward compatibility
    /*public void waitForAppear(By findBy) {
        waitForElementToAppear(findBy);
    }*/

    // Wait for element to be clickable
    /*public void waitForElementToBeClickable(By findBy, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }*/


}
