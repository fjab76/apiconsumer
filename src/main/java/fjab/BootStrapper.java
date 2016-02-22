package fjab;

import fjab.movie.MovieService;
import fjab.song.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootStrapper {


  @Bean
  @Autowired
  CommandLineRunner app(MovieService movieService, SongService songService){
    return new App(movieService,songService);
  }

  public static void main(String[] args) {
		SpringApplication.run(BootStrapper.class, args);
	}
}
