package ca.cacheproxy.service;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @SneakyThrows
    @Override
    public List<String> run(String item, Double value, Date date) {
        Thread.sleep(1000);
        return Arrays.asList("a", "b");
    }

    @SneakyThrows
    @Override
    public List<String> work(String item) {
        Thread.sleep(1000);
        return Arrays.asList("c", "d");
    }

    @Override
    public Integer sum(Integer one, Integer two) {
        return one + two;
    }

    @Override
    public List<Integer> getList(Integer length) {
        return Arrays.asList(1, 2, 3);
    }

    @Override
    public String nonAnnotatedMethod() {
        return "blah";
    }
}
