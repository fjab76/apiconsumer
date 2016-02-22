package fjab.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

  @JsonProperty("Title")
  private String title;
  @JsonProperty("Year")
  private String year;

  public String getTitle() {
    return title;
  }

  public String getYear() {
    return year;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setYear(String year) {
    this.year = year;
  }


  @Override
  public String toString() {
    return "Movie{" +
            "title='" + title + '\'' +
            ", year='" + year + '\'' +
            '}';
  }
}
