package com.cepheid.training.ops;

import java.util.Map;

// Scenario 1: NullPointerException. A customer record can be absent from the
// cache, but this method assumes it is always present.
public class OrderLookupService {

    private final Map<String, String> customerCache;

    public OrderLookupService(Map<String, String> customerCache) {
        this.customerCache = customerCache;
    }

    public String getCustomerRegion(String customerId) {
        String cached = customerCache.get(customerId);
        return cached.toUpperCase(); // throws NPE when customerId is not cached
    }
}
