package fjab.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {

  @JsonProperty("artist_name")
  private String artistName;
  private String title;

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtistName() {
    return artistName;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "Song{" +
            "artistName='" + artistName + '\'' +
            ", title='" + title + '\'' +
            '}';
  }
}
