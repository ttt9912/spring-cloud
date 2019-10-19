package loansource;

import loansource.data.Loan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.function.Supplier;

/*
 * -----------------------------------------------------------------------------
 * Start Kafka & Rabbit
 * -----------------------------------------------------------------------------
 * - docker-compose up
 *
 * -----------------------------------------------------------------------------
 * kafdrop (Kafka UI)
 * -----------------------------------------------------------------------------
 * http://localhost:9000/
 *
 * -----------------------------------------------------------------------------
 * Rabbit UI
 * -----------------------------------------------------------------------------
 * localhost:15672
 * login: guest/guest
 *
 * -----------------------------------------------------------------------------
 * Run in Kafka/Rabbit mode
 * -----------------------------------------------------------------------------
 * mvn spring-boot:run -P<profile-choice>
 *
 */
@Slf4j
@SpringBootApplication
public class LoanSourceApp {

    public static void main(final String[] args) {
        SpringApplication.run(LoanSourceApp.class, args);
    }

    /*
     * -------------------------------------------
     * Spring Cloud Function Support
     * -------------------------------------------
     * https://cloud.spring.io/spring-cloud-stream/reference/html/spring-cloud-stream.html#spring_cloud_function
     *
     * @Bean of type Supplier<Loan>
     *
     * - periodically triggered every 1s
     *      => spring.integration.poller.fixed-delay
     *
     * - sends result to default MessageChannel "output"
     *      => TODO
     *
     * - if there is more than 1 Supplier bean, define bean
     *   name to be bound to binding desitnations with:
     *      => spring.cloud.function.definition
     *
     *
     *
     */
    @Bean
    public Supplier<Loan> supplyLoan() {
        return () -> {
            final Loan loan = new Loan(UUID.randomUUID().toString(), "Ben", 10000L);
            log.info(">> Loan created {}", loan);
            return loan;
        };
    }
}
