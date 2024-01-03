package haw.vs.middleware.nameService.impl.nameservicehelper;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import haw.vs.middleware.common.Pair;
import haw.vs.middleware.nameService.api.INameServiceHelper;
import haw.vs.middleware.nameService.impl.exception.NameServiceException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class NameServiceHelperCachingProxy implements INameServiceHelper {
    private final NameServiceHelper nameServiceHelper;

    private final LoadingCache<Pair<Long, String>, String> specificCache;

    public NameServiceHelperCachingProxy(NameServiceHelper nameServiceHelper) {
        this.nameServiceHelper = nameServiceHelper;
        CacheLoader<Pair<Long, String>, String> cacheLoader = new CacheLoader<Pair<Long, String>, String>() {
            @Override
            public String load(Pair<Long, String> key) throws Exception {
                return nameServiceHelper.lookupSpecific(key.getValue(), key.getKey());
            }
        };

        specificCache = CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.HOURS)
                .build(cacheLoader);
    }

    @Override
    public long bind(List<String> methodNames, int type) throws NameServiceException {
        return nameServiceHelper.bind(methodNames, type);
    }

    @Override
    public String lookup(String methodName) throws NameServiceException {
        return nameServiceHelper.lookup(methodName);
    }

    @Override
    public String lookup(String methodName, long stateId) throws NameServiceException {
        return nameServiceHelper.lookup(methodName, stateId);
    }

    @Override
    public String lookupSpecific(String methodName, long specificId) throws NameServiceException {
        Pair<Long, String> key = new Pair<>(specificId, methodName);
        String lookedUpValue;
        try {
            lookedUpValue = specificCache.get(key);
        } catch (ExecutionException e) {
            throw new NameServiceException("Remote NameService replied with error");
        }

        return lookedUpValue;
    }
}
