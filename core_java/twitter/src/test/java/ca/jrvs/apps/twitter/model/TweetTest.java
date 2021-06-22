package ca.jrvs.apps.twitter.model;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;


public class TweetTest {

  @Test
  public void testTweet() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Tweet tweet = mapper.readValue(tweetString, Tweet.class);

    System.out.println(tweet);

    assertEquals("Mon Feb 18 21:24:39 +0000 2019", tweet.getCreated_at());
    assertEquals(1097607853932564480L, tweet.getId());
    assertEquals("1097607853932564480", tweet.getId_str());
    assertEquals("test with loc223", tweet.getText());
    assertEquals(new Integer(0), tweet.getRetweet_count());
    assertEquals(new Integer(0), tweet.getFavorite_count());
    assertEquals(new Boolean(false), tweet.isFavorited());
    assertEquals(new Boolean(false), tweet.isRetweeted());
    assertNotNull(tweet.getEntities());
    assertNotNull(tweet.getCoordinates());
  }
  @Test
  public void testCoordinates() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    Coordinates coordinatesEmpty = mapper.readValue("{}", Coordinates.class);
    assertNull(coordinatesEmpty.getCoordinates());
    assertNull(coordinatesEmpty.getType());

    Coordinates coordinates = mapper.readValue("{\n"
      + "      \"coordinates\":[\n"
      + "         -75.14310264,\n"
      + "         40.05701649\n"
      + "      ],\n"
      + "      \"type\":\"Point\"\n"
      + "   }", Coordinates.class);
    assertArrayEquals(new double[] {-75.14310264, 40.05701649}, coordinates.getCoordinates(), 0.001);
    assertEquals("Point", coordinates.getType());
  }

  @Test
  public void testEntities() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    Entities entitiesEmpty = mapper.readValue("{}", Entities.class);
    assertNull(entitiesEmpty.getHashtags());
    assertNull(entitiesEmpty.getUser_mentions());

    Entities entitiesEmpty2 = mapper.readValue("{\"hashtags\":[], \"user_mentions\":[]}", Entities.class);
    assertEquals(0, entitiesEmpty2.getHashtags().size());
    assertEquals(0, entitiesEmpty2.getUser_mentions().size());

    Entities entities = mapper.readValue("{\n"
      + "      \"hashtags\":[\n"
      + "         {\n"
      + "            \"text\":\"documentation\",\n"
      + "            \"indices\":[\n"
      + "               211,\n"
      + "               225\n"
      + "            ]\n"
      + "         },\n"
      + "         {\n"
      + "            \"text\":\"parsingJSON\",\n"
      + "            \"indices\":[\n"
      + "               226,\n"
      + "               238\n"
      + "            ]\n"
      + "         },\n"
      + "         {\n"
      + "            \"text\":\"GeoTagged\",\n"
      + "            \"indices\":[\n"
      + "               239,\n"
      + "               249\n"
      + "            ]\n"
      + "         }\n"
      + "      ],\n"
      + "      \"user_mentions\":[\n"
      + "         {\n"
      + "            \"name\":\"Twitter API\",\n"
      + "            \"indices\":[\n"
      + "               4,\n"
      + "               15\n"
      + "            ],\n"
      + "            \"screen_name\":\"twitterapi\",\n"
      + "            \"id\":6253282,\n"
      + "            \"id_str\":\"6253282\"\n"
      + "         }\n"
      + "      ]\n"
      + "   }", Entities.class);

    assertEquals(3, entities.getHashtags().size());
    assertEquals("GeoTagged", entities.getHashtags().get(2).getText());
    assertArrayEquals(new int[] {239, 249}, entities.getHashtags().get(2).getIndices());

    assertEquals(1, entities.getUser_mentions().size());
    assertEquals("Twitter API", entities.getUser_mentions().get(0).getName());
    assertArrayEquals(new int[] {4, 15}, entities.getUser_mentions().get(0).getIndices());
    assertEquals("twitterapi", entities.getUser_mentions().get(0).getScreen_name());
    assertEquals(6253282, entities.getUser_mentions().get(0).getId());
    assertEquals("6253282", entities.getUser_mentions().get(0).getId_str());
  }




  static final String tweetString = "{\n"
      + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
      + "   \"id\":1097607853932564480,\n"
      + "   \"id_str\":\"1097607853932564480\",\n"
      + "   \"text\":\"test with loc223\",\n"
      + "   \"entities\":{\n"
      + "      \"hashtags\":[\n"
      + "         {\n"
      + "            \"text\":\"documentation\",\n"
      + "            \"indices\":[\n"
      + "               211,\n"
      + "               225\n"
      + "            ]\n"
      + "         },\n"
      + "         {\n"
      + "            \"text\":\"parsingJSON\",\n"
      + "            \"indices\":[\n"
      + "               226,\n"
      + "               238\n"
      + "            ]\n"
      + "         },\n"
      + "         {\n"
      + "            \"text\":\"GeoTagged\",\n"
      + "            \"indices\":[\n"
      + "               239,\n"
      + "               249\n"
      + "            ]\n"
      + "         }\n"
      + "      ],\n"
      + "      \"user_mentions\":[\n"
      + "         {\n"
      + "            \"name\":\"Twitter API\",\n"
      + "            \"indices\":[\n"
      + "               4,\n"
      + "               15\n"
      + "            ],\n"
      + "            \"screen_name\":\"twitterapi\",\n"
      + "            \"id\":6253282,\n"
      + "            \"id_str\":\"6253282\"\n"
      + "         }\n"
      + "      ]\n"
      + "   },\n"
      + "   \"coordinates\":{\n"
      + "      \"coordinates\":[\n"
      + "         -75.14310264,\n"
      + "         40.05701649\n"
      + "      ],\n"
      + "      \"type\":\"Point\"\n"
      + "   },\n"
      + "   \"retweet_count\":0,\n"
      + "   \"favorite_count\":0,\n"
      + "   \"favorited\":false,\n"
      + "   \"retweeted\":false\n"
      + "}";
}