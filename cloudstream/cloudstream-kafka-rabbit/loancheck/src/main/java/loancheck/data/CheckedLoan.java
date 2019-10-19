package loancheck.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedLoan {
    private String loanUuid;
    private LoanStatus status;
}