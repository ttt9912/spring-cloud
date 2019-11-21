package loansource;

import loansource.data.Loan;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static common.collections.AnyElementFunction.any;

public class StaticLoanData {
    private static final List<String> names = Arrays.asList("Donald", "Theresa", "Vladimir", "Angela", "Emmanuel", "Shinzō", "Jacinda", "Kim");
    private static final List<Long> amounts = Arrays.asList(10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 100000000L);

    public static Loan getAny() {
        return new Loan(UUID.randomUUID().toString(),
                any(names), any(amounts));
    }
}
