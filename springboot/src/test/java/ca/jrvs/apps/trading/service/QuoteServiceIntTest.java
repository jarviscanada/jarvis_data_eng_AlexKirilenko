package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
//@ComponentScan(basePackages = "ca.jrvs.apps.trading")
@SpringBootTest
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
  }

  @After
  public void cleanup() {
    quoteDao.deleteAll();
  }


  @Test
  public void updateMarketData() {

    quoteService.saveQuotes(Arrays.asList("AAPL","TSLA"));
    quoteService.updateMarketData();
    List<Quote> quotes = quoteDao.findAllById(Arrays.asList("AAPL","TSLA"));

    assertEquals(2, quotes.size());
    assertTrue(quotes.get(0).getTicker().contains("AAPL"));
    assertTrue(quotes.get(1).getTicker().contains("TSLA"));

    // trying to update wrong quote saved in database
    Quote wrongQuote = new Quote();
    wrongQuote.setTicker("AAA1");
    wrongQuote.setBidPrice(1.1);
    wrongQuote.setBidSize(23);
    wrongQuote.setAskPrice(1.3);
    wrongQuote.setAskSize(44);
    wrongQuote.setLastPrice(1.3);
    quoteDao.save(wrongQuote);
    try {
      quoteService.updateMarketData();
      fail();
    } catch (Exception e){
      // spring wraps IllegalArgumentException into InvalidDataAccessApiUsageException
      assertTrue(true);
    }

  }

  @Test
  public void saveQuotes() {
    List<Quote> quotes = quoteService.saveQuotes(Arrays.asList("AAPL","TSLA", "GOOG"));

    assertEquals(3, quotes.size());
    assertTrue(quotes.get(0).getTicker().contains("AAPL"));
    assertTrue(quotes.get(1).getTicker().contains("TSLA"));
    assertTrue(quotes.get(2).getTicker().contains("GOOG"));
  }

  @Test
  public void findIexQuoteByTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("GOOG");
    assertTrue(iexQuote.getTicker().contains("GOOG"));
    assertNotNull(iexQuote.getLastPrice());

    try {
      IexQuote iexQuoteWrong = quoteService.findIexQuoteByTicker("GOOOOOG");
      fail();
    } catch (Exception e) {
      assertTrue(true);
    }
  }

  @Test
  public void saveQuote() {
    Quote quote = quoteService.saveQuote("AAPL");
    assertTrue(quote.getTicker().contains("AAPL"));
    assertNotNull(quote.getLastPrice());
  }

  @Test
  public void findAllQuotes() {

    quoteService.saveQuotes(Arrays.asList("AAPL","TSLA", "GOOG"));
    assertEquals(3, quoteService.findAllQuotes().size());
  }
}