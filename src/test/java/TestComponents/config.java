package TestComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {

    private static final Logger log = LoggerFactory.getLogger(config.class);
    private static Properties prop;

    private static void loadProperties() throws IOException {
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
    }

    // Get property value by key
    public static String getProperty(String key) {
        boolean isLoaded = false;
        try {
            loadProperties();
        } catch (IOException e) {
            log.error("Failed to load properties when getting key: {}", key, e);
            return null;
        }
        String value = prop.getProperty(key);
        if (value == null) {
            log.warn("Property key '{}' not found", key);
        }
        return value;
    }


}
