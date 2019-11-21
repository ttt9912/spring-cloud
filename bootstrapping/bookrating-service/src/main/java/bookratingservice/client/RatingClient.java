package bookratingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("rating-service")
public interface RatingClient {

    @RequestMapping("/ratings")
    List<RatingDto> findAllRatings();

    @RequestMapping("/ratings/{bookId}")
    List<RatingDto> findRatingsByBookId(@PathVariable final Long bookId);
}
