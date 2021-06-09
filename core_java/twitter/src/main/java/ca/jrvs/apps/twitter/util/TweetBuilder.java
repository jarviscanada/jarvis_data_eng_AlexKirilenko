package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

public class TweetBuilder {

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

}
