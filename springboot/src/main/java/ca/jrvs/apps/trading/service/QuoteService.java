package ca.jrvs.apps.trading.service;


import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.QuoteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }
}
