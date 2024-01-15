package haw.vs.middleware.serverstub;

import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.common.ICallee;
import haw.vs.middleware.serverStub.api.IServerStub;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestCallee implements ICallee {
    private final IServerStub serverStub;

    public TestCallee(IServerStub serverStub) {
        this.serverStub = serverStub;
    }

    @Override
    public void register() throws NameServiceException, MethodNameAlreadyExistsException {
        try {
            List<Method> methods = new ArrayList<>();
            Method method = this.getClass().getMethod("foo", long.class, TestParamObject.class);
            methods.add(method);
            serverStub.register(methods, this, 1);
        } catch (NoSuchMethodException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public void foo(long id, TestParamObject testParamObject) {
        System.out.println(String.format("foo with %s and %s", id, testParamObject));
    }
}
