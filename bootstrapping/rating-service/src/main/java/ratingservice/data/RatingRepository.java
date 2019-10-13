package ratingservice.data;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RatingRepository {

    public List<Rating> findAll() {
        return ratings();
    }

    public List<Rating> findByBook(final Long bookId) {
        if (bookId == null || bookId.equals(0L)) {
            return Collections.emptyList();
        }

        return ratings().stream()
                .filter(r -> r.getBookId().equals(bookId))
                .collect(Collectors.toList());
    }

    private List<Rating> ratings() {
        return Arrays.asList(
                new Rating(1L, 1L, 2),
                new Rating(2L, 1L, 3),
                new Rating(3L, 2L, 4),
                new Rating(4L, 2L, 5));
    }

}
