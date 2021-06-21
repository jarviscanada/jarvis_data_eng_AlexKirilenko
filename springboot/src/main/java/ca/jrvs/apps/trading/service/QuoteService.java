package ca.jrvs.apps.trading.service;


import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao qouteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao qouteDao, MarketDataDao marketDataDao) {
    this.qouteDao = qouteDao;
    this.marketDataDao = marketDataDao;
  }


  /**
   * Updates quote table against IEX source
   *
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException for invalid input
   */
  public void updateMarketData() {
    List<Quote> storedQuotes = qouteDao.findAll();
    List<String> tickers = storedQuotes.stream()
        .map(quote -> quote.getTicker())
        .collect(Collectors.toList());
    saveQuotes(tickers);
  }

  /**
   * Helper method. Maps IexQuote to Quote
   * @param iexQuote
   * @return
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote quote = new Quote();
    quote.setTicker(iexQuote.getTicker());
    quote.setLastPrice(iexQuote.getLastPrice() == null ? 0.0 : iexQuote.getLastPrice());
    quote.setAskPrice(iexQuote.getAskPrice() == null ? 0.0 : iexQuote.getAskPrice());
    quote.setAskSize(iexQuote.getAskSize() == null ? 0 : iexQuote.getAskSize());
    quote.setBidPrice(iexQuote.getBidPrice() == null ? 0.0 : iexQuote.getBidPrice());
    quote.setBidSize(iexQuote.getBidSize() == null ?  0  : iexQuote.getBidSize());
    return quote;
  }

  /**
   * Validates and saves given tickers to quote table
   * @param tickers list of tickers
   * @throws IllegalArgumentException if ticket is not found in IEX
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    List<Quote> quotes = new ArrayList<>(tickers.size());
    for (String ticker : tickers) {
      Quote quoteReceived = saveQuote(ticker);
      quotes.add(quoteReceived);
    }
    return quotes;
  }

  /**
   * Finds an IexQuote
   * @param ticker id
   * @return
   * @throws IllegalArgumentException if ticker is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }

  /**
   * Helper method
   * @param ticker
   * @return
   */
  public Quote saveQuote(String ticker) {
    IexQuote iexQuote = findIexQuoteByTicker(ticker);
    Quote quoteReceived = buildQuoteFromIexQuote(iexQuote);
    return saveQuote(quoteReceived);
  }

  /**
   * Update a given quote to quote table without validation
   * @param quote
   * @return
   */
  public Quote saveQuote(Quote quote) {
    return qouteDao.save(quote);
  }

  /**
   * Find all quotes from the quote table
   * @return
   */
  public List<Quote> findAllQuotes() {
    return qouteDao.findAll();
  }












}
