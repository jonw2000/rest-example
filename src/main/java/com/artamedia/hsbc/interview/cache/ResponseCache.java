package com.artamedia.hsbc.interview.cache;

import com.artamedia.hsbc.interview.model.Trade;

import java.util.concurrent.ExecutionException;

// Could also be used to cache to a backend repository, e.g. Mongo
public interface ResponseCache {

    Trade put(Trade t, String json);
    String get(Trade t) throws ExecutionException;
    boolean contains(Trade t) throws ExecutionException;
}
