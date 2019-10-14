package bookratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * consume BookService & RatingService
 *
 * TODO: feign, hystrix, etc... projects
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class BookRatingService {

    public static void main(String[] args) {
        SpringApplication.run(BookRatingService.class, args);
    }
}
