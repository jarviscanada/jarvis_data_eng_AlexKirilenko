package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpMethod;

public class TwitterHttpHelper implements HttpHelper {

  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Constructor Sets up HttpClient and OAuth header
   *
   * @param cosumerKey
   * @param cosumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String cosumerKey, String cosumerSecret, String accessToken,
      String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(cosumerKey, cosumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = new DefaultHttpClient();
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    return executeRequest(uri, "POST");
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    return executeRequest(uri, "GET");
  }

  /**
   * Helper method to execute http requests
   * converts all checked exceptions to runtime exceptions
   * @param uri request URI
   * @param requestType one of the supported request types
   * @return HttpResponse
   */
  private HttpResponse executeRequest(URI uri, String requestType) {

    HttpRequestBase requestBase = null;
    if (requestType.equals("GET")) {
      requestBase = new HttpGet(uri);
    } else if (requestType.equals("POST")) {
      requestBase = new HttpPost(uri);
    } else {
      throw new RuntimeException("Wrong or unsupported HTTP request type.");
    }

    try {
      consumer.sign(requestBase);
    } catch (OAuthException e) {
      throw new RuntimeException("Couldn't sign the HTTP request.", e);
    }

    try {
      return httpClient.execute(requestBase);
    } catch (IOException e) {
      throw new RuntimeException("Couldn't execute given HTTP request.", e);
    }
  }
}
