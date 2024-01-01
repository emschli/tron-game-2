package haw.vs.middleware.clientStub.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.middleware.clientStub.api.IClientStub;
import haw.vs.middleware.common.JsonRequest;
import haw.vs.middleware.nameService.api.INameService;
import haw.vs.middleware.nameService.impl.NameServiceFactory;

public class Marshaller extends Sender implements IClientStub {


    private INameService nameService;
    private ObjectMapper objectMapper;

    public Marshaller(INameService nameService) {
        this.nameService = nameService;
        this.objectMapper = new ObjectMapper();
    }

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
        String serverAddress = nameService.lookup(methodName);
        switch (modus) {
            case 1 -> sendSynchronouslyTcp(serverAddress, requestData);
            case 2 -> sendAsynchronouslyTcp(serverAddress, requestData);
            case 3 -> sendAsynchronouslyUdp(serverAddress, requestData);
            default -> throw new IllegalArgumentException("Ungültiger Modus: " + modus);
        }
    }

    @Override
    public void invoke(String methodName, Object[] args, int modus, long stateId) {
        byte[] requestData = marshall(args, methodName);
        String serverAddress = nameService.lookup(methodName, stateId);
        switch (modus) {
            case 1 -> sendSynchronouslyTcp(serverAddress, requestData);
            case 2 -> sendAsynchronouslyTcp(serverAddress, requestData);
            case 3 -> sendAsynchronouslyUdp(serverAddress, requestData);

            default -> throw new IllegalArgumentException("Ungültiger Modus: " + modus);
        }
    }

    @Override
    public void invokeSpecific(long specificId, String methodName, Object[] args, int modus) {
        byte[] requestData = marshall(args, methodName);
        String specificServerAddress = nameService.lookup(specificId);

        switch (modus) {
            case 1 -> sendSynchronouslyTcp(specificServerAddress, requestData);
            case 2 -> sendAsynchronouslyTcp(specificServerAddress, requestData);
            case 3 -> sendAsynchronouslyUdp(specificServerAddress, requestData);

            default -> throw new IllegalArgumentException("Ungültiger Modus: " + modus);
        }
    }

}
