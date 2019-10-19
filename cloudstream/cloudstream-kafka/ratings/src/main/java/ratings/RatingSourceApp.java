package ratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class RatingSourceApp {

    public static void main(final String[] args) {
        SpringApplication.run(RatingSourceApp.class, args);
    }

    @Bean
    @InboundChannelAdapter(value = "transform", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() throws IOException {
        final FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new ClassPathResource("ratings_json").getFile());
        return sourceReader;
    }
}
