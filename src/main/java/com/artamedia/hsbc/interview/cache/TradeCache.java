package com.artamedia.hsbc.interview.cache;

import com.artamedia.hsbc.interview.model.Trade;

import java.util.concurrent.ExecutionException;

public interface TradeCache {
    Long put(Long id, Trade trade);
    Trade get(Long id) throws ExecutionException;
    boolean contains(Long id) throws ExecutionException;
}
