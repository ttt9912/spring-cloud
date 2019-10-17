package bookprovider.data;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

    public List<Book> findAll() {
        return books();
    }

    public Book findById(final Long bookId) {
        return books().stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst().orElse(null);
    }

    private List<Book> books() {
        return Arrays.asList(
                new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
                new Book(2L, "Baeldung goes to the park", "Slavisa"));
    }
}
