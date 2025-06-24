package PageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    public WebDriver driver;

    // Constructor: initializes PageFactory elements
    public LandingPage(WebDriver driver) {
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

    // Actions
    public LandingPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LandingPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LandingPage clickLogin() {
        loginButton.click();
        return this;
    }

    // Combined action for login
    public ProductList login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        // Wait for products to load (this ensures login was successful)
        AbstractComponent.waitForElementToAppear(By.cssSelector(".mb-3"), driver);
        return new ProductList(driver);
    }
}
