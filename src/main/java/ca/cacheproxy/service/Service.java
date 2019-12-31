package ca.cacheproxy.service;

import ca.cacheproxy.proxy.Cache;

import java.util.Date;
import java.util.List;

import static ca.cacheproxy.proxy.Cache.CacheType.FILE;
import static ca.cacheproxy.proxy.Cache.CacheType.IN_MEMORY;


public interface Service {
    @Cache(cacheType = IN_MEMORY, listList = 100_000)
    List<String> run(String item, Double value, Date date);

    @Cache(cacheType = FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    List<String> work(String item);

    @Cache(identityBy = {Integer.class})
    Integer sum(Integer one, Integer two);

    @Cache(listList = 2)
    List<Integer> getList(Integer length);

    String nonAnnotatedMethod();
}
