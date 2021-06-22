package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {


  TwitterDAO dao;
  TwitterService service;

  @Before
  public void setup() {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

    this.dao = new TwitterDAO(helper);
    this.service = new TwitterService(dao);
  }

  /**
   * Test valid text length, valid coordinates
   */
  @Test
  public void postTweet() {
    Tweet newTweet = new Tweet();
    long currentTime = System.currentTimeMillis();
    newTweet.setText("new tweet test at " + currentTime);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {-55.5, 40.1});
    coordinates.setType("Point");
    newTweet.setCoordinates(coordinates);
    Tweet returnedTweet = service.postTweet(newTweet);
    assertEquals(newTweet.getText(), returnedTweet.getText());
    assertArrayEquals(newTweet.getCoordinates().getCoordinates(),
        returnedTweet.getCoordinates().getCoordinates(), 0.001);
    assertTrue(returnedTweet.getText().contains(String.valueOf(currentTime)));

  }

  /**
   * Test tweet with all fields and limited fields
   */
  @Test
  public void showTweet() {

    long id = 1400850084481441799L;
    Tweet returnedTweetFull = service.showTweet(String.valueOf(id), new String[] {});
    assertEquals("1400850084481441799", returnedTweetFull.getId_str());
    double[] coordinatesArray = new double[] {-55.5, 40.1};
    assertArrayEquals(coordinatesArray,
        returnedTweetFull.getCoordinates().getCoordinates(), 0.001);
    assertTrue(returnedTweetFull.getText().contains("tweet test"));

    Tweet returnedTweetLimited = service.showTweet(String.valueOf(id), new String[] {"id_str", "text", "id", "created_at"});
    assertEquals("1400850084481441799", returnedTweetLimited.getId_str());
    assertEquals(id, returnedTweetLimited.getId());
    assertTrue(returnedTweetLimited.getText().contains("tweet test"));
    assertNotNull(returnedTweetLimited.getCreated_at());
    assertNull(returnedTweetLimited.getCoordinates());
    assertNull(returnedTweetLimited.getEntities());
  }

  /**
   * checks if tweets can be deleted
   */
  @Test
  public void deleteTweets() {
    // creating new tweets that are to be deleted
    Tweet newTweet1 = new Tweet();
    long currentTime1 = System.currentTimeMillis();
    newTweet1.setText("new tweet test at " + currentTime1);
    Coordinates coordinates1 = new Coordinates();
    coordinates1.setCoordinates(new double[] {-55.5, 40.1});
    coordinates1.setType("Point");
    newTweet1.setCoordinates(coordinates1);
    Tweet returnedTweet1 = dao.create(newTweet1);
    long id1 = returnedTweet1.getId();
    Tweet newTweet2 = new Tweet();
    long currentTime2= System.currentTimeMillis();
    newTweet2.setText("new tweet test at " + currentTime2);
    Coordinates coordinates2 = new Coordinates();
    coordinates2.setCoordinates(new double[] {35.08, -15.9});
    coordinates2.setType("Point");
    newTweet2.setCoordinates(coordinates2);
    Tweet returnedTweet2 = dao.create(newTweet2);
    long id2 = returnedTweet2.getId();


    List<Tweet> deletedTweets = service.deleteTweets(new String[] {String.valueOf(id1), String.valueOf(id2)});
    assertEquals(id1, deletedTweets.get(0).getId());
    assertEquals(id2, deletedTweets.get(1).getId());
    assertTrue(deletedTweets.get(0).getText().contains(String.valueOf(currentTime1)));
    assertTrue(deletedTweets.get(1).getText().contains(String.valueOf(currentTime2)));

    // test exception when deleting not accessible/ non existent tweets
    try {
      service.deleteTweets(new String[] {"11123213"});
      fail();
    } catch (Exception e){
      assertTrue(true);
    }

  }
}