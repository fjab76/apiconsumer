package fjab.song;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
public class SongServiceTest {

  private SongService songService;

  @Before
  public void setUp() throws Exception {
    songService = new SongService();
  }

  @Test
  public void whenSongTitleIs_jude_andArtistIs_beatles_allResultsContain_jude_and_beatles(){

    //given
    String artist="beatles", title = "jude";

    //when
    SongResponse songResponse = songService.retrieveSongInfo(artist, title);

    //then
    Assert.assertThat("Returned songs must contain the word 'beatles' in artist field and word 'jude' in title field", songResponse, songMatcher(artist, title));
  }

  @Test
  public void whenSongDoesNotExist_noSongsAreReturned(){

    //given random search values
    String artist="dk4idrklj", title = "lkj3ljeljk";

    //when
    SongResponse songResponse = songService.retrieveSongInfo(artist, title);

    //then
    Assert.assertEquals(0, songResponse.getSongList().getSongs().length);
  }

  private Matcher<? super SongResponse> songMatcher(String expectedArtist, String expectedTitle) {

    return new BaseMatcher<SongResponse>() {

      @Override
      public boolean matches(Object item) {

        if(item instanceof SongResponse){
          SongResponse songResponse = (SongResponse)item;
          Song[] songs = songResponse.getSongList().getSongs();

          for(Song song : songs){
            if(song.getArtistName().toLowerCase().indexOf(expectedArtist)==-1 || song.getTitle().toLowerCase().indexOf(expectedTitle)==-1){
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