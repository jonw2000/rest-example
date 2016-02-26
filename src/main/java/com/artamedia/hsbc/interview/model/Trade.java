package com.artamedia.hsbc.interview.model;

import com.artamedia.hsbc.interview.model.json.TradeSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/***
 <Trade>

 <TradeId>123456</TradeId>

 <Buyer>EXAMPLE BUYER</Buyer>

 <Seller>EXAMPLE SELLER</Seller>

 <Price>105.5</Price>

 </Trade>
 */
@XmlRootElement(name = "Trade")
public class Trade {

    private static ObjectMapper mapper = new ObjectMapper();
    private Long tradeId;
    private Double price;
    private String buyer;
    private String seller;

    static {
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        SimpleModule mymod = new SimpleModule("MyModule", new Version(1, 0, 0, null, "com.artamedia.hsbc", "hsbc-rest"));
        mymod.addSerializer(new TradeSerializer(Trade.class));
        mapper.registerModule(mymod);
    }

    public Trade() {
    }

    public String toString() {
        return toJson();
    }

    @JsonCreator
    public String toJson() {
        String json;
        try {
            json = mapper.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            json = "Problem serializing trade";
        }
        return json;
    }

    @XmlElement( name = "TradeId")
    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }


    @XmlElement( name = "Buyer")
    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    @XmlElement( name = "Seller")
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @XmlElement( name = "Price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isValid() {
        return tradeId != null && buyer != null && seller != null && price != null;
    }
}
