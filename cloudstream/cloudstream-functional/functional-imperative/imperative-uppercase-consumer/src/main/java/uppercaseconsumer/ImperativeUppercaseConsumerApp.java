package uppercaseconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

/*
 *
 * -------------------------------------------
 * Kafdrop Kafka UI
 * -------------------------------------------
 * http://localhost:9000/
 */
@Slf4j
@SpringBootApplication
public class ImperativeUppercaseConsumerApp {

    public static void main(final String[] args) {
        SpringApplication.run(ImperativeUppercaseConsumerApp.class, args);
    }

    /*
     * -------------------------------------------
     * Consumer
     * -------------------------------------------
     * Bound to "uppercaseSink-in-0" channel
     */
    @Bean
    public Consumer<String> uppercaseSink() {
        return string -> log.info(">> uppercaseSink - {}", string);
    }
}