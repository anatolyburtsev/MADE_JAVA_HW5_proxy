package ca.cacheproxy.proxy;

import lombok.AllArgsConstructor;

import java.lang.reflect.Proxy;

@AllArgsConstructor
public class CacheProxy {
    private final Cacher cacher;

    public <T> T cache(T target) {
        Class<T> iface = getInterface(target);
        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class[]{iface},
                new CacheProxyHandler(target, (Class<Object>) iface, cacher)
        );
    }

    private static <T> Class<T> getInterface(T target) {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if (interfaces.length != 1) {
            throw new RuntimeException("class should implement one and only one interface");
        }
        // TODO: why one? get interface in more reliable way
        return (Class<T>) interfaces[0];
    }
}
