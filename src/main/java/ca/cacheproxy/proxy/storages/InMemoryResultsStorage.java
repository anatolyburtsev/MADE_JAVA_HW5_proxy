package ca.cacheproxy.proxy.storages;

import ca.cacheproxy.proxy.Cache;
import ca.cacheproxy.proxy.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InMemoryResultsStorage implements ResultsStorage {
    private final Map<String, Object> values;

    public InMemoryResultsStorage() {
        values = new HashMap<>();
    }

    @Override
    public void putValue(Object[] args, Object value) {
        values.put(Arrays.toString(args), value);
    }

    @Override
    public Result getValue(Object[] args) {
        Object value = values.get(Arrays.toString(args));
        if (value != null) {
            return new Result(true, value);
        } else {
            return new Result(false, null);
        }
    }
}
