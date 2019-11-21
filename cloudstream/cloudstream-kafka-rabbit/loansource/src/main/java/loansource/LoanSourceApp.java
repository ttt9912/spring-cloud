package loansource;

import loansource.data.Loan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
     * Spring Cloud Function Support
     * - periodically triggered every 1s
     * - auto generates MessageChannel "supplyLoan-out-0"
     * - bind to topic "loan" with spring.cloud.stream.bindings.supplyLoan-out-0.destination property
     * - otherwise auto generates Topic "supplyLoan-out-0"
     */
    @Bean
    public Supplier<Loan> supplyLoan() {
        return () -> {
            final Loan loan = StaticLoanData.getAny();
            log.info(">> Loan created {}", loan);
            return loan;
        };
    }
}
