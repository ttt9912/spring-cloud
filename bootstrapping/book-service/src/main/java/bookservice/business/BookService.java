package bookservice.business;

import bookservice.data.Book;
import bookservice.data.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        final List<Book> books = bookRepository.findAll();
        log.info("found total {} books", books.size());
        return books;
    }

    public Book findById(final Long bookId) {
        final Book book = bookRepository.findById(bookId);
        log.info("found book {}", book);
        return book;
    }
}
