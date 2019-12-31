package ca.cacheproxy.proxy.storages;

import ca.cacheproxy.proxy.Result;

public interface ResultsStorage {
    void putValue(Object[] args, Object value);
    Result getValue(Object[] args);
}
