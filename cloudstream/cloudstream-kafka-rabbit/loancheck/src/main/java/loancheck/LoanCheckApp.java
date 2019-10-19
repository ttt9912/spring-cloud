package loancheck;

import loancheck.process.LoanProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(LoanProcessor.class)
public class LoanCheckApp {

    public static void main(final String[] args) {
        SpringApplication.run(LoanCheckApp.class, args);
    }
}
