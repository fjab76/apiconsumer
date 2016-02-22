package fjab.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {

  @JsonProperty("Search")
  private Movie[] movies;
  @JsonProperty("Error")
  private String error;

  public MovieResponse(){}

  public MovieResponse(Movie[] movies) {
    this.movies = movies;
  }

  public void setMovies(Movie[] movies) {
    this.movies = movies;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Movie[] getMovies() {
    return movies;
  }

  public String getError() {
    return error;
  }
}
