package com.artamedia.hsbc.interview.model;

import java.util.UUID;

public class TradeToken {

    private final String token;
    private final Long time;
    private String response;

    public TradeToken() {
        token = UUID.randomUUID().toString();
        time = System.currentTimeMillis();
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {  }

    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {  }
}
