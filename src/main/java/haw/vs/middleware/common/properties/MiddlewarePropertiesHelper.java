package haw.vs.middleware.common.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MiddlewarePropertiesHelper {
    private static String PROPERTIES_FILE = "middleware.properties";

    private static String SYNC_TCP_PORT = "sync_tcp_port";
    private static String ASYNC_TCP_PORT = "async_tcp_port";
    private static String ASYNC_UDP_PORT = "async_udp_port";

    private static Properties PROPERTIES = new Properties();

    /**
     * Set the properties file that should be used
     * @param filename of properties file located in resources folder
     */
    public static void setPropertiesFile(String filename) {
        PROPERTIES_FILE = filename;
    }

    public static int getSynchronousTcpPort()  throws MiddlewarePropertiesException {
        return getPort(SYNC_TCP_PORT);
    }

    public static int getAsynchronousTcpPort()  throws MiddlewarePropertiesException {
        return getPort(ASYNC_TCP_PORT);
    }
    public static int getAsynchronousUdpPort()  throws MiddlewarePropertiesException {
        return getPort(ASYNC_UDP_PORT);
    }

    private static int getPort(String portType) throws MiddlewarePropertiesException {
        load();
        String portString = PROPERTIES.getProperty(portType);

        if (portString == null) {
            throw new MiddlewarePropertiesException(String.format("No %s specified!", portType));
        }

        int port;
        try {
            port = Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            throw new MiddlewarePropertiesException(String.format("No valid %s !", portType));
        }

        return port;
    }

    private static void load() throws MiddlewarePropertiesException {
        try {
            PROPERTIES.load(new FileInputStream(getCompletePath()));
        } catch (IOException e) {
            throw new MiddlewarePropertiesException("Properties File not found!");
        }
    }
    private static String getCompletePath() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        return rootPath + PROPERTIES_FILE;
    }
}
