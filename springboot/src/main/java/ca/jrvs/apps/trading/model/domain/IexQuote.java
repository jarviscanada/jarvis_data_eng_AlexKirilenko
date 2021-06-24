package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;


/**
 * https://iexcloud.io/docs/api/#quote
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "ticker",
    "lastPrice",
    "bidPrice",
    "bidSize",
    "askPrice",
    "askSize"
})
public class IexQuote {

  private String ticker;
  private Double lastPrice;
  private Double bidPrice;
  private Integer bidSize;
  private Double askPrice;
  private Integer askSize;

  public IexQuote() {

  }
  @JsonGetter("ticker")
  public String getTicker() {
    return ticker;
  }

  @JsonSetter("symbol")
  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @JsonGetter("lastPrice")
  public Double getLastPrice() {
    return lastPrice;
  }

  @JsonSetter("latestPrice")
  public void setLastPrice(Double lastPrice) {
    this.lastPrice = lastPrice;
  }

  @JsonGetter("bidPrice")
  public Double getBidPrice() {
    return bidPrice;
  }

  @JsonSetter("iexBidPrice")
  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  @JsonGetter("bidSize")
  public Integer getBidSize() {
    return bidSize;
  }

  @JsonSetter("iexBidSize")
  public void setBidSize(Integer bidSize) {
    this.bidSize = bidSize;
  }

  @JsonGetter("askPrice")
  public Double getAskPrice() {
    return askPrice;
  }

  @JsonSetter("iexAskPrice")
  public void setAskPrice(Double askPrice) {
    this.askPrice = askPrice;
  }

  @JsonGetter("askSize")
  public Integer getAskSize() {
    return askSize;
  }

  @JsonSetter("iexAskSize")
  public void setAskSize(Integer askSize) {
    this.askSize = askSize;
  }

  @Override
  public String toString() {
    return "IexQuote{" +
        "ticker='" + ticker + '\'' +
        ", lastPrice=" + lastPrice +
        ", bidPrice=" + bidPrice +
        ", bidSize=" + bidSize +
        ", askPrice=" + askPrice +
        ", askSize=" + askSize +
        '}';
  }
}
