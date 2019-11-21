package bookratingservice.service;

import bookratingservice.business.BookRatingService;
import bookratingservice.data.BookRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookratings")
public class BookRatingController {
    private final BookRatingService bookRatingService;

    public BookRatingController(final BookRatingService bookRatingService) {
        this.bookRatingService = bookRatingService;
    }

    @GetMapping
    public List<BookRating> findAll() {
        return bookRatingService.findAll();
    }

    @GetMapping("/bookId")
    public BookRating findAll(@PathVariable final Long bookId) {
        return bookRatingService.findByBook(bookId);
    }
}
