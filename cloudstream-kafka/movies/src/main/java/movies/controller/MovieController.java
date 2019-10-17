package movies.controller;

import movies.data.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movie")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/name")
    public ResponseEntity<String> findMovieName(@RequestParam("id") final String id) {
        return movieRepository.findById(id)
                .map(movie -> ResponseEntity.ok().body(movie.getTitle()))
                .orElse(ResponseEntity.ok().body(null));
    }
}
