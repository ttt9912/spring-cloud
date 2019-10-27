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
     * - sends result to default MessageChannel "output"
     */
    @Bean
    public Supplier<Loan> supplyLoan() {
        return () -> {
            final Loan loan = StaticLoanData.get();
            log.info(">> Loan created {}", loan);
            return loan;
        };
    }
}
