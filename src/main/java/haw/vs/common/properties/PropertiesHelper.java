package haw.vs.common.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    private static String PROPERTIES_FILE = "app.properties";

    private static Properties PROPERTIES = new Properties();

    /**
     * Get AppType of App
     * @return the AppType
     * @throws PropertiesException if properties file cannot be found or there is no valid value
     */
    public static AppType getAppType() throws PropertiesException {
        load();
        String appType = PROPERTIES.getProperty("app_type");
        if (appType == null) {
            throw new PropertiesException("No property named app_type!");
        }

        try {
            return AppType.valueOf(appType);
        } catch (IllegalArgumentException e) {
            throw new PropertiesException("Invalid Value for Property app_type");
        }
    }

    /**
     * Use to get Port for componentType
     * @param componentType of Component to get Port for
     * @return the port number or null if there is no entry
     * @throws PropertiesException if properties file cannot be found
     */
    public static String getPort(ComponentType componentType) throws PropertiesException {
        load();
        String portKey = componentType.toString().toLowerCase() + "_port";
        return PROPERTIES.getProperty(portKey);
    }

    /**
     * Get Property for Key
     * @param key
     * @return Property for key or null if there is no entry
     * @throws PropertiesException if properties file cannot be found
     */
    public static String getProperty(String key) throws PropertiesException {
        load();
        return PROPERTIES.getProperty(key);
    }

    private static void load() throws PropertiesException {
        if (PROPERTIES.isEmpty()) {
            try {
                PROPERTIES.load(new FileInputStream(getCompletePath()));
            } catch (IOException e) {
                throw new PropertiesException("Properties File not found!");
            }
        }
    }
    private static String getCompletePath() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        return rootPath + PROPERTIES_FILE;
    }
}
