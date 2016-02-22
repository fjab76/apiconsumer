package fjab.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by franciscoalvarez on 21/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongList {

  private Song[] songs;

  public SongList(){}

  public SongList(Song[] songs){
    this.songs = songs;
  }

  public Song[] getSongs() {
    return songs;
  }

  public void setSongs(Song[] songs) {
    this.songs = songs;
  }
}
