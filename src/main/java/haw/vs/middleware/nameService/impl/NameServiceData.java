package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.common.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameServiceData {
    private static NameServiceData INSTANCE;

    private static Long SPECIFIC_ID_COUNTER = 1L;
    private static Long METHOD_ID_COUNTER = 1L;
    private static Long BUNDLE_ID_COUNTER = 1L;

    public static int STATELESS = 0;
    public static int STATEFUL = 1;
    public static int SPECIFIC = 2;
    public static int STATE_INITIALIZING = 3;

    private final Map<Pair<Long,String>, String> specificMap;

    private final Map<Long, MethodObject> mainMap;
    private final Map<Pair<Long, String>, Long> stateMap;

    private final Map<String, List<Long>> methodNameAccessMap;
    private final Map<Long, List<Long>> bundleIdAccessMap;

    private NameServiceData() {
        specificMap = new HashMap<>();
        mainMap = new HashMap<>();
        stateMap = new HashMap<>();
        methodNameAccessMap = new HashMap<>();
        bundleIdAccessMap = new HashMap<>();
    }

    public static NameServiceData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NameServiceData();
        }

        return INSTANCE;
    }

    public String getFromSpecificMap(String methodName, long specificId) {
        Pair<Long, String> key = new Pair<>(specificId, methodName);
         return specificMap.get(key);
    }

    public List<Long> getMethodIdsForMethodName(String methodName) {
        return methodNameAccessMap.get(methodName);
    }

    public long addStatelessMethods(List<String> methodNames, String ip) {
        long bundleId = BUNDLE_ID_COUNTER;
        BUNDLE_ID_COUNTER++;

        for (String methodName: methodNames) {
            MethodObject methodObject = createNewMethodEntry(methodName, bundleId, STATELESS, ip);
            mainMap.put(methodObject.getMethodId(), methodObject);

            addEntryToMethodNameAccessMap(methodObject);
            addEntryToBundleIdAccessMap(methodObject);
        }

        return bundleId;
    }

    public long addStatefulMethods(List<String> methodNames, String ip) {
        long bundleId = BUNDLE_ID_COUNTER;
        BUNDLE_ID_COUNTER++;

        //First Method is state_initializing
        MethodObject stateInitializingMethod = createNewMethodEntry(methodNames.get(0), bundleId, STATE_INITIALIZING, ip);
        mainMap.put(stateInitializingMethod.getMethodId(), stateInitializingMethod);
        addEntryToMethodNameAccessMap(stateInitializingMethod);
        addEntryToBundleIdAccessMap(stateInitializingMethod);

        for (int i = 1; i < methodNames.size() ; i++) {
            MethodObject methodObject = createNewMethodEntry(methodNames.get(i), bundleId, STATEFUL, ip);
            mainMap.put(methodObject.getMethodId(), methodObject);

            addEntryToMethodNameAccessMap(methodObject);
            addEntryToBundleIdAccessMap(methodObject);
        }

        return bundleId;
    }

    public long addSpecificMethods(List<String> methodNames, String ip) {
        long specificId = SPECIFIC_ID_COUNTER;
        SPECIFIC_ID_COUNTER++;

        for (String methodName: methodNames) {
            specificMap.put(new Pair<>(specificId, methodName), ip);
        }

        return specificId;
    }

    public Integer getMethodType(String methodName) {
        List<Long> methodIds = methodNameAccessMap.get(methodName);

        if (methodIds == null || methodIds.isEmpty()) {
            return null;
        }

        Long methodId = methodIds.get(0);
        return mainMap.get(methodId).getMethodType();
    }

    public MethodObject getMethodFromMainMap(Long methodId) {
        return mainMap.get(methodId);
    }

    public List<Long> getMethodIdsForBundleId(Long bundleId) {
        return bundleIdAccessMap.get(bundleId);
    }

    public void removeFromStateMap(Long stateId, String methodName) {
        Pair<Long, String> key = new Pair<>(stateId, methodName);
        stateMap.remove(key);
    }

    public void addToStateMap(Long stateId, String methodName, long methodId) {
        Pair<Long, String> key = new Pair<>(stateId, methodName);
        stateMap.put(key, methodId);
    }

    public Long getMethodIdFromStateMap(Long stateId, String methodName) {
        Pair<Long, String> key = new Pair<>(stateId, methodName);
        return stateMap.get(key);
    }

    private MethodObject createNewMethodEntry(String methodName, long bundleId, int type, String ip) {
        long methodId = METHOD_ID_COUNTER;
        METHOD_ID_COUNTER++;

        return new MethodObject(methodId, bundleId, methodName, type, ip);
    }

    private void addEntryToMethodNameAccessMap(MethodObject methodObject) {
        if (!methodNameAccessMap.containsKey(methodObject.getMethodName())) {
            methodNameAccessMap.put(methodObject.getMethodName(), new ArrayList<>());
        }

        methodNameAccessMap.get(methodObject.getMethodName()).add(methodObject.getMethodId());
    }

    private void addEntryToBundleIdAccessMap(MethodObject methodObject) {
        if (!bundleIdAccessMap.containsKey(methodObject.getBundleId())) {
            bundleIdAccessMap.put(methodObject.getBundleId(), new ArrayList<>());
        }

        bundleIdAccessMap.get(methodObject.getBundleId()).add(methodObject.getMethodId());
    }
}
