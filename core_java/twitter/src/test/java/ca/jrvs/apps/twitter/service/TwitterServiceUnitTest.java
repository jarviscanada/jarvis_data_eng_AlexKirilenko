package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() {
    // test that appropriate methods of dao are called
    when(dao.create(any())).thenReturn(new Tweet());
    Tweet newTweet = new Tweet();
    long currentTime = System.currentTimeMillis();
    newTweet.setText("new tweet test at " + currentTime);
    service.postTweet(newTweet);
    verify(dao, times(1)).create(any());

    // test for illegal arguments
    try {
      service.postTweet(null);
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      Tweet emptyTweet = new Tweet();
      service.postTweet(emptyTweet);
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      Tweet longTweet = new Tweet();
      longTweet.setText("Updates the authenticating user's current status, also known as Tweeting.\n"
          + "\n"
          + "For each update attempt, the update text is compared with the authenticating user's recent Tweets. Any attempt that would result in duplication will be blocked, resulting in a 403 error. A user cannot submit the same status twice in a row.\n"
          + "\n"
          + "While not rate limited by the API, a user is limited in the number of Tweets they can create at a time. If the number of updates posted by the user reaches the current allowed limit this method will return an HTTP 403 error.");
      service.postTweet(longTweet);
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      Tweet wrongCoordinatesTweet = new Tweet();
      wrongCoordinatesTweet.setText("wrong coordinates");
      wrongCoordinatesTweet.setCoordinates(new Coordinates());
      wrongCoordinatesTweet.getCoordinates().setCoordinates(new double[] {-255.5, 140.1});
      wrongCoordinatesTweet.getCoordinates().setType("Point");
      service.postTweet(wrongCoordinatesTweet);
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
  }

  @Test
  public void showTweet() {
    // test that appropriate methods of dao are called
    when(dao.findById(any())).thenReturn(new Tweet());
    String id_str = "131331312";
    service.showTweet(id_str, new String[]{});
    verify(dao, times(1)).findById(any());


    // test illegal arguments
    try {
      Tweet tweetWrongArgument = new Tweet();
      service.showTweet(tweetWrongArgument.getId_str(), new String[] {"id_STRING", "text", "id", "created_at"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    // test wrong ids
    try {
      Tweet tweetWrongId = new Tweet();
      tweetWrongId.setId_str("13131a");
      service.showTweet(tweetWrongId.getId_str(), new String[] {});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      Tweet tweetWrongId = new Tweet();
      tweetWrongId.setId_str("-1313");
      service.showTweet(tweetWrongId.getId_str(), new String[] {});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      Tweet tweetWrongId = new Tweet();
      tweetWrongId.setId_str("4444444444444444444444");
      service.showTweet(tweetWrongId.getId_str(), new String[] {});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      Tweet tweetWrongId = new Tweet();
      tweetWrongId.setId_str("");
      service.showTweet(tweetWrongId.getId_str(), new String[] {});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
  }

  @Test
  public void deleteTweets() {
    // test that appropriate methods of dao are called
    when(dao.deleteById(any())).thenReturn(new Tweet());
    String[] idsToDelete = new String[]{"23132132", "1111111", "12324234234324"};
    service.deleteTweets(idsToDelete);
    verify(dao, times(idsToDelete.length)).deleteById(any());

    // test that arguments for deletion are provided and valid
    try {
      service.deleteTweets(new String[]{});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      service.deleteTweets(null);
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      service.deleteTweets(new String[]{"23132132", "-1111111", "12324234234324"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
  }
}