package ca.cacheproxy.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class CacheProxyHandler implements InvocationHandler {
    private final Object target;
    private final Cacher cacher;

    public CacheProxyHandler(Object target, Class<Object> iface, Cacher cacher) {
        this.target = target;
        this.cacher = cacher;
        cacher.addNewClass(iface);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return cacher.invokeOrGetFromCache(method, target, args);
    }

}
