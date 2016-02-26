package com.artamedia.hsbc.interview.rest;

import com.artamedia.hsbc.interview.model.Trade;
import com.artamedia.hsbc.interview.model.TradeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/process")
public class TradeProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TradeProcessor.class);
    private static String MSG_SUCCESS = "Trade saved successfully";
    private static String MSG_FAILURE = "Trade not saved successfully";

    /***
     * Handle a Trade in JSON format and return a TradeToken
     * @param trade
     * @return
     */
    @POST
    @Path("trade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TradeToken save(Trade trade) {
        logger.info("Received {}", trade);
        TradeToken token = new TradeToken();
        if (trade.isValid()) {
            token.setResponse(MSG_SUCCESS);
            logger.info("Trade is valid");
        } else {
            token.setResponse(MSG_FAILURE);
            logger.info("Trade is invalid");
        }
        return token;
    }
}
