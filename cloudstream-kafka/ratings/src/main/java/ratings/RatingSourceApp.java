package ratings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import ratings.data.Rating;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootApplication
public class RatingSourceApp {

    public static void main(String[] args) {
        SpringApplication.run(RatingSourceApp.class, args);
    }


    @Bean
    CommandLineRunner sendSingle(MessageChannel output) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        Rating payload = new Rating(LocalDateTime.now(), 500L, 4.5);
        objectObjectHashMap.put("key", payload.getMovieId());

        return args -> {
            output.send(MessageBuilder.createMessage(payload, new MessageHeaders(objectObjectHashMap)));
        };
    }

    /*
    @Bean
    @InboundChannelAdapter(value = "transform", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() throws IOException {
        final FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new ClassPathResource("ratings_json").getFile());
        return sourceReader;
    }*/
}
