package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.LinkedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void postTweet() {
    // testing that appropriate methods of service are called
    when(service.postTweet(any())).thenReturn(new Tweet());
    controller.postTweet(new String[] {"post", "tweeeeet 123", "33:54"});
    verify(service, times(1)).postTweet(any());

    // test illegal arguments
    try {
      controller.postTweet(new String[] {"tweeeeet 123", "33:54"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      controller.postTweet(new String[] {"post", "tweeeeet 123", "33l:54"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      controller.postTweet(new String[] {"post", "tweeeeet 123", "33"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      controller.postTweet(new String[] {"post", "", "33:54"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }

  }

  @Test
  public void showTweet() {
    // testing that appropriate methods of service are called
    when(service.showTweet(any(), any())).thenReturn(new Tweet());
    controller.showTweet(new String[] {"show", "123"});
    controller.showTweet(new String[] {"show", "123", "id_str,created_at,text"});
    verify(service, times(2)).showTweet(any(), any());

    try {
      controller.showTweet(new String[] {"show"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }

    try {
      controller.showTweet(new String[] {"show", ""});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }

  }

  @Test
  public void deleteTweet() {
    // testing that appropriate methods of service are called
    when(service.deleteTweets(any())).thenReturn(new LinkedList<>());
    controller.deleteTweet(new String[] {"delete", "123"});
    controller.deleteTweet(new String[] {"delete", "123,5435345,23424,3333"});
    verify(service, times(2)).deleteTweets(any());

    try {
      controller.deleteTweet(new String[] {"delete"});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }

    try {
      controller.deleteTweet(new String[] {"delete", ""});
      fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
  }
}