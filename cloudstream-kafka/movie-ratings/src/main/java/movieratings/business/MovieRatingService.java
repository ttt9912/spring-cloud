package movieratings.business;

import movieratings.data.MovieRating;
import movieratings.data.Rating;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingService {

    public MovieRating createMovieRating(final Rating rating) {
        return new MovieRating(getTitle(rating.getMovieId()), calculateAverage(rating.getRating()));
    }

    private Double calculateAverage(final Double rating) {
        // cache Ratings TODO
        return rating;
    }

    private String getTitle(final Long movieId) {
        // retrieve Movie metadata TODO
        return "Gladiator";
    }
}
