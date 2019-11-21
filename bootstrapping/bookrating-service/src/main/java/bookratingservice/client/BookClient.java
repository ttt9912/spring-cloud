package bookratingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("book-service")
public interface BookClient {

    @RequestMapping("/books")
    List<BookDto> findAllBooks();

    @RequestMapping("/books/{bookId}")
    BookDto findById(@PathVariable final Long bookId);
}
