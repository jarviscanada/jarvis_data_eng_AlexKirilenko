package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetBuilder;
import java.util.List;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";
  private static final String USAGE_POST = "USAGE: TwitterCLI post \"tweet_text\" \"latitude:longitude\"";
  private static final String USAGE_SHOW = "USAGE: TwitterCLI show tweet_id [\"field1,field2, ...\"]";
  private static final String USAGE_DELETE = "USAGE: TwitterCLI delete \"tweet_id1[,tweet_id2, ...]\"";


  private Service service;

  public TwitterController(Service service) {this.service = service;}

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Incorrect arguments, " + USAGE_POST);
    }
    String tweet_text = args[1];
    String coordinatesString = args[2];
    String[] coordinatesArray = coordinatesString.split(COORD_SEP);
    if (coordinatesArray.length != 2 || tweet_text.length() == 0) {
      throw new IllegalArgumentException("Incorrect arguments, " + USAGE_POST);
    }
    double latitude;
    double longitude;
    try {
      latitude = Double.parseDouble(coordinatesArray[0]);
      longitude = Double.parseDouble(coordinatesArray[1]);
    } catch (Exception e) {
      throw new IllegalArgumentException("Incorrect coordinates format, " + USAGE_POST);
    }
    Tweet tweet = TweetBuilder.buildTweet(tweet_text, latitude, longitude);
    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("Incorrect arguments, " + USAGE_SHOW);
    }
    if (args[1].length() == 0) {
      throw new IllegalArgumentException("Incorrect arguments, " + USAGE_SHOW);
    }
    String id_str = args[1];
    String[] fields;
    if (args.length == 3) {
      fields = args[2].split(COMMA);
    } else {
      fields = new String[0];
    }
    return service.showTweet(id_str, fields);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Incorrect arguments, " + USAGE_DELETE);
    }
    if (args[1].length() == 0) {
      throw new IllegalArgumentException("Incorrect arguments, " + USAGE_DELETE);
    }
    String[] ids;
    if (args[1].contains(COMMA)) {
      ids = args[1].split(COMMA);
    } else {
      ids = new String[] {args[1]};
    }
    return service.deleteTweets(ids);
  }
}
