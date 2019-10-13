package ratingservice.service;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ratingservice.data.Rating;
import ratingservice.data.RatingRepository;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingRepository ratingRepository;

    public RatingController(final RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @GetMapping
    public List<Rating> findRatingsByBookId(@RequestParam Long bookId) {
        return ratingRepository.findByBook(bookId);
    }

    @GetMapping("/all")
    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }
}
