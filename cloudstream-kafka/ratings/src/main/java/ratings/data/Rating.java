package ratings.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating { // extends Event
    private LocalDateTime timestamp; // key
    private String movieId;
    private Double rating;

    public String createKey() {
        return timestamp.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
