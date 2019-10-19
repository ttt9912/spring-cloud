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
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import ratings.data.Rating;
import ratings.fileimport.RatingImportService;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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

        /*
         * - send Rating: MESSAGE_KEY will not be set
         *
         * - create & send Message<Rating>: able to set MESSAGE_KEY
         * but:
         * - key.serializer must be set (default is: ByteArraySerializer)
         * - key.deserializer must be set on consumer side for retrieving @Header(KafkaHeaders.MESSAGE_KEY)
         *   as String instead of byte[]
         *
         */
        @Splitter(inputChannel = "transform", outputChannel = Source.OUTPUT)
        public List<Message<Rating>> process(final File file) {


            Flux.just(ratingImportService.importRatings(file))
                    .subscribe();

            return ratingImportService.importRatings(file).stream()
                    .map(this::createRatingMessage)
                    .collect(Collectors.toList());
        }

        private Message<Rating> createRatingMessage(final Rating rating) {
            return MessageBuilder.withPayload(rating)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, String.valueOf(rating.getTimestamp()))
                    .build();
        }
    }
}
