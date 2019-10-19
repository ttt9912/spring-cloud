package movieratings.business;

import movieratings.data.Rating;
import movieratings.data.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(final RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void save(final Rating rating) {
        ratingRepository.save(rating);
    }

    public List<Rating> findByMovie(final String movieId) {
        return ratingRepository.findByMovieId(movieId);
    }
}
