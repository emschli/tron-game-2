package haw.vs.common.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    private static String PROPERTIES_FILE = "app.properties";

    private static Properties PROPERTIES = new Properties();

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

    public static ComponentType getComponentType() throws PropertiesException {
        load();
        String componentType = PROPERTIES.getProperty("component_type");
        if (componentType == null) {
            throw new PropertiesException("No property named component_type!");
        }

        try {
            return ComponentType.valueOf(componentType);
        } catch (IllegalArgumentException e) {
            throw new PropertiesException("Invalid Value for Property component_type");
        }
    }

    public static int getPort() throws PropertiesException {
        return getPort(null);
    }

    /**
     * Use Only if the AppType is STANDALONE_WITH_MIDDLEWARE
     * @param componentType of Component to get Port for
     * @return the port number
     * @throws PropertiesException if properties file cannot be found or port property is missing
     */
    public static int getPort(ComponentType componentType) throws PropertiesException {
        load();
        AppType appType = getAppType();
        int port;
        String portKey = "port";
        if (appType == AppType.STANDALONE_WITH_MIDDLEWARE) {
            portKey = componentType.toString().toLowerCase() + "_port";
        }

        String portString = PROPERTIES.getProperty(portKey);
        if (portString != null) {
            port = Integer.parseInt(portString);
        } else {
            throw new PropertiesException(String.format("No property named %s!", portKey));
        }
        return port;
    }

    public static String getNameServiceIp() throws PropertiesException {
        load();
        String nameServiceIp = PROPERTIES.getProperty("nameservice_ip");
        if (nameServiceIp == null) {
            throw new PropertiesException("No Property named nameservice_ip");
        }
        return nameServiceIp;
    }

    public static int getNameServicePort() throws PropertiesException {
        load();
        String nameServicePortString = PROPERTIES.getProperty("nameservice_port");
        if (nameServicePortString != null) {
            return Integer.parseInt(nameServicePortString);
        } else {
            throw new PropertiesException("No Property named namservice_port");
        }
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
