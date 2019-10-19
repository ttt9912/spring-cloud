package loansource;

import loansource.data.Loan;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ScheduledLoanProducer {
    private final List<String> names = Arrays.asList("Donald", "Theresa", "Vladimir", "Angela", "Emmanuel", "Shinzō", "Jacinda", "Kim");
    private final List<Long> amounts = Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 100000000L);

    public void publishLoan() {
        final Loan loan = new Loan(UUID.randomUUID().toString(),
                any(names), any(amounts));
        log.info(">> Loan created {}", loan);
    }

    private <T> T any(final List<T> objects) {
        return objects.stream().findAny().orElse(null);
    }
}
