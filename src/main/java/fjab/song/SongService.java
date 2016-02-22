package fjab.song;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@Service
public class SongService {

  public SongResponse retrieveSongInfo(String artist, String title) {

    RestTemplate restTemplate = new RestTemplate();
    SongResponse songResponse = restTemplate.getForObject("http://developer.echonest.com/api/v4/song/search?api_key=G6LCXWZWLO2CJY0BF&artist={artist}&title={title}",SongResponse.class,artist,title);
    return songResponse;
  }
}
