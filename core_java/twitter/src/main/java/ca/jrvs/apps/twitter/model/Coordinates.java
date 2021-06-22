package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
  private double[] coordinates;
  private String type;

  public double[] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(double[] coordinates) {
    this.coordinates = coordinates;
  }
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Coordinates{" +
        "coordinates=" + Arrays.toString(coordinates) +
        ", type='" + type + '\'' +
        '}';
  }
}
