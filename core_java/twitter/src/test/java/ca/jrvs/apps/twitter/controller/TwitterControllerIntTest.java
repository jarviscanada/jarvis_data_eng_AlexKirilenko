package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  TwitterDAO dao;
  TwitterService service;
  TwitterController controller;

  @Before
  public void setup() {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");


    HttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

    this.dao = new TwitterDAO(helper);
    this.service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    long currentTime = System.currentTimeMillis();
    String tweetText = "new tweet test at " + currentTime;
    double latitude = 40.1;
    double longitude = -55.5;
    String[] arguments = new String[] {"post", tweetText,
        String.valueOf(latitude)+":"+String.valueOf(longitude)};
    Tweet returnedTweet = controller.postTweet(arguments);
    assertEquals(tweetText, returnedTweet.getText());
    assertEquals(longitude, returnedTweet.getCoordinates().getCoordinates()[0],0.001);
    assertEquals(latitude, returnedTweet.getCoordinates().getCoordinates()[1],0.001);
    assertTrue(returnedTweet.getText().contains(String.valueOf(currentTime)));
  }

  @Test
  public void showTweet() {
    String str_id = "1400850084481441799";
    String[] argumentsFull = new String[] {"show", str_id};
    Tweet returnedTweetFull = controller.showTweet(argumentsFull);
    assertEquals(str_id, returnedTweetFull.getId_str());
    double[] coordinatesArray = new double[] {-55.5, 40.1};
    assertArrayEquals(coordinatesArray,
        returnedTweetFull.getCoordinates().getCoordinates(), 0.001);
    assertTrue(returnedTweetFull.getText().contains("tweet test"));
    assertNotNull(returnedTweetFull.getCreated_at());


    String[] argumentsLimited = new String[] {"show", str_id, "id_str,text,id,created_at"};
    Tweet returnedTweetLimited = controller.showTweet(argumentsLimited);
    assertEquals(str_id, returnedTweetLimited.getId_str());
    assertEquals(Long.parseLong(str_id), returnedTweetLimited.getId());
    assertTrue(returnedTweetLimited.getText().contains("tweet test"));
    assertNotNull(returnedTweetLimited.getCreated_at());
    assertNull(returnedTweetLimited.getCoordinates());
    assertNull(returnedTweetLimited.getEntities());
  }

  @Test
  public void deleteTweet() {
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

    String[] arguments = new String[] {"delete", id1 + "," + id2};


    List<Tweet> deletedTweets = controller.deleteTweets(arguments);
    assertEquals(id1, deletedTweets.get(0).getId());
    assertEquals(id2, deletedTweets.get(1).getId());
    assertTrue(deletedTweets.get(0).getText().contains(String.valueOf(currentTime1)));
    assertTrue(deletedTweets.get(1).getText().contains(String.valueOf(currentTime2)));
  }
}