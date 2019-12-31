package ca.cacheproxy.proxy;

import lombok.Value;

@Value
public class Result {
    private boolean found;
    private Object value;
}
