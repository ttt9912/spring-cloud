package bookconsumer.feign;

import bookconsumer.data.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 * @FeignClient - provide spring.application.name of the
 * service
 */
@FeignClient("book-provider")
public interface BookClient {

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    @ResponseBody
    List<Book> findAll();
}
