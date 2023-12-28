package haw.vs.common.properties;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class PropertiesHelperTest {

    @Test
    public void testGetAppType() {
        AppType appType = assertDoesNotThrow(PropertiesHelper::getAppType);
        assertEquals(appType, AppType.STANDALONE);
    }

    @Test
    public void testComponentPortExists() {
        String port = assertDoesNotThrow(() -> PropertiesHelper.getPort(ComponentType.VIEW));
        assertEquals(port, "5000");
    }

    @Test
    public void testComponentPortDoesNotExist() {
        String port = assertDoesNotThrow(() -> PropertiesHelper.getPort(ComponentType.CONTROLLER));
        assertNull(port);
    }
}
