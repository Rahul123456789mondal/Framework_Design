package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    // Constructor: initializes PageFactory elements
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Define elements using @FindBy annotations

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    // Error message element
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    // Actions
    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    // Combined action for login
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        // Wait for products to load (this ensures login was successful)
        waitForElementToAppear(By.cssSelector(".mb-3"));
    }
    // Get error message (in case of failed login)
    public String getErrorMessage() {
        //waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}


/* driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("arkatest@test.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
*/