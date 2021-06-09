package ca.jrvs.apps.twitter.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
  private String created_at;
  private long id;
  private String id_str;
  private String text;
  private Entities entities;
  private Coordinates coordinates;
  private Integer retweet_count;
  private Integer favorite_count;
  private Boolean favorited;
  private Boolean retweeted;

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getId_str() {
    return id_str;
  }

  public void setId_str(String id_str) {
    this.id_str = id_str;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Entities getEntities() {
    return entities;
  }

  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Integer getRetweet_count() {
    return retweet_count;
  }

  public void setRetweet_count(Integer retweet_count) {
    this.retweet_count = retweet_count;
  }

  public Integer getFavorite_count() {
    return favorite_count;
  }

  public void setFavorite_count(Integer favorite_count) {
    this.favorite_count = favorite_count;
  }

  public Boolean isFavorited() {
    return favorited;
  }

  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  public Boolean isRetweeted() {
    return retweeted;
  }

  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }

  @Override
  public String toString() {
    return "Tweet{" +
        "created_at='" + created_at + '\'' +
        ", id=" + id +
        ", id_str='" + id_str + '\'' +
        ", text='" + text + '\'' +
        ", entities=" + entities +
        ", coordinates=" + coordinates +
        ", retweet_count=" + retweet_count +
        ", favorite_count=" + favorite_count +
        ", favorited=" + favorited +
        ", retweeted=" + retweeted +
        '}';
  }
}
