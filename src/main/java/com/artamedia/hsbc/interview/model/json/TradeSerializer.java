package com.artamedia.hsbc.interview.model.json;

import com.artamedia.hsbc.interview.model.Trade;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TradeSerializer extends StdSerializer<Trade> {

    public TradeSerializer(Class<Trade> t) {
        super(t);
    }


    @Override
    public void serialize(Trade trade, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", trade.getTradeId());
        jgen.writeStringField("customer", trade.getBuyer());
        jgen.writeStringField("trader", trade.getSeller());
        jgen.writeNumberField("price", trade.getPrice());
        jgen.writeEndObject();
    }
}
