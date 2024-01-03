package haw.vs.middleware.nameservice;

import haw.vs.middleware.nameService.impl.INameService;
import haw.vs.middleware.nameService.impl.NameServiceFactory;
import haw.vs.middleware.nameService.impl.exception.NameServiceLookupException;
import haw.vs.middleware.nameService.impl.exception.NameServiceBindException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static haw.vs.middleware.nameService.impl.NameServiceData.*;
import static org.junit.jupiter.api.Assertions.*;
public class NameServiceTest {
    public static String METHOD_NAME_1 = "methodName1";
    public static String METHOD_NAME_2 = "methodName2";
    public static String METHOD_NAME_3 = "methodName3";

    public static String IP_1 = "1.1.1.1";
    public static String IP_2 = "2.2.2.2";

    @Test
    public void testBindAndLookupStateless() throws NameServiceBindException, NameServiceLookupException {
        INameService nameService = NameServiceFactory.getNameService();
        assertDoesNotThrow(() -> nameService.bind(getMethodNames(), STATELESS, IP_1));
        String lookedUpIp = assertDoesNotThrow(() -> nameService.lookup(METHOD_NAME_1));

        assertEquals(IP_1, lookedUpIp);

        // Load Balancer Part
        nameService.bind(getMethodNames(), STATELESS, IP_2);
        String secondLookedUpIp = nameService.lookup(METHOD_NAME_2);
        String thirdLookedUpIp = nameService.lookup(METHOD_NAME_2);
        assertEquals(IP_2, thirdLookedUpIp);
    }

    @Test
    public void testBindAndLookupStateful() throws NameServiceLookupException {
        INameService nameService = NameServiceFactory.getNameService();
        assertDoesNotThrow(() -> nameService.bind(getMethodNames(), STATEFUL, IP_1));
        assertDoesNotThrow(() -> nameService.bind(getMethodNames(), STATEFUL, IP_2));

        assertThrowsExactly(NameServiceLookupException.class, () -> nameService.lookup(METHOD_NAME_2, 1));

        nameService.lookup(METHOD_NAME_1, 1);
        String ip = nameService.lookup(METHOD_NAME_2, 1);
        for (int i = 0; i < 10; i++) {
            assertEquals(ip, nameService.lookup(METHOD_NAME_2, 1));
        }
    }

    @Test
    public void testBindAndLookupSpecific() {
        INameService nameService = NameServiceFactory.getNameService();
        long specificID = assertDoesNotThrow(() -> nameService.bind(getMethodNames(), SPECIFIC, IP_1));
        String lookedUpIp = assertDoesNotThrow(() -> nameService.lookupSpecific(METHOD_NAME_1, specificID));

        assertEquals(IP_1, lookedUpIp);
    }

    public List<String> getMethodNames() {
        List<String> result = new ArrayList<>();
        result.add(METHOD_NAME_1);
        result.add(METHOD_NAME_2);
        result.add(METHOD_NAME_3);
        return result;
    }
}
