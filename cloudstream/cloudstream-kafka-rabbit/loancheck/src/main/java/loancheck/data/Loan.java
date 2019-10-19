package loancheck.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    private String uuid;
    private String name;
    private Long amount;
}