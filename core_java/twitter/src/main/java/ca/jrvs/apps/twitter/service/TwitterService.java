package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) { this.dao = dao; }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateShowTweet(id, fields);
    Tweet tweetDao = (Tweet) dao.findById(id);
    return buildNewTweetWithFields(tweetDao, fields);
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    validateDeleteTweets(ids);
    return deleteTweetsAtomically(ids);
  }

  private void validatePostTweet(Tweet tweet) {
    if (tweet == null) {
      throw new IllegalArgumentException("Input tweet object is null.");
    }
    if (tweet.getText() == null || tweet.getText().length() > 140) {
      throw new IllegalArgumentException("Tweet length must be between 1 and 140 characters.");
    }
    Coordinates coordinates = tweet.getCoordinates();
    if (coordinates != null) {
      double[] coordinatesArray = coordinates.getCoordinates();
      if (coordinatesArray != null && coordinatesArray.length == 2) {
        double longitude = coordinatesArray[0];
        double latitude = coordinatesArray[1];
        if (Math.abs(longitude) > 180 || Math.abs(latitude) > 90) {
          throw new IllegalArgumentException("Provided coordinates are outside of the possible range.");
        }
      }
    }
  }

  private void validateShowTweet(String id, String[] fields) {
    try {
      long id_long = Long.parseUnsignedLong(id);
    } catch (NumberFormatException e) {
      throw  new IllegalArgumentException("ID must be an unsigned 64-bit number. Was provided: " + id);
    }
    if (fields == null) return;
    for (String f : fields) {
      try {
        Field field = Tweet.class.getDeclaredField(f);
//        boolean accessible = field.isAccessible();
      } catch (NoSuchFieldException e) {
        throw  new IllegalArgumentException(f + " field doesn't exist.");
      }
    }
  }

  private void validateDeleteTweets(String[] ids) {
    if (ids == null || ids.length == 0) {
      throw  new IllegalArgumentException("Must provide Tweet IDs for deletion.");
    }
    for (String id : ids) {
      try {
        long id_long = Long.parseUnsignedLong(id);
      } catch (NumberFormatException e) {
        throw  new IllegalArgumentException("ID must be an unsigned 64-bit number. Was provided: " + id);
      }
    }
  }

  private Tweet buildNewTweetWithFields(Tweet oldTweet, String[] fields) {
    // return the tweet from DAO with all fields
    if (fields == null || fields.length == 0) {
      return oldTweet;
    }
    // build new tweet with only selected fields using reflection
    Tweet tweetService = new Tweet();
    for (Field field : Tweet.class.getDeclaredFields()) {
      boolean accessible = field.isAccessible();
      if (Arrays.asList(fields).contains(field.getName())) {
        try {
          field.setAccessible(true);
          field.set(tweetService, field.get(oldTweet));
          field.setAccessible(accessible);
        } catch (Exception e) {
          throw  new IllegalArgumentException("Couldn't process field: " + field.getName());
        }
      }
    }
    return  tweetService;
  }

  private List<Tweet> deleteTweetsAtomically(String[] ids) {
    List<Tweet> tweets = new LinkedList<>();
    // find tweets first before deleting
    // Ensures that half-way exceptions dont result in inconsistent state
    for (String id : ids) {
      tweets.add((Tweet) dao.findById(id));
    }
    for (String id : ids) {
      dao.deleteById(id);
    }
    return tweets;
  }


}
