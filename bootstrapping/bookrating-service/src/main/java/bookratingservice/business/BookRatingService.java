package bookratingservice.business;

import bookratingservice.client.BookClient;
import bookratingservice.client.BookDto;
import bookratingservice.client.RatingClient;
import bookratingservice.client.RatingDto;
import bookratingservice.data.BookRating;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookRatingService {
    private final BookClient bookClient;
    private final RatingClient ratingClient;

    public BookRatingService(final BookClient bookClient, final RatingClient ratingClient) {
        this.bookClient = bookClient;
        this.ratingClient = ratingClient;
    }

    public BookRating findByBook(final Long bookId) {
        final BookDto book = bookClient.findById(bookId);
        final List<RatingDto> ratings = ratingClient.findRatingsByBookId(bookId);
        return createBookRating(book, ratings);
    }

    public List<BookRating> findAll() {
        final List<BookDto> books = bookClient.findAllBooks();
        final List<RatingDto> ratings = ratingClient.findAllRatings();
        final Map<Long, List<RatingDto>> ratingsByBook = groupByBook(ratings);
        return createBookRatings(books, ratingsByBook);
    }

    private List<BookRating> createBookRatings(final List<BookDto> books, final Map<Long, List<RatingDto>> ratingsByBook) {
        return books.stream()
                .map(book -> createBookRating(book, ratingsByBook.get(book.getId())))
                .collect(Collectors.toList());
    }

    private BookRating createBookRating(final BookDto bookDto, final List<RatingDto> ratings) {
        return new BookRating(bookDto.getAuthor(), bookDto.getTitle(), calculateAverage(ratings));
    }

    private Double calculateAverage(final List<RatingDto> ratings) {
        return ratings.stream()
                .collect(Collectors.averagingInt(RatingDto::getStars));
    }

    private Map<Long, List<RatingDto>> groupByBook(final List<RatingDto> ratings) {
        return ratings.stream().collect(Collectors.groupingBy(RatingDto::getBookId));
    }
}
