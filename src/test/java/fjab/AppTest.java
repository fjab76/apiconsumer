package fjab;

import fjab.movie.MovieResponse;
import fjab.movie.MovieService;
import fjab.movie.Movie;
import fjab.song.SongResponse;
import fjab.song.SongList;
import fjab.song.SongService;
import fjab.song.Song;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest {

  private App app;

  @Mock
  private MovieService movieService;
  @Mock
  private SongService songService;

  @Rule
  public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  @Before
  public void setUp() throws Exception {
    app = new App(movieService,songService);
  }

  @After
  public void tearDown(){
    System.clearProperty("api");
    System.clearProperty("artist");
    System.clearProperty("title");
  }

  @Test
  public void whenApiPropertyIsMissing_usageMessageIsPrintedOut() throws Exception {

    //when
    app.run();

    //then
    Assert.assertEquals(App.USAGE_MESSAGE, systemOutRule.getLog());

  }

  @Test
  public void whenApiPropertyIsOtherThan_movies_or_songs_usageMessageIsPrintedOut() throws Exception {

    //given
    System.setProperty("api", "xxx");

    //when
    app.run();

    //then
    Assert.assertEquals(App.USAGE_MESSAGE, systemOutRule.getLog());

  }

  @Test
  public void whenApiPropertyIs_movies_andTitlePropertyIsMissing_usageMessageIsPrintedOut() throws Exception {

    //given
    System.setProperty("api", "movies");

    //when
    app.run();

    //then
    Assert.assertEquals(App.USAGE_MESSAGE, systemOutRule.getLog());

  }

  @Test
  public void whenApiPropertyIs_songs_andTitlePropertyIsMissing_usageMessageIsPrintedOut() throws Exception {

    //given
    System.setProperty("api", "songs");

    //when
    app.run();

    //then
    Assert.assertEquals(App.USAGE_MESSAGE, systemOutRule.getLog());

  }

  @Test
  public void whenApiPropertyIs_songs_andArtistPropertyIsMissing_usageMessageIsPrintedOut() throws Exception {

    //given
    System.setProperty("api", "songs");
    System.setProperty("title","movies");

    //when
    app.run();

    //then
    Assert.assertEquals(App.USAGE_MESSAGE, systemOutRule.getLog());

  }

  @Test
  public void whenApiPropertyIs_movies_andThereIsTitleProperty_movieServiceIsCalled() throws Exception {

    //given
    System.setProperty("api","movies");
    System.setProperty("title","movies");
    when(movieService.retrieveMovieInfo(anyString())).thenReturn(new MovieResponse(new Movie[]{}));

    //when
    app.run();

    //then
    verify(movieService,times(1)).retrieveMovieInfo(anyString());

  }


  @Test
  public void whenApiPropertyIs_songs_songServiceIsCalled() throws Exception {

    //given
    System.setProperty("api","songs");
    System.setProperty("title","title");
    System.setProperty("artist","artist");
    when(songService.retrieveSongInfo(anyString(),anyString())).thenReturn(new SongResponse(new SongList(new Song[]{})));

    //when
    app.run();

    //then
    verify(songService,times(1)).retrieveSongInfo(anyString(),anyString());

  }


}