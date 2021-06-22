package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterDAO implements CrdDao<Tweet, String> {

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DElETE_PATH = "/1.1/statuses/destroy/";

  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterDAO(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  @Override
  public Tweet create(Tweet entity) {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    String postString = API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(entity.getText());
    if (entity.getCoordinates() != null) {
      double longitude =  entity.getCoordinates().getCoordinates()[0];
      double latitude =  entity.getCoordinates().getCoordinates()[1];
      postString = postString + AMPERSAND + "long" + EQUAL + longitude + AMPERSAND + "lat" + EQUAL + latitude;
    }
    HttpResponse response = httpHelper.httpPost(createUriFromString(postString));
    return createTweetFromHttp(response);
  }

  @Override
  public Tweet findById(String id_str) {
    String getString = API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id_str;
    HttpResponse response = httpHelper.httpGet(createUriFromString(getString));
    return createTweetFromHttp(response);
  }

  @Override
  public Tweet deleteById(String id_str) {
    String deleteString = API_BASE_URI + DElETE_PATH + id_str + ".json";
    HttpResponse response = httpHelper.httpPost(createUriFromString(deleteString));
    return createTweetFromHttp(response);
  }

  URI createUriFromString(String uri) {
    try {
      return new URI(uri);
    } catch (URISyntaxException e) {
      new RuntimeException("Wrong URI formatting." , e);
    }
    return null;
  }

  Tweet createTweetFromHttp(HttpResponse response) {

    if (response.getStatusLine().getStatusCode() != HTTP_OK) {
      throw new RuntimeException("Request status code failure.");
    }
    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body.");
    }

    Tweet responseTweet = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
      String responseBody = EntityUtils.toString(response.getEntity());
      responseTweet = mapper.readValue(responseBody, Tweet.class);
    } catch (IOException e) {
      new RuntimeException("Couldn't convert response JSON to Tweet object." , e);
    }
    return responseTweet;
  }
}
