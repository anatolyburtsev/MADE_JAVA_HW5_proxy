package ca.cacheproxy;

import ca.cacheproxy.proxy.CacheProxy;
import ca.cacheproxy.proxy.Cacher;
import ca.cacheproxy.service.Service;
import ca.cacheproxy.service.ServiceImpl;

import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy(new Cacher());
        Service cachedService = cacheProxy.cache(new ServiceImpl());
        java.util.Date d = Date.from(Instant.now());
        String s = "abc";
        Double v = 1.0;
        System.out.println(cachedService.run(s, v, d));
        System.out.println(cachedService.run(s, v, d));

    }
}
