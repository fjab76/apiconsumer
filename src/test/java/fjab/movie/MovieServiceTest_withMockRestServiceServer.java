package fjab.movie;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
public class MovieServiceTest_withMockRestServiceServer {

  private MovieService movieService;
  private MockRestServiceServer mockServer;
  private RestTemplate restTemplate;

  @Before
  public void setUp() throws Exception {
    movieService = new MovieService();
    restTemplate = new RestTemplate();
    movieService.setRestTemplate(restTemplate);
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }


  @Test
  public void whenMovieTitleIs_batman_allResultsContain_batman(){

    //given
    FileSystemResource response = new FileSystemResource("src/test/resources/__files/body-(root)-vS29h.json");
    String title = "batman";
    mockServer.expect(requestTo("http://www.omdbapi.com/?s="+title)).andExpect(method(HttpMethod.GET))
        .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

    //when
    MovieResponse movieResponse = movieService.retrieveMovieInfo(title);

    //then
    mockServer.verify();
    Assert.assertThat(String.format("Returned movies must contain the word '%s' in title field", title), movieResponse, titleOfMoviesContain(title));
  }

  @Test
  public void whenMovieTitleDoesNotExist_errorMessageIs_movieNotFound(){

    //given random title
    FileSystemResource response = new FileSystemResource("src/test/resources/__files/body-(root)-5310i.json");
    String title = "r84fjdkjoeiue";
    mockServer.expect(requestTo("http://www.omdbapi.com/?s="+title)).andExpect(method(HttpMethod.GET))
        .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

    //when
    MovieResponse movieResponse = movieService.retrieveMovieInfo(title);

    //then
    mockServer.verify();
    Assert.assertEquals("Movie not found!", movieResponse.getError());
  }



  private Matcher<? super MovieResponse> titleOfMoviesContain(String expectedTitle) {

    return new BaseMatcher<MovieResponse>(){

      @Override
      public boolean matches(Object item) {

        if(item instanceof MovieResponse){
          MovieResponse movieResponse = (MovieResponse)item;
          Movie[] movies = movieResponse.getMovies();

          return !Arrays.stream(movies).filter(movie -> !movie.getTitle().toLowerCase().contains(expectedTitle)).findFirst().isPresent();
        }
        else{
          return false;
        }
      }

      @Override
      public void describeTo(Description description) {

      }
    };
  }
}