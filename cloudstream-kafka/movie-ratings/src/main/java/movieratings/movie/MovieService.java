package movieratings.movie;

import movieratings.adaptder.MovieAdapter;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieAdapter movieAdapter;

    public MovieService(final MovieAdapter movieAdapter) {
        this.movieAdapter = movieAdapter;
    }

    public String findMovieName(final String id) {
        return movieAdapter.findMovieName(id);
    }
}
