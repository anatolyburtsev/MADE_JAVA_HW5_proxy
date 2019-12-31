package ca.cacheproxy.proxy.storages;

import ca.cacheproxy.proxy.Result;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class IdentifyByWrapperStorage implements ResultsStorage {
    private ResultsStorage realStorage;
    private Class[] identityBy;

    @Override
    public void putValue(Object[] args, Object value) {
        realStorage.putValue(filterArgs(args), value);
    }

    @Override
    public Result getValue(Object[] args) {
        return realStorage.getValue(filterArgs(args));
    }

    private Object[] filterArgs(Object[] args) {
        List<Object> newArgs = new ArrayList<>();
        int identityByIndex = 0;
        for (Object arg : args) {
            if (arg.getClass() == identityBy[identityByIndex]) {
                newArgs.add(arg);
                identityByIndex++;
            }
            if (identityByIndex == identityBy.length) break;
        }
        return newArgs.toArray();
    }
}
