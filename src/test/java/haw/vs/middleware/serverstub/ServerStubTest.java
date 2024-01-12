package haw.vs.middleware.serverstub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.middleware.common.JsonRequest;
import haw.vs.middleware.common.exceptions.MethodNameAlreadyExistsException;
import haw.vs.middleware.common.properties.MiddlewarePropertiesHelper;
import haw.vs.middleware.nameService.impl.NameServiceThread;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.impl.Caller;
import haw.vs.middleware.serverStub.impl.ServerStubFacade;

public class ServerStubTest {
    public static void main(String[] args) throws NameServiceException, JsonProcessingException, MethodNameAlreadyExistsException {
        MiddlewarePropertiesHelper.setPropertiesFile("middleware/middleware.properties");

        //Start NameService
        NameServiceThread nameServiceThread = new NameServiceThread();
        Thread nameService = new Thread(nameServiceThread);
        nameService.start();

        //Register caller
        TestCallee testCallee = new TestCallee(new ServerStubFacade());
        testCallee.register();

        //Build Request
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.setMethodname("foo");
        Object[] params = new Object[] {
                1L,
                new TestParamObject(42, "blupp!")
        };
        jsonRequest.setParams(params);

        //Marshall data
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] marshalledData = objectMapper.writeValueAsString(jsonRequest).getBytes();

        // Test call
        Caller caller = Caller.getInstance();
        caller.callSynchronously(marshalledData);
    }
}
