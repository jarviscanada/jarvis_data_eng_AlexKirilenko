package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.util.Arrays;
import java.util.List;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

public class MarketDataDaoTest {

  private MarketDataDao dao;

  @Before
  public void init() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/v1");
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

    dao = new MarketDataDao(cm, marketDataConfig);
  }

  @Test
  public void findById() {
    String ticker = "AAPL";
    IexQuote iexQuote = dao.findById(ticker).get();
    System.out.println(iexQuote);
    assertEquals(ticker, iexQuote.getTicker());
  }

  @Test
  public void findAllById() {
    List<IexQuote> quoteList = dao.findAllById(Arrays.asList("aapl", "fb"));
    assertEquals(2, quoteList.size());
    assertEquals("AAPL", quoteList.get(0).getTicker());

    try {
      dao.findAllById(Arrays.asList("aapl", "fb2"));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    } catch (Exception e) {
      fail();
    }
  }
}