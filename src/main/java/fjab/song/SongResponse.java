package fjab.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongResponse {

  @JsonProperty("response")
  private SongList songList;

  public SongResponse(){}

  public SongResponse(SongList songList){
    this.songList = songList;
  }

  public SongList getSongList() {
    return songList;
  }

  public void setSongList(SongList songList) {
    this.songList = songList;
  }
}
