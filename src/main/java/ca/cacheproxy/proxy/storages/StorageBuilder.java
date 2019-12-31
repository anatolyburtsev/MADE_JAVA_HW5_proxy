package ca.cacheproxy.proxy.storages;

import ca.cacheproxy.proxy.Cache;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StorageBuilder {
    public static ResultsStorage build(Cache annotation) {
        ResultsStorage storage;
        switch (annotation.cacheType()) {
            case FILE:
                storage = FileResultsStorage.builder()
                        .compress(annotation.zip())
                        .path(annotation.fileNamePrefix())
                        .build();
                break;
            case IN_MEMORY:
                storage = new InMemoryResultsStorage();
                break;
            default:
                throw new RuntimeException("Unknown cache type");
        }

        if (annotation.identityBy().length > 0) {
            storage = new IdentifyByWrapperStorage(storage, annotation.identityBy());
        }

        if (annotation.listList() > 0) {
            storage = new ListLimitWrapperStorage(storage, annotation.listList());
        }

        return storage;
    }
}
