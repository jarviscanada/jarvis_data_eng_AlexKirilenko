package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import oauth.signpost.OAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.mockito.Mock;

public class TwitterHttpHelperTest {


  @Test
  public void httpPost() throws URISyntaxException, IOException {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

    long testNumber = new Random().nextLong();
    HttpResponse response = helper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status="+testNumber));

    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(EntityUtils.toString(response.getEntity()));
    assertEquals(testNumber, node.get("text").asLong());
  }

  @Test
  public void httpGet() throws URISyntaxException, IOException {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

    long testNumber = new Random().nextLong();
    HttpResponse response = helper.httpGet(new URI("https://api.twitter.com/1.1/users/lookup.json?screen_name=elonmusk"));

    ObjectMapper mapper = new ObjectMapper();
    JsonNode[] tweets = mapper.readValue(EntityUtils.toString(response.getEntity()), JsonNode[].class);

    JsonNode node = tweets[0];
    assertEquals("Elon Musk", node.get("name").asText());
  }
}