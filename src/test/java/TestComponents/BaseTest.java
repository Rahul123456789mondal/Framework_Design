package TestComponents;

import BaseConfig.config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.Objects;
import java.time.Duration;

public class BaseTest {

    //public WebDriver driver;
    protected WebDriver driver;
    protected boolean isSessionBased = false; // Flag to determine if tests should share session

    public WebDriver getDriver() {
        return driver;
    }

    // For session-based tests (login once, run multiple tests)
    @BeforeClass(alwaysRun = true)
    @Parameters({"Browser"})
    public void setupSessionBrowser(String Browser) throws IOException {
        if (isSessionBased) {
            createDriver(Browser);
            driver.get(Objects.requireNonNull(config.getProperty("url")));
            System.out.println("✅ Session browser initialized successfully");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeSessionBrowser() {
        if (isSessionBased && driver != null) {
            System.out.println("🔚 Closing session-based browser");
            driver.quit();
            driver = null;
        }
    }

    // For individual test-based approach (each test gets fresh browser)
    @BeforeMethod(alwaysRun = true)
    @Parameters({"Browser"})
    public void setupBrowser(String Browser) throws IOException {
        if (!isSessionBased) {
            System.out.println("🔄 Setting up individual browser for test: " + Browser);
            createDriver(Browser);
            driver.get(Objects.requireNonNull(config.getProperty("url")));
            System.out.println("✅ Individual browser initialized successfully");

        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (!isSessionBased && driver != null) {
            System.out.println("🔚 Closing individual browser after test");
            driver.quit();
            driver = null;
        }
    }

    // In This Function We Select The Browser Need To Select.
    private void createDriver(String browserName) {

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Browser not supported: " + browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    // In This Function We Get The Browser Name From The Property Files And Run The Value
    public WebDriver initilizeDriver() {

        // Get browser from system property or properties file
        String browserName = config.getProperty("browser");
        if (browserName != null){
            createDriver(browserName);
        }else {
            throw new RuntimeException("Please Pass The BrowserValue");
        }
        return driver;
    }

}

// Load properties file
/*  private void loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream file = null;
        try {
            String propertiesPath = System.getProperty("user.dir") + "\\src\\main\\java\\resourse\\globalData.properties";
            file = new FileInputStream(propertiesPath);
            prop.load(file);
            log.info("Properties file loaded successfully from: {}", propertiesPath);
        } catch (IOException e) {
            log.error("Error loading properties file: ", e);
            throw e;
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    log.error("Error closing properties file: ", e);
                }
            }
        }
    } */