package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper httpHelper;

  @InjectMocks
  TwitterDAO dao;

  @Test
  public void create() throws IOException {
    Tweet newTweet = new Tweet();
    newTweet.setText("new tweet test");
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {-55.5, 40.1});
    coordinates.setType("Point");
    newTweet.setCoordinates(coordinates);

    String tweetString = "{\n"
        + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "   \"id\":1097607853932564480,\n"
        + "   \"id_str\":\"1097607853932564480\",\n"
        + "   \"text\":\"test with loc223\",\n"
        + "   \"entities\":{\n"
        + "      \"hashtags\":[],\n"
        + "      \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinates\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorite_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    Tweet expectedTweet = new ObjectMapper().readValue(tweetString, Tweet.class);

    when(httpHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDAO spyDao = spy(dao);
    try {
      Tweet tweetNull = spyDao.create(newTweet);
      fail();
    } catch (NullPointerException e) {
      assertTrue(true);
    }
    // method had to be made package-default for testing
    doReturn(expectedTweet).when(spyDao).createTweetFromHttp(any());
    Tweet tweetResult = spyDao.create(newTweet);
    assertNotNull(tweetResult);
    assertNotNull(tweetResult.getText());
  }

  @Test
  public void find() throws IOException {
    String id_str = "1";

    String tweetString = "{\n"
        + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "   \"id\":1097607853932564480,\n"
        + "   \"id_str\":\"1097607853932564480\",\n"
        + "   \"text\":\"test with loc223\",\n"
        + "   \"entities\":{\n"
        + "      \"hashtags\":[],\n"
        + "      \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinates\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorite_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    Tweet expectedTweet = new ObjectMapper().readValue(tweetString, Tweet.class);

    when(httpHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDAO spyDao = spy(dao);
    try {
      Tweet tweetNull = spyDao.findById(String.valueOf(id_str));
      fail();
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }

  @Test
  public void delete() throws IOException {
    String id_str = "1";

    String tweetString = "{\n"
        + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "   \"id\":1097607853932564480,\n"
        + "   \"id_str\":\"1097607853932564480\",\n"
        + "   \"text\":\"test with loc223\",\n"
        + "   \"entities\":{\n"
        + "      \"hashtags\":[],\n"
        + "      \"user_mentions\":[]\n"
        + "   },\n"
        + "   \"coordinates\":null,\n"
        + "   \"retweet_count\":0,\n"
        + "   \"favorite_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    Tweet expectedTweet = new ObjectMapper().readValue(tweetString, Tweet.class);

    when(httpHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDAO spyDao = spy(dao);
    try {
      Tweet tweetNull = spyDao.deleteById(String.valueOf(id_str));
      fail();
    } catch (NullPointerException e) {
      assertTrue(true);
    }
  }


}