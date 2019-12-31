package ca.cacheproxy;

import ca.cacheproxy.proxy.CacheProxy;
import ca.cacheproxy.proxy.Cacher;
import ca.cacheproxy.service.Service;
import ca.cacheproxy.service.ServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private CacheProxy cacheProxy;
    private Service cachedService;
    @BeforeEach
    public void setup() {
        cacheProxy = new CacheProxy(new Cacher());
        cachedService = cacheProxy.cache(new ServiceImpl());
    }

    @Test
    public void testCacheTime() {
        java.util.Date d = Date.from(Instant.now());
        String s = "abc";
        Double v = 1.9;
        long before = System.currentTimeMillis();
        System.out.println(cachedService.run(s, v, d));
        long between = System.currentTimeMillis();
        System.out.println(cachedService.run(s, v, d));
        long after = System.currentTimeMillis();
        assertTrue(between - before > 1000);
        assertTrue(after - between < 100);
    }

    @Test
    public void testIdentityBy() {
        assertEquals(3, cachedService.sum(1, 2));
        assertEquals(3, cachedService.sum(1, 2000));
    }

    @Test
    void testListLimit() {
        assertEquals(Arrays.asList(1, 2,3), cachedService.getList(1));
        assertEquals(Arrays.asList(1, 2), cachedService.getList(1));
    }


}