package com.artamedia.hsbc.interview.cache;

import com.artamedia.hsbc.interview.model.Trade;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaTradeCache implements TradeCache {

    private final Cache<Long, Trade> trades;

    public GuavaTradeCache(int expiryInHours) {
        trades = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryInHours, TimeUnit.HOURS)
                .build();
    }

    public Long put(Long id, Trade trade) {
        trades.put(id, trade);
        return id;
    }

    public Trade get(Long id) throws ExecutionException {
        return trades.get(id, null);
    }

    public boolean contains(Long id) throws ExecutionException {
        return trades.get(id, null) != null;
    }
}
