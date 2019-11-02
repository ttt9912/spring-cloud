package uppercasefunction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

/*
 *
 * -------------------------------------------
 * Kafdrop Kafka UI
 * -------------------------------------------
 * http://localhost:9000/
 */
@Slf4j
@SpringBootApplication
public class ImperativeUppercaseFunctionApp {

    public static void main(final String[] args) {
        SpringApplication.run(ImperativeUppercaseFunctionApp.class, args);
    }

    /*
     * -------------------------------------------
     * Function
     * -------------------------------------------
     * Bound to "uppercase-in-0" and "uppercase-out-0" channels
     *
     * - auto generates topics with the same name if properties
     *   spring.cloud.stream.bindings.<channelName>.destination
     *   are not set
     */
    @Bean
    public Function<String, String> uppercase() {
        return string -> {
            final String upperCase = string.toUpperCase();
            log.info(">> uppercase - {}", upperCase);
            return upperCase;
        };
    }
}
