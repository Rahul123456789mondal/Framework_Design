package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.time.Duration;
public class BaseTest {

    public Logger log = LoggerFactory.getLogger(BaseTest.class);
    public WebDriver driver;
    public Properties prop;
    public WebDriver initilizeDriver() throws IOException {

        // Load properties file
        loadProperties();

        // Get browser from system property or properties file
        String browserName = prop.getProperty("browser");
        createDriver(browserName);
        return driver;
    }

    // TestNG method to initialize driver before each test
    @BeforeMethod(alwaysRun = true)
    public void runBrowser() throws IOException {
        driver = initilizeDriver();
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void closeBrowser(){
        if (driver != null) {
            driver.quit();
            driver = null; // Set to null to avoid memory leaks
        }
    }


    // Load properties file
    private void loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream file = null;
        try {
            String propertiesPath = System.getProperty("user.dir") + "\\src\\main\\java\\resourse\\globalData.properties";
            file = new FileInputStream(propertiesPath);
            prop.load(file);
            log.info("Properties file loaded successfully from: " + propertiesPath);
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
    }

    private void createDriver(String browserName) {

        switch (browserName) {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }


}