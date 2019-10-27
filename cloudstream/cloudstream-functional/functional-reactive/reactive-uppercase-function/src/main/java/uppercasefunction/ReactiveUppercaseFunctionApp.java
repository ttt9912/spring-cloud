package uppercasefunction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/*
 * -------------------------------------------
 * Reactive Function
 * -------------------------------------------
 * - Imperative functions are triggered on each individual event
 * - Reactive functions are triggered once, passing the reference to the
 *   entire event stream abstractions (such as Flux and Mono)
 *
 * -------------------------------------------
 * Run
 * -------------------------------------------
 * - start ImperativeStringsSupplierApp to get events
 *
 * -------------------------------------------
 * Kafdrop Kafka UI
 * -------------------------------------------
 * http://localhost:9000/
 */
@Slf4j
@SpringBootApplication
public class ReactiveUppercaseFunctionApp {

    public static void main(final String[] args) {
        SpringApplication.run(ReactiveUppercaseFunctionApp.class, args);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> reactiveUppercase() {
        return flux -> flux
                .map(String::toUpperCase)
                .doOnEach(string -> log.info(">> reactiveUppercase - {}", string));
    }
}
