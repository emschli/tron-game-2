package haw.vs.middleware.clientStub.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.JsonRequest;
import haw.vs.middleware.common.exceptions.InvokeFailedException;
import haw.vs.middleware.nameService.api.INameServiceHelper;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

public class Marshaller implements IClientStub {

    private INameServiceHelper nameServiceHelper;
    private ObjectMapper objectMapper;
    private Sender sender;

    public Marshaller(INameServiceHelper nameServiceHelper) {
        this.nameServiceHelper = nameServiceHelper;
        this.objectMapper = new ObjectMapper();
        this.sender = new Sender();
    }

    public byte[] marshall(Object[] args, String methodName) {

        JsonRequest request = new JsonRequest();
        request.setMethod(methodName);
        request.setParams(args);

        try {
            return objectMapper.writeValueAsBytes(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void invoke(String methodName, int modus, Object... args) throws NameServiceException, InvokeFailedException {
        byte[] requestData = marshall(args, methodName);
        String serverAddress = nameServiceHelper.lookup(methodName);
        switch (modus) {
            case 1:
                sender.sendSynchronouslyTcp(serverAddress, requestData);
                break;
            case 2:
                sender.sendAsynchronouslyTcp(serverAddress, requestData);
                break;
            case 3:
                sender.sendAsynchronouslyUdp(serverAddress, requestData);
                break;
            default:
                throw new IllegalArgumentException("Ungültiger Modus: " + modus);
        }
    }

    @Override
    public void invoke(String methodName, int modus, long stateId, Object... args) throws NameServiceException, InvokeFailedException {
        byte[] requestData = marshall(args, methodName);
        String serverAddress = nameServiceHelper.lookup(methodName, stateId);
        switch (modus) {
            case 1:
                sender.sendSynchronouslyTcp(serverAddress, requestData);
                break;
            case 2:
                sender.sendAsynchronouslyTcp(serverAddress, requestData);
                break;
            case 3:
                sender.sendAsynchronouslyUdp(serverAddress, requestData);
                break;
            default:
                throw new IllegalArgumentException("Ungültiger Modus: " + modus);
        }
    }

    @Override
    public void invokeSpecific(long specificId, String methodName, int modus, Object... args) throws InvokeFailedException {
        byte[] requestData = marshall(args, methodName);
        String specificServerAddress = null;
        try {
            specificServerAddress = nameServiceHelper.lookupSpecific(methodName, specificId);
        } catch (NameServiceException e) {

            //TODO exception nach oben weiterleiten???
            e.printStackTrace();
        }

        switch (modus) {
            case 1:
                sender.sendSynchronouslyTcp(specificServerAddress, requestData);
                break;
            case 2:
                sender.sendAsynchronouslyTcp(specificServerAddress, requestData);
                break;
            case 3:
                sender.sendAsynchronouslyUdp(specificServerAddress, requestData);
                break;
            default:
                throw new IllegalArgumentException("Ungültiger Modus: " + modus);
        }
    }

}
