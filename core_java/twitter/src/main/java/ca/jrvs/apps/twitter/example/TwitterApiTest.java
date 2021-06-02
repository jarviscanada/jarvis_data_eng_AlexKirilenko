package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TwitterApiTest {

//  private static String CONSUMER_KEY = System.getenv("cosumerKey");
//  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
//  private static String ACCESS_TOKEN = System.getenv("accessToken");
//  private static String TOKEN_SECRET = System.getenv("cosumerKey");
  private static String CONSUMER_KEY = "69poB0utiGYgY7XwYlAfhMZ71";
  private static String CONSUMER_SECRET = "QjrijragoMvJS9HVEj4cTVEdo3RCYxSrqfr2MEXOGOzXmqy17q";
  private static String ACCESS_TOKEN = "1394855368379965443-rJMg114OFyuCdXiLLDpzgnx20KLT6n";
  private static String TOKEN_SECRET = "3IPO5ntPhd3VwgzYG1XSz9ZH2NQBEFXUiyhlwhF0vpWl0";

  public static void main(String[] args)
      throws Exception {
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    String status = "today is a good day";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpPost request = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status));

    consumer.sign(request);

    System.out.println("Request Headers");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response = httpClient.execute(request);
    System.out.println(EntityUtils.toString(response.getEntity()));

  }
}
