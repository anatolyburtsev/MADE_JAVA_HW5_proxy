package ca.cacheproxy.proxy.storages;

import ca.cacheproxy.proxy.Result;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ListLimitWrapperStorage implements ResultsStorage {
    private ResultsStorage realStorage;
    private int maxLength;

    @Override
    public void putValue(Object[] args, Object value) {
        if (value instanceof List<?>) {
            value = ((List) value).stream().limit(maxLength).collect(Collectors.toList());
        }
        realStorage.putValue(args, value);
    }

    @Override
    public Result getValue(Object[] args) {
        return realStorage.getValue(args);
    }
}
