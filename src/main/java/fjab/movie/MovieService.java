package fjab.movie;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@Service
public class MovieService {

  public MovieResponse retrieveMovieInfo(String title) {
    RestTemplate restTemplate = new RestTemplate();
    MovieResponse movieResponse = restTemplate.getForObject("http://www.omdbapi.com/?s={title}", MovieResponse.class,title);
    return movieResponse;
  }
}
