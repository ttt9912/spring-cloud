package ratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * Runs on localhost:15300
 *
 * Browser => http://localhost:8080/rating-service/ratings/all
 * - Gateway server
 * - will route /rating-service/* to RatingService app
 */
@SpringBootApplication
@EnableEurekaClient
public class RatingServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(RatingServiceApp.class, args);
    }
}
