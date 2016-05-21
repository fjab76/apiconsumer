package fjab;

import fjab.movie.MovieResponse;
import fjab.movie.MovieService;
import fjab.song.SongResponse;
import fjab.song.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
public class App implements CommandLineRunner {

  public static final String USAGE_MESSAGE = "It is necessary to provide the following properties." + System.lineSeparator() +
          "Property 'api' must specify the API to use ('movies' for movies and 'songs' for songs)." + System.lineSeparator() +
          "If movies, a property 'title' must specify the movie title." + System.lineSeparator() +
          "If songs, a property 'artist' must specify the artist name and a property 'title' the song title" + System.lineSeparator();

  private MovieService movieService;
  private SongService songService;

  public App(MovieService movieService, SongService songService){
    this.movieService = movieService;
    this.songService = songService;
  }

  @Override
  public void run(String... args) throws Exception {

    String api = System.getProperty("api");
    String artist = System.getProperty("artist");
    String title = System.getProperty("title");

    if(api!=null){
      if(api.equals("movies") && title != null){
        MovieResponse movieResponse = movieService.retrieveMovieInfo(title);
        if(movieResponse.getError()!=null){
          System.out.println(movieResponse.getError());
        }
        else{
          Stream.of(movieResponse.getMovies()).forEach(System.out::println);
        }
      }
      else if(api.equals("songs") && title != null && artist != null){
        SongResponse songResponse = songService.retrieveSongInfo(artist,title);
        if(songResponse.getSongList().getSongs().length==0){
          System.out.println("SongResponse not found!");
        }
        else{
          Stream.of(songResponse.getSongList().getSongs()).forEach(System.out::println);
        }
      }
      else{
        System.out.print(USAGE_MESSAGE);
      }
    }
    else{
      System.out.print(USAGE_MESSAGE);
    }
  }

}
