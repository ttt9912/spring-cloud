package loancheck.process;

import loancheck.data.CheckedLoan;
import loancheck.data.Loan;
import loancheck.data.LoanStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoanCheckerStream {
    private final LoanProcessor loanProcessor;

    private static final Long MAX_AMOUNT = 10000L;

    public LoanCheckerStream(final LoanProcessor loanProcessor) {
        this.loanProcessor = loanProcessor;
    }

    @StreamListener(LoanProcessor.LOAN_IN)
    public void checkAndSortLoans(final Loan loan) {
        // alternative: @Router
        if (loan.getAmount() > MAX_AMOUNT) {
            log.info(">> DECLINED Loan {}", loan);
            final CheckedLoan checkedLoan = new CheckedLoan(loan.getUuid(), LoanStatus.DECLINED);
            loanProcessor.declined().send(message(checkedLoan));
        } else {
            log.info(">> APPROVED Loan {}", loan);
            final CheckedLoan checkedLoan = new CheckedLoan(loan.getUuid(), LoanStatus.APPROVED);
            loanProcessor.approved().send(message(loan));
        }
    }

    private static <T> Message<T> message(final T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
