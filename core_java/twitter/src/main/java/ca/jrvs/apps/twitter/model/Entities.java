package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {
  private List<Hashtag> hashtags;
  private List<UserMention> user_mentions;

  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  public void setHashtags(List<Hashtag> hashtags) {
    this.hashtags = hashtags;
  }

  public List<UserMention> getUser_mentions() {
    return user_mentions;
  }

  public void setUser_mentions(List<UserMention> user_mentions) {
    this.user_mentions = user_mentions;
  }

  @Override
  public String toString() {
    return "Entities{" +
        "hashtags=" + hashtags +
        ", user_mentions=" + user_mentions +
        '}';
  }
}
