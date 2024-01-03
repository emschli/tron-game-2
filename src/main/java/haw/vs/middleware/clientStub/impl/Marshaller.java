package haw.vs.middleware.clientStub.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.JsonRequest;
import haw.vs.middleware.nameService.api.INameServiceHelper;

public class Marshaller extends Sender implements IClientStub {

    private INameServiceHelper nameServiceHelper;
    private ObjectMapper objectMapper;
    public byte[] marshall(Object[] args, String methodName) {

        JsonRequest request = new JsonRequest();
        request.setMethod(methodName);
        request.setParams(args);

        try {
            return objectMapper.writeValueAsString(request).getBytes();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void invoke(String methodName, Object[] args, int modus) {
        byte[] requestData = marshall(args, methodName);
//        String serverAddress = nameServiceHelper.lookup(methodName);
        //TODO
    }

    @Override
    public void invoke(String methodName, Object[] args, int modus, long stateId) {
        byte[] requestData = marshall(args, methodName);
//        String serverAddress = nameServiceHelper.lookup(methodName, stateId);
        //TODO
    }

    @Override
    public void invokeSpecific(long id, String methodName, int modus) {
        //TODO
    }
}
