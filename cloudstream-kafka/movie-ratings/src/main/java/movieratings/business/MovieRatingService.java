package movieratings.business;

import movieratings.data.MovieRating;
import movieratings.data.Rating;
import movieratings.movie.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieRatingService {
    private final MovieService movieService;
    private final RatingService ratingService;

    public MovieRatingService(final MovieService movieService, final RatingService ratingService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
    }

    public MovieRating createMovieRating(final String movieId) {
        final String movieName = movieService.findMovieName(movieId);
        final List<Rating> ratings = ratingService.findByMovie(movieId);
        return new MovieRating(movieName, calculateRatingAverage(ratings), ratings.size());
    }

    private Double calculateRatingAverage(final List<Rating> ratings) {
        return ratings.stream()
                .collect(Collectors.averagingDouble(Rating::getRating));
    }
}
