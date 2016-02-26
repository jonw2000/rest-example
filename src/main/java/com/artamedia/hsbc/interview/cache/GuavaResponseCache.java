package com.artamedia.hsbc.interview.cache;

import com.artamedia.hsbc.interview.model.Trade;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaResponseCache implements ResponseCache {

    private final LoadingCache<Trade, String> trades;

    public GuavaResponseCache(int expiryInHours) {
        trades = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryInHours, TimeUnit.HOURS)
                .build(
                        new CacheLoader<Trade, String>() {
                            public String load(Trade t) {
                                return t.toJson();
                            }
                        });
    }

    public Trade put(Trade t, String json) {
        trades.put(t, json);
        return t;
    }

    public String get(Trade t) throws ExecutionException {
        return trades.get(t);
    }

    public boolean contains(Trade t) throws ExecutionException {
        return trades.get(t) != null;
    }
}
