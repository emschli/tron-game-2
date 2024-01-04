package haw.vs.middleware.serverStub.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haw.vs.middleware.common.JsonRequest;
import haw.vs.middleware.nameService.api.INameService;
import haw.vs.middleware.nameService.impl.NameServiceFactory;
import haw.vs.middleware.serverStub.api.ICallee;
import haw.vs.middleware.serverStub.api.ICaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caller implements ICaller, Runnable {


    private NameServiceFactory nameServiceFactory;
    //private INameService nameService;
    private static Caller instance;
    private Map<String, ICallee> calleeMap;
    private Lock lock;
    private ObjectMapper objectMapper;
    private ReceiveQueue receiveQueue;

    private Caller() {
        calleeMap = new HashMap<>();
        lock = new ReentrantLock();
        objectMapper = new ObjectMapper();
        this.receiveQueue = ReceiveQueue.getInstance();

    }

    public static synchronized Caller getInstance() {
        if (instance == null) {
            instance = new Caller();
        }
        return instance;
    }

    @Override
    public void register(List<String> methodNames, ICallee callee, int type) {
        lock.lock();
        try {
            for (String method : methodNames) {
                calleeMap.put(method, callee);
            }
        } finally {
            lock.unlock();
        }
        //TODO shouldn't the type be given in the bind-method?
        callee.setId(nameServiceFactory.getNameService().bind(methodNames));
    }

    @Override
    public void callSynchronously(byte[] data) {
        JsonRequest request = unmarshall(data);
        makeCall(request);
    }

    private JsonRequest unmarshall(byte[] data) {
        try {
            String jsonString = new String(data);
            return objectMapper.readValue(jsonString, JsonRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private INameService getNameService() {
        return nameServiceFactory.getNameService();
    }

    public void setNameServiceFactory(NameServiceFactory nameServiceFactory) {
        this.nameServiceFactory = nameServiceFactory;
    }

    private void makeCall(JsonRequest request) {
        ICallee callee;
        lock.lock();
        try {
            //look whom to call
            callee = calleeMap.get(request.getMethod());
            // TODO zweiter Param!
            callee.call(request.getMethod(), (String) request.getParams());
        } finally{
            lock.unlock();
        }
    }

    @Override
    public void run() {
        //TODO
        while (true) {
            //take stuff from receiveQueue
            byte[] data = receiveQueue.take();
            //process it
            JsonRequest request = unmarshall(data);
            makeCall(request);
        }
    }
}
