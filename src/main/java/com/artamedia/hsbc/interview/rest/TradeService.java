package com.artamedia.hsbc.interview.rest;

import com.artamedia.hsbc.interview.cache.GuavaResponseCache;
import com.artamedia.hsbc.interview.cache.GuavaTradeCache;
import com.artamedia.hsbc.interview.cache.ResponseCache;
import com.artamedia.hsbc.interview.cache.TradeCache;
import com.artamedia.hsbc.interview.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;

@Path("/trade/service")
public class TradeService {

    private static final Logger logger = LoggerFactory.getLogger(TradeProcessor.class);
    private static final ResponseCache respCache = new GuavaResponseCache(72);
    private static final TradeCache tradeCache = new GuavaTradeCache(72);


    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(Trade trade) {
        logger.info("Received {}", trade);

        String json = trade.toJson();
        tradeCache.put(trade.getTradeId(), trade);
        logger.debug("Converted to {}", json);

        logger.info("Posting to REST service");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8081/v1/").path("process/trade");
        Response resp = target.request(MediaType.APPLICATION_JSON).post(Entity.json(trade), Response.class);

        String tokenJson = resp.readEntity(String.class);
        respCache.put(trade, tokenJson);
        logger.info("Done, received token {}", tokenJson);
        return tokenJson;
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("id") String id) {
        logger.info("Received {}", id);
        String resp;
        try {
            Long longId = Long.getLong(id);
            if (tradeCache.contains(longId)) {
                resp = tradeCache.get(longId).toJson();
            } else {
                resp = Response.status(404)
                        .entity("Trade not found")
                        .type(MediaType.APPLICATION_JSON)
                        .build().toString();
            }
        } catch (ExecutionException e) {
            resp = Response.status(404)
                    .entity("Trade not found")
                    .type(MediaType.APPLICATION_JSON)
                    .build().toString();
        }
        return resp;
    }
}
