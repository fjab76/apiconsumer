package fjab.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@Service
public class MovieService {

  private RestTemplate restTemplate;

  public MovieResponse retrieveMovieInfo(String title) {
    MovieResponse movieResponse = restTemplate.getForObject("http://www.omdbapi.com/?s={title}", MovieResponse.class,title);
    return movieResponse;
  }

  @Autowired
  public void setRestTemplate(RestTemplate restTemplate){
    this.restTemplate = restTemplate;
  }
}
