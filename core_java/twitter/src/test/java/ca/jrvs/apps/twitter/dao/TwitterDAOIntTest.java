package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.net.URISyntaxException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDAOIntTest {

  TwitterDAO dao;

  @Before
  public void setup() {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

    this.dao = new TwitterDAO(helper);
  }

  @Test
  public void create() throws URISyntaxException {
    Tweet newTweet = new Tweet();
    long currentTime = System.currentTimeMillis();
    newTweet.setText("new tweet test at " + currentTime);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {-55.5, 40.1});
    coordinates.setType("Point");
    newTweet.setCoordinates(coordinates);

    Tweet returnedTweet = dao.create(newTweet);

    assertEquals(newTweet.getText(), returnedTweet.getText());
    assertArrayEquals(newTweet.getCoordinates().getCoordinates(),
        returnedTweet.getCoordinates().getCoordinates(), 0.001);

    assertTrue(returnedTweet.getText().contains(String.valueOf(currentTime)));
  }

  @Test
  public void findById() {

    long id = 1400850084481441799L;

    Tweet returnedTweet = dao.findById(String.valueOf(id));

    assertEquals("1400850084481441799", returnedTweet.getId_str());

    double[] coordinatesArray = new double[] {-55.5, 40.1};
    assertArrayEquals(coordinatesArray,
        returnedTweet.getCoordinates().getCoordinates(), 0.001);

    assertTrue(returnedTweet.getText().contains("tweet test"));
  }

  @Test
  public void deleteById() {
    // creating new tweet that is to be deleted
    Tweet newTweet = new Tweet();
    long currentTime = System.currentTimeMillis();
    newTweet.setText("new tweet test at " + currentTime);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {-55.5, 40.1});
    coordinates.setType("Point");
    newTweet.setCoordinates(coordinates);
    Tweet returnedTweet = dao.create(newTweet);

    long id = returnedTweet.getId();
    Tweet deletedTweet = dao.deleteById(String.valueOf(id));

    assertEquals(id, deletedTweet.getId());
    assertTrue(deletedTweet.getText().contains(String.valueOf(currentTime)));

  }


}