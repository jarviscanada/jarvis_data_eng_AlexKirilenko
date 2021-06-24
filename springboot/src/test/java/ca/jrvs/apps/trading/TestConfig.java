package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class TestConfig {


  private Logger logger = LoggerFactory.getLogger(TestConfig.class);

  @Value("${app.IEX_HOST}")
  private String IEX_HOST;

  @Value("${app.IEX_PUB_TOKEN}")
  private String IEX_PUB_TOKEN;

  @Bean
  public MarketDataConfig marketDataConfig() {
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost(IEX_HOST);
    marketDataConfig.setToken(IEX_PUB_TOKEN);
    return marketDataConfig;
  }



  @Bean
  public HttpClientConnectionManager httpClientConnectionManager() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    return cm;
  }

}
