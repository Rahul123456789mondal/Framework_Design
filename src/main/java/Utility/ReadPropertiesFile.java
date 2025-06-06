package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertiesFile {

    private FileInputStream fileName ;
    private Properties props ;

    public ReadPropertiesFile(String filePath ){

        try {
            fileName = new FileInputStream("."+File.separator+filePath);
            props = new Properties();
            props.load(fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPropertyKey(String key){
        return props.getProperty(key);
    }

    public String getBrowserName(){
        String browserName = getPropertyKey("browser");
        if (browserName!= null){
            return browserName;
        }else {
            throw new RuntimeException("Please Configure The BrowserName First");
        }
    }
    public String getUrl(){
        String url = getPropertyKey("url");
        if (url!= null){
            return url;
        }else {
            throw new RuntimeException("Please Configure The Url First");
        }
    }

}
