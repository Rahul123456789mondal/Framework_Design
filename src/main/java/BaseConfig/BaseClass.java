package BaseConfig;

import Utility.ReadPropertiesFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class BaseClass {

    private WebDriver driver ;
    ReadPropertiesFile readProp;

    public BaseClass(String path){
        readProp = new ReadPropertiesFile(path);
    }

    public void setupBrowser(String browser, String url){

        switch (browser.toLowerCase()){

            case "Chrome" :
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                break;

            case "Firefox" :
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                break;

            default:
                throw new RuntimeException("Please Do The BrowserConfig First");
        }

        driver.get(url);

    }

    public void intlizeDriver(){
        setupBrowser(readProp.getBrowserName(), readProp.getUrl());
    }
    public WebDriver getDriver(){
        return driver;
    }


}
