package com.artamedia.hsbc.interview.rest;

import com.artamedia.hsbc.interview.model.Trade;
import com.artamedia.hsbc.interview.model.TradeToken;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TradeProcessorTest {


    @Test
    public void testSave() {
        TradeProcessor proc = new TradeProcessor();

        Trade t = new Trade();
        t.setTradeId(1);
        t.setBuyer("Buffet");
        t.setSeller("Icahn");
        t.setPrice(100.3);
        TradeToken res = proc.save(t);

        assertThat(res.getToken(), is(notNullValue()));
        assertThat(res.getTime(), is(notNullValue()));
        assertThat(res.getResponse(), is("Trade saved successfully"));
    }
}
