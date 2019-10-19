package movieratings;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import movieratings.business.MovieRatingService;
import movieratings.business.RatingService;
import movieratings.data.MovieRating;
import movieratings.data.Rating;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@Configuration
public class IntegrationConfig {
    private final MovieRatingService movieRatingService;
    private final RatingService ratingService;
    private final MeterRegistry meterRegistry;

    public IntegrationConfig(final MovieRatingService movieRatingService, final RatingService ratingService, final MeterRegistry meterRegistry) {
        this.movieRatingService = movieRatingService;
        this.ratingService = ratingService;
        this.meterRegistry = meterRegistry;
    }

    @Bean
    public MessageChannel save() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel filter() {
        return new DirectChannel();
    }

    @EnableBinding(Processor.class)
    class RatingProcessor {
    }

    @StreamListener(Processor.INPUT)
    @SendTo("save")
    public Rating save(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final String key,
                       @Payload final Rating rating) {
        meterRegistry.counter("rating_consumed").increment();
        ratingService.save(rating);
        log.info(">> received key={}", key);
        return rating;
    }

    @Transformer(inputChannel = "save", outputChannel = "filter")
    public MovieRating process(final Rating rating) {
        return movieRatingService.createMovieRating(rating.getMovieId());
    }

    @Filter(inputChannel = "filter", outputChannel = Processor.OUTPUT)
    public boolean filter(final MovieRating rating) {
        return rating.getTitle() != null;
    }

}
