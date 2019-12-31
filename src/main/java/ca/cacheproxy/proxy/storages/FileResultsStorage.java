package ca.cacheproxy.proxy.storages;

import ca.cacheproxy.proxy.Cache;
import ca.cacheproxy.proxy.Result;
import jdk.jshell.spi.ExecutionControl;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileResultsStorage implements ResultsStorage {
    private boolean compress;
    private String path;

    @Override
    public void putValue(Object[] args, Object value) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Result getValue(Object[] args) {
        throw new RuntimeException("not implemented");
    }
}
