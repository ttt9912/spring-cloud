package movieratings;

import lombok.extern.slf4j.Slf4j;
import movieratings.business.MovieRatingService;
import movieratings.data.MovieRating;
import movieratings.data.Rating;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@Configuration
public class IntegrationConfig {
    private final MovieRatingService movieRatingService;

    public IntegrationConfig(final MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @EnableBinding(Processor.class)
    class RatingProcessor {
    }

    @StreamListener(Processor.INPUT) // Processor.INPUT -> save -> process TODO
    @SendTo(Processor.OUTPUT)
    public MovieRating process(@Payload final Rating rating) { // @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) byte[] key TODO
        log.info(">> value={}", rating);
        // return Message<Rating> with headers?
        return movieRatingService.createMovieRating(rating);
    }

}
