package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH =
      "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    this.IEX_BATCH_URL =
        marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }

  /**
   *
   * @param ticker
   * @return quote corresponding to the ticker
   * @throws IllegalArgumentException if ticker is invalid
   * @throws DataRetrievalFailureException if http request failed
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));
    if (quotes.size() == 0) {
      iexQuote = Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  /**
   * Get a quotes from Iex
   * @param tickers list of tickers
   * @return list of quotes
   * @throws IllegalArgumentException if any ticker is invalid or tickers are empty
   * @throws DataRetrievalFailureException if http request failed
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {

    List<String> tickersInputList = new LinkedList<>();
    tickers.iterator().forEachRemaining(tickersInputList::add);

    if (tickersInputList.size() == 0) {
      throw new IllegalArgumentException("No tickers provided.");
    }

    // build url string from tickers
    String tickersString = tickersInputList.stream().collect(Collectors.joining(","));
    String url = String.format(IEX_BATCH_URL, tickersString.toString());

    // perform request
    String responseString = executeHttpGet(url)
        .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

    // parsing requests
    List<IexQuote> quotesOutput = new LinkedList<>();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode IexQuotesJson = objectMapper.readTree(responseString);
      for (String tickerName : tickersInputList) {
        JsonNode tickerNode = IexQuotesJson.path(tickerName.toUpperCase());
        if (tickerNode.isMissingNode()) {
          throw new IllegalArgumentException("No ticket " + tickerName + " found in response");
        } else {
          quotesOutput.add(objectMapper.treeToValue(tickerNode.path("quote"), IexQuote.class));
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Failure parsing response", e);
    }
    return quotesOutput;
  }

  /**
   * Execute a get and return http entity/body as a string
   * @param url resource for get
   * @return http response body or Optional.empty for 404 status
   * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String url) {
    Optional<String> responseBodyString;
    try (CloseableHttpClient httpClient = getHttpClient()) {

      HttpGet httpGet = new HttpGet(url);
      try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
        StatusLine statusLine = response.getStatusLine();
        switch (statusLine.getStatusCode()) {
          case 200:
            HttpEntity entity = response.getEntity();
            responseBodyString = Optional.of(EntityUtils.toString(entity));
            break;
          case 404:
            responseBodyString = Optional.empty();
            break;
          default:
            throw new DataRetrievalFailureException("Unexpected status code");
        }
      }

    } catch (IOException e) {
      throw new DataRetrievalFailureException("Http client failure", e);
    }
    return responseBodyString;
  }

  /**
   *
   * @return httpClient from connection manager
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        .setConnectionManagerShared(true)
        .build();
  }



  @Override
  public <S extends IexQuote> S save(S entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }



  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented");
  }



  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(IexQuote entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
