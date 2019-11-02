package stringsupplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

/*
 * -------------------------------------------
 * Kafdrop Kafka UI
 * -------------------------------------------
 * http://localhost:9000/
 */
@Slf4j
@SpringBootApplication
public class ImperativeStringsSupplierApp {

    public static void main(final String[] args) {
        SpringApplication.run(ImperativeStringsSupplierApp.class, args);
    }

    /*
     * -------------------------------------------
     * Supplier
     * -------------------------------------------
     * - periodically triggered every 1s
     *      => spring.integration.poller.fixed-delay
     *
     * - sends result to default MessageChannel "stringSource-out-0"
     *      => Define Topic: spring.cloud.stream.bindings.stringSource-out-0.destination
     *      => default generated Topic: "stringSource-out-0"
     *
     * - if there is more than 1 Supplier bean, define bean
     *   name to be bound to binding desitnations with:
     *      => spring.cloud.function.definition
     */
    @Bean
    public Supplier<String> stringSource() {
        return () -> {
            final String string = StaticStrings.random();
            log.info(">> stringSource - {}", string);
            return string;
        };
    }

}
