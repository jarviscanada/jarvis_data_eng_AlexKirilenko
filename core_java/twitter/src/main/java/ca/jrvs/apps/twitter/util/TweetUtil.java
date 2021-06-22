package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TweetUtil {

  public static Tweet buildTweet(String text) {
    Tweet tweet = new Tweet();
    tweet.setText(text);
    return tweet;
  }

  public static Tweet buildTweet(String text, double latitude, double longitude) {
    Tweet tweet = buildTweet(text);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {longitude, latitude});
    tweet.setCoordinates(coordinates);
    return tweet;
  }

  public static void displayTweet(Tweet tweet) {
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    try {
      System.out.println(mapper.writeValueAsString(tweet));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Couldn't print tweet \n " + tweet.toString());
    }
  }

}
