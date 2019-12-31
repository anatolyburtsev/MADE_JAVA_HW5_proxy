package ca.cacheproxy.proxy;

import ca.cacheproxy.proxy.storages.ResultsStorage;
import ca.cacheproxy.proxy.storages.StorageBuilder;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Cacher {
    private Map<String, ResultsStorage> cachedValues;
    private Map<Method, String> keys;

    public Cacher() {
        cachedValues = new HashMap<>();
        keys = new HashMap<>();
    }

    public void addNewClass(Class<Object> iface) {
        for (Method method : iface.getDeclaredMethods()) {
            Cache annotation = method.getDeclaredAnnotation(Cache.class);
            if (annotation == null) continue;
            ResultsStorage storage = StorageBuilder.build(annotation);
            String key = generateKey(method, annotation);
            cachedValues.put(key, storage);
        }
    }

    private String generateKey(Method method, Cache cache) {
        String key = cache.key().isEmpty() ? method.getName() : cache.key();
        keys.put(method, key);
        return key;
    }

    private String getKey(Method method) {
        return keys.get(method);
    }

    Object invokeOrGetFromCache(Method method, Object target, Object[] args) {
        if (!isMethodCachable(method)) {
            return invoke(method, target, args);
        }

        Result resultFromCache = lookForInCache(method, args);
        if (resultFromCache.isFound()) {
            // return result from cache
            return resultFromCache.getValue();
        } else {
            Object result = invoke(method, target, args);
            // cache result
            putToCache(method, args, result);
            return result;
        }
    }

    private void putToCache(Method method, Object[] args, Object value) {
        cachedValues.get(getKey(method)).putValue(args, value);
    }

    private Result lookForInCache(Method method, Object[] args) {
        return cachedValues.get(getKey(method)).getValue(args);
    }

    private boolean isMethodCachable(Method method) {
        // no annotation Cache
        return cachedValues.get(getKey(method)) != null;
    }

    @SneakyThrows
    private Object invoke(Method method, Object target, Object[] args) {
        return method.invoke(target, args);
    }

}
