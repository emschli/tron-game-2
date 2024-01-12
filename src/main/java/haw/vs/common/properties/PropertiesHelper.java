package haw.vs.common.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesHelper {
    private static String PROPERTIES_FILE = "app.properties";

    private static Properties PROPERTIES = new Properties();

    /**
     * Set the properties file that should be used
     * @param filename of properties file located in resources folder
     */
    public static synchronized void setPropertiesFile(String filename) {
        PROPERTIES_FILE = filename;
    }

    public static synchronized boolean isTest(ComponentType componentType) throws PropertiesException {
        load();
        String isTestString = PROPERTIES.getProperty(componentType.toString().toLowerCase() + "_test");
        return Boolean.parseBoolean(isTestString);
    }

    /**
     * Get AppType of App
     * @return the AppType
     * @throws PropertiesException if properties file cannot be found or there is no valid value
     */
    public static synchronized AppType getAppType() throws PropertiesException {
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
     * Test if Component should be deployed
      * @param componentType
     * @return true if it should be deployed and false if set to false or null
     * @throws PropertiesException if properties file cannot be found
     */
    public static synchronized boolean shouldBeDeployed(ComponentType componentType) throws PropertiesException {
        String componentKey = componentType.toString().toLowerCase();
        load();
        return Boolean.parseBoolean(PROPERTIES.getProperty(componentKey));
    }

    /**
     * Get a List of all the component types that should be deployed on this node
     * @return a List of ComponentTypes
     * @throws PropertiesException if properties file cannot be found
     */
    public synchronized static List<ComponentType> getAllComponents() throws PropertiesException {
        List<ComponentType> result = new ArrayList<>();
        load();

        for (ComponentType componentType : ComponentType.values()) {
            if(shouldBeDeployed(componentType)) {
                result.add(componentType);
            }
        }

        return result;
    }

    /**
     * Get Property for Key
     * @param key
     * @return Property for key or null if there is no entry
     * @throws PropertiesException if properties file cannot be found
     */
    public static synchronized String getProperty(String key) throws PropertiesException {
        load();
        return PROPERTIES.getProperty(key);
    }

    private static void load() throws PropertiesException {
        try {
            PROPERTIES.load(new FileInputStream(getCompletePath()));
        } catch (IOException e) {
            throw new PropertiesException("Properties File not found!");
        }
    }
    private static String getCompletePath() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        return rootPath + PROPERTIES_FILE;
    }
}
