package haw.vs.common.properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PropertiesHelperTest {

    @Before
     public void setup() {
        PropertiesHelper.setPropertiesFile("common/properties_helper_test.properties");
    }
    @Test
    public void testGetAppType() {
        AppType appType = assertDoesNotThrow(PropertiesHelper::getAppType);
        assertEquals(appType, AppType.DISTRIBUTED);
    }

    @Test
    public void testComponentIsSetToTest() {
        boolean isSetToTest = assertDoesNotThrow(() -> PropertiesHelper.isTest(ComponentType.CONTROLLER));
        assertTrue(isSetToTest);
    }

    @Test
    public void testComponentIsNotSetToTest() {
        boolean isSetToTest = assertDoesNotThrow(() -> PropertiesHelper.isTest(ComponentType.VIEW));
        assertFalse(isSetToTest);
    }

    @Test
    public void testComponentSetToBeDeployed() {
        boolean isSetToBeDeployed = assertDoesNotThrow(() -> PropertiesHelper.shouldBeDeployed(ComponentType.MATCH_MANAGER));
        assertTrue(isSetToBeDeployed);
    }

    @Test
    public void testComponentNotSetToBeDeployed() {
        boolean isSetToBeDeployed = assertDoesNotThrow(() -> PropertiesHelper.shouldBeDeployed(ComponentType.VIEW));
        assertFalse(isSetToBeDeployed);
    }

    @Test
    public void testGetAllComponents() {
        List<ComponentType> result = assertDoesNotThrow(PropertiesHelper::getAllComponents);
        List<ComponentType> expected = new ArrayList<>();
        expected.add(ComponentType.MATCH_MANAGER);
        expected.add(ComponentType.GAME_LOGIC);

        assertEquals(expected, result);
    }
}
