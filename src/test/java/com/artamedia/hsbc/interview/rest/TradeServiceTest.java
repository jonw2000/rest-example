package com.artamedia.hsbc.interview.rest;

import com.artamedia.hsbc.interview.model.Trade;
import com.artamedia.hsbc.interview.model.TradeToken;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TradeServiceTest {


    @Ignore
    @Test
    public void testSave() {
        Client mockClient = mock(Client.class);
        WebTarget mockTarget = mock(WebTarget.class);
        when(mockClient.target(anyString())).thenReturn(mockTarget);
        TradeService serv = new TradeService(mockClient);

        Trade t = new Trade();
        t.setTradeId(1);
        t.setBuyer("Buffet");
        t.setSeller("Icahn");
        t.setPrice(100.3);
        String res = serv.save(t);

        // Not finished
    }
}
