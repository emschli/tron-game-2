package haw.vs.middleware.common.properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MiddlewarePropertiesHelperTest {
    @Before
    public void setup() {
        MiddlewarePropertiesHelper.setPropertiesFile("middleware/common/properties/middleware.properties");
    }

    @Test
    public void testPortMustBePresent() {
        assertThrows(MiddlewarePropertiesException.class, MiddlewarePropertiesHelper::getAsynchronousUdpPort);
    }

    @Test
    public void testPortIsValid() {
        assertThrows(MiddlewarePropertiesException.class, MiddlewarePropertiesHelper::getAsynchronousTcpPort);
    }

    @Test
    public void testGetPort() {
        int port = assertDoesNotThrow(MiddlewarePropertiesHelper::getSynchronousTcpPort);
        assertEquals(5000, port);
    }
}
