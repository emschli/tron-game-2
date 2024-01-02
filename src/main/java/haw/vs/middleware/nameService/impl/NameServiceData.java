package haw.vs.middleware.nameService.impl;

import haw.vs.middleware.common.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameServiceData {
    private static NameServiceData INSTANCE;

    private static Long SPECIFIC_ID_COUNTER = 1L;
    private static Long METHOD_ID_COUNTER = 1L;
    private static Long BUNDLE_ID_COUNTER = 1L;

    private Map<Long, String> specificMap;

    private Map<Long, MethodObject> mainMap;
    private Map<Pair<Long, String>, Long> stateMap;

    private Map<String, List<Long>> methodNameAccessMap;
    private Map<Long, List<Long>> bundleIdAccessMap;

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
}
