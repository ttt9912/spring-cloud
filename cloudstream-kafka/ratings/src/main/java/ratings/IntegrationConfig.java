package ratings;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import ratings.data.Rating;
import ratings.fileimport.RatingImportService;

import java.io.File;
import java.util.List;

@Slf4j
@EnableIntegration
@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel transform() {
        return new DirectChannel();
    }

    @EnableBinding(Source.class)
    class RatingSourceOutput { // Kafka adapter, Source.OUTPUT = topic
    }

    @MessageEndpoint
    class RatingTransformer {
        private final RatingImportService ratingImportService;

        RatingTransformer(final RatingImportService ratingImportService) {
            this.ratingImportService = ratingImportService;
        }

        @Splitter(inputChannel = "transform", outputChannel = Source.OUTPUT)
        public List<Rating> process(final File file) {
            return ratingImportService.importRatings(file);
        }
    }
}
