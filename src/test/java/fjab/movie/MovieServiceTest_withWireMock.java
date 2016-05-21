package fjab.movie;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
public class MovieServiceTest_withWireMock {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8888);

  private MovieService movieService;

  @Before
  public void setUp() throws Exception {
    movieService = new MovieService();
    movieService.setRestTemplate(new RestTemplate());
  }


  @Test
  public void whenMovieTitleIs_batman_allResultsContain_batman(){

    //given
    String title = "batman";

    //when
    MovieResponse movieResponse = movieService.retrieveMovieInfo(title);

    //then
    Assert.assertThat("Returned movies must contain the word 'batman' in title field", movieResponse, movieMatcher(title));
  }

  @Test
  public void whenMovieTitleDoesNotExist_errorMessageIs_movieNotFound(){

    //given random title
    String title = "r84fjdkjoeiue";

    //when
    MovieResponse movieResponse = movieService.retrieveMovieInfo(title);

    //then
    Assert.assertEquals("Movie not found!", movieResponse.getError());
  }



  private Matcher<? super MovieResponse> movieMatcher(String expectedTitle) {

    return new BaseMatcher<MovieResponse>(){

      @Override
      public boolean matches(Object item) {

        if(item instanceof MovieResponse){
          MovieResponse movieResponse = (MovieResponse)item;
          Movie[] movies = movieResponse.getMovies();

          for(Movie movie : movies){
            if(movie.getTitle().toLowerCase().indexOf(expectedTitle)==-1){
              return false;
            }
          }
        }
        else{
          return false;
        }
        return true;
      }

      @Override
      public void describeTo(Description description) {

      }
    };
  }
}