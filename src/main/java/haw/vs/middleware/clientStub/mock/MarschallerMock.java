package haw.vs.middleware.clientStub.mock;

import haw.vs.middleware.clientStub.api.IClientStub;
import java.util.Arrays;

public class MarschallerMock implements IClientStub {

    @Override
    public void invoke(String methodName, int modus, Object... args) {
        System.out.printf("MarshallerMock: invoke(%s, %s, %s)\n", methodName, Arrays.toString(args), modus);
    }

    @Override
    public void invoke(String methodName, int modus, long stateId, Object... args) {
        System.out.printf("MarshallerMock: invoke(%s, %s, %s, %s)\n", methodName, Arrays.toString(args), modus, stateId);
    }

    @Override
    public void invokeSpecific(long id, String methodName, int modus, Object... args) {
        System.out.printf("MarshallerMock: invokeSpecific(%s, %s, %s, %s)\n", id, methodName,Arrays.toString(args), modus);
    }
}
