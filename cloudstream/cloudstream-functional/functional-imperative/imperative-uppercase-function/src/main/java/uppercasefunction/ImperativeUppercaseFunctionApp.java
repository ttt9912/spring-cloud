package uppercasefunction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

/*
 * -------------------------------------------
 * Processor
 * -------------------------------------------
 * - in terms of spring integration
 * - "input" & "output" channel
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
     * Bound to "input" and "output" channels
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
