package haw.vs.middleware.serverStub.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import haw.vs.middleware.common.JsonRequest;
import haw.vs.middleware.common.Pair;
import haw.vs.middleware.nameService.api.INameServiceHelper;
import haw.vs.middleware.nameService.api.NameServiceHelperFactory;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;
import haw.vs.middleware.serverStub.api.ICaller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Caller implements ICaller, Runnable {
    private INameServiceHelper nameServiceHelper;
    private static Caller instance;

    private Map<String, Pair<Method, Object>> calleeMap;
    private Lock lock;
    private ObjectMapper objectMapper;
    private ReceiveQueue receiveQueue;

    private Caller() {
        calleeMap = new HashMap<>();
        lock = new ReentrantLock();
        objectMapper = new ObjectMapper();
        this.receiveQueue = ReceiveQueue.getInstance();
        this.nameServiceHelper = NameServiceHelperFactory.getNameServiceHelper();
    }

    public static synchronized Caller getInstance() {
        if (instance == null) {
            instance = new Caller();
        }
        return instance;
    }

    @Override
    public long register(List<Method> methods, Object callee, int type) throws NameServiceException {
        lock.lock();
        long id;
        try {
            for (Method method : methods) {
                calleeMap.put(method.getName(),  new Pair<>(method, callee));
            }
            List<String> methodNames = methods.stream().map(Method::getName).collect(Collectors.toList());
            id = nameServiceHelper.bind(methodNames, type);
        } finally {
            lock.unlock();
        }
        return id;
    }

    @Override
    public void callSynchronously(byte[] data) {
        JsonRequest request = unmarshall(data);
        makeCall(request);
    }

    private JsonRequest unmarshall(byte[] data) {
        String jsonString = null;
        try {
            System.out.println("Unmarshalling: ");
            jsonString = new String(data, StandardCharsets.UTF_8);
            // Logging
            System.out.println("Received JSON data: " + jsonString);
            JsonRequest request = objectMapper.readValue(data, JsonRequest.class);
            System.out.println("Unmarschalling success "+ request.toString());

            return request;
        } catch (IOException e) {
            System.out.println("Error during unmarshalling: " + e.getMessage());
            System.out.println("JSON String: " + jsonString);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @VisibleForTesting
    private void makeCall(JsonRequest request) {
        Object callee;
        lock.lock();
        try {
            //look whom to call
            Pair<Method, Object> pair = calleeMap.get(request.getMethodname());
            if (pair == null) {
                // Logging
                System.out.println("Method not found: " + request.getMethodname());
                return;
            }
            if (pair.getKey() == null || pair.getValue() == null) {
                // Logging
                System.out.println("Invalid Pair: " + pair);
                return;
            }
            Method method = pair.getKey();
            callee = pair.getValue();

            Class[] argumentTypes = method.getParameterTypes();
            List<Object> args = new ArrayList<>();

            for (int i = 0; i < request.getParams().length; i++) {
                Object param = request.getParams()[i];
                Class paramClass = argumentTypes[i];

                String jsonString = objectMapper.writeValueAsString(param);
                Object arg = objectMapper.readValue(jsonString, paramClass);
                args.add(arg);
            }

            method.invoke(callee, args.toArray());

        } catch (InvocationTargetException e) {
            System.out.println("Error in method invocation: " + e.getMessage());

            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());

            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            System.out.println("Error during JSON processing: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            //take stuff from receiveQueue
            byte[] data = new byte[0];
            try {
                data = receiveQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //process it
            JsonRequest request = unmarshall(data);
            makeCall(request);
        }
    }
}
